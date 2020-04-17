package com.sample.aboutcanada.presenter

import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.verify
import com.sample.aboutcanada.model.entity.CountryDetails
import com.sample.aboutcanada.model.interactor.CountryInteractor
import com.sample.aboutcanada.model.interactor.CountryInteractorImpl
import com.sample.aboutcanada.view.CountryView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CountryPresenterTest {

    @Mock
    private lateinit var countryView: CountryView

    @Mock
    private lateinit var interactor: CountryInteractor

    private lateinit var countryPresenter: CountryPresenter

    private lateinit var countryDetails: CountryDetails

    @Before
    fun setup() {
        countryPresenter = CountryPresenter(countryView, interactor)
        countryDetails = CountryDetails("test", null)
    }

    @Test
    fun shouldCallGetCountryDetails_WhenGetCountriesIsCalled() {
        countryPresenter.getCountries()
        verify(countryView).showLoader()
        verify(interactor).getCountryDetails(countryPresenter)
    }

    @Test
    fun getCountries_Success() {

        Mockito.doAnswer { invocation ->
            val listener: CountryInteractorImpl.CountryListener = invocation.getArgument(0)
            listener.onCountriesResponse(countryDetails)
            null
        }.`when`<CountryInteractor>(interactor).getCountryDetails(
            anyOrNull()
        )
        countryPresenter.getCountries()

        verify(countryView).showLoader()
        verify(countryView).hideLoader()
        verify(countryView).countriesReady(countryDetails)
    }

    @Test
    fun getCountries_Failure() {

        Mockito.doAnswer { invocation ->
            val listener: CountryInteractorImpl.CountryListener = invocation.getArgument(0)
            listener.onFailure()
            null
        }.`when`<CountryInteractor>(interactor).getCountryDetails(
            anyOrNull()
        )
        countryPresenter.getCountries()

        verify(countryView).showLoader()
        verify(countryView).hideLoader()
        verify(countryView).countriesFailed()
    }
}