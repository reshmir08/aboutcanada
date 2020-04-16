package com.sample.aboutcanada.model.interactor

import com.sample.aboutcanada.model.entity.CountryDetails
import com.sample.aboutcanada.model.service.CountryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This interactor implementation calls the required API services and gets the data
 */
class CountryInteractorImpl : CountryInteractor {
    //    private var countryService = CountryService()
    override fun getCountryDetails(countryListener: CountryListener) {
        CountryService().getAPI()
            .results
            .enqueue(object : Callback<CountryDetails?> {
                override fun onResponse(
                    call: Call<CountryDetails?>,
                    response: Response<CountryDetails?>
                ) {
                    val countryDetails = response.body()
                    if (countryDetails != null) {
                        countryListener.onCountriesResponse(countryDetails)
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
        fun onCountriesResponse(countryDetails: CountryDetails?)
        fun onFailure()
    }
}