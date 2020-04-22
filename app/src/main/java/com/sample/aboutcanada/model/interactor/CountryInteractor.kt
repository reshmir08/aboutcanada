package com.sample.aboutcanada.model.interactor

import androidx.lifecycle.LiveData
import com.sample.aboutcanada.model.entity.Rows

/**
 * This interface defines the contract for Interactor implementation
 */
interface CountryInteractor {
    fun getCountryDetails(countryListener: CountryInteractorImpl.CountryListener)
    fun getCountryDetailsOffline(countryListener: CountryInteractorImpl.CountryListener): LiveData<List<Rows>>?
}