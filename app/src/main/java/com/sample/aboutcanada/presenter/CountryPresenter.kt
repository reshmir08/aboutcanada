package com.sample.aboutcanada.presenter

import androidx.lifecycle.Observer
import com.sample.aboutcanada.helper.DatabaseHelper
import com.sample.aboutcanada.model.entity.CountryDetails
import com.sample.aboutcanada.model.entity.Rows
import com.sample.aboutcanada.model.interactor.CountryInteractor
import com.sample.aboutcanada.model.interactor.CountryInteractorImpl.CountryListener
import com.sample.aboutcanada.view.CountryDetailsFragment
import com.sample.aboutcanada.view.CountryView

/**
 * This class represents the country presenter.
 */
class CountryPresenter(
    private val countryView: CountryView,
    private val countryInteractor: CountryInteractor
) : CountryListener {

    /**
     * This function gets the data from API
     */
    fun getCountryDetails() {
        countryView.showLoader()
        countryInteractor.getCountryDetails(this)
    }

    /**
     * This function gets the data from database
     */
    fun getCountryDetailsOffline() {
        countryView.showLoader()

        val countryTitle = DatabaseHelper.getInstance()?.countryDao()?.getCountryTitle()

        val countryCount = DatabaseHelper.getInstance()?.countryDao()?.getCountryCount()
        if (countryCount == null || countryCount == 0) {
            countryView.hideLoader()
            countryView.onOfflineError()
        } else {
            val liveDataCountryDetails = countryInteractor.getCountryDetailsOffline(this)
            liveDataCountryDetails?.observe(countryView as CountryDetailsFragment,
                object : Observer<List<Rows>> {
                    override fun onChanged(rows: List<Rows>?) {
                        countryView.hideLoader()
                        countryView.countryDetailsReady(CountryDetails(countryTitle ?: "", rows))
                        liveDataCountryDetails.removeObserver(this)
                    }
                })
        }
    }

    override fun onCountryDetailsSuccess(countryDetails: CountryDetails?) {
        countryView.hideLoader()
        countryView.countryDetailsReady(countryDetails)
    }

    override fun onFailure() {
        countryView.hideLoader()
        countryView.countryDetailsFailed()
    }
}