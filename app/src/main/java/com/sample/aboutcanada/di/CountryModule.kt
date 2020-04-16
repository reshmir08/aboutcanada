package com.sample.aboutcanada.di

import com.sample.aboutcanada.model.interactor.CountryInteractor
import com.sample.aboutcanada.model.interactor.CountryInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class CountryModule {
    @Provides
    fun provideInteractorImpl(): CountryInteractor {
        return CountryInteractorImpl()
    }
}