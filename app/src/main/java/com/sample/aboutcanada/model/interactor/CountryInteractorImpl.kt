package com.sample.aboutcanada.model.interactor

import android.util.Log
import androidx.lifecycle.LiveData
import com.sample.aboutcanada.helper.DatabaseHelper
import com.sample.aboutcanada.model.entity.CountryDetails
import com.sample.aboutcanada.model.entity.CountryTitle
import com.sample.aboutcanada.model.entity.Rows
import com.sample.aboutcanada.model.service.CountryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This interactor implementation calls the required API services and gets the data
 */
class CountryInteractorImpl : CountryInteractor {

    override fun getCountryDetailsOffline(countryListener: CountryListener): LiveData<List<Rows>>? {
        val instance = DatabaseHelper.getInstance()
        return instance?.countryDao()?.getCountryDetails()
    }

    override fun getCountryDetails(countryListener: CountryListener) {
        // Get the data from API
        CountryService().getAPI().results
            .enqueue(object : Callback<CountryDetails?> {
                override fun onResponse(
                    call: Call<CountryDetails?>,
                    response: Response<CountryDetails?>
                ) {
                    val countryDetails = response.body()
                    if (countryDetails != null) {
                        val nonNullRows = countryDetails.rows?.filter { it.title != null }
                        countryDetails.rows = nonNullRows

                        countryListener.onCountryDetailsSuccess(countryDetails)

                        val thread = Thread {
                            // Clear the old data
                            DatabaseHelper.getInstance()?.countryDao()
                                ?.clearCountryDetails()

                            // Save data to database
                            val rowId = DatabaseHelper.getInstance()?.countryDao()
                                ?.saveCountryTitle(CountryTitle(countryDetails.title))

                            DatabaseHelper.getInstance()?.countryDao()
                                ?.saveCountryDetails(countryDetails.rows)

                            Log.i("Interactor= ", rowId.toString())
                        }
                        thread.start()
                    }
                }

                override fun onFailure(
                    call: Call<CountryDetails?>,
                    t: Throwable
                ) {
                    countryListener.onFailure()
                }
            })
    }

    /**
     * This interface communicates with the presenter for success and failure responses
     */
    interface CountryListener {
        fun onCountryDetailsSuccess(countryDetails: CountryDetails?)
        fun onFailure()
    }
}