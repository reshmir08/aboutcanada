package com.sample.aboutcanada.model.interactor

import com.sample.aboutcanada.model.interactor.CountryInteractorImpl.CountryListener

/**
 * This interface defines the contract for Interactor implementation
 */
interface CountryInteractor {
    fun getCountryDetails(countryListener: CountryListener)
}