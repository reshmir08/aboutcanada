package com.sample.aboutcanada.presenter

import com.sample.aboutcanada.model.entity.CountryDetails
import com.sample.aboutcanada.model.interactor.CountryInteractor
import com.sample.aboutcanada.model.interactor.CountryInteractorImpl.CountryListener
import com.sample.aboutcanada.view.CountryView

/**
 * This class represents the country presenter.
 */
class CountryPresenter(
    private val countryView: CountryView,
    private val countryInteractor: CountryInteractor
) : CountryListener {

    fun getCountryDetails() {
        countryView.showLoader()
        countryInteractor.getCountryDetails(this)
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