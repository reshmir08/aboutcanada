package com.sample.aboutcanada.di

import com.sample.aboutcanada.view.CountryDetailsFragment
import com.sample.aboutcanada.view.MainActivity
import dagger.Component

@Component(modules = [CountryModule::class])
interface CountryComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(countryDetailsFragment: CountryDetailsFragment)
}