package com.sample.aboutcanada.model.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class represents the country service.
 */
class CountryService {
    private var retrofit: Retrofit? = null
    /**
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     */
    fun getAPI(): CountryDetailsAPI {
        val baseUrl = "https://dl.dropboxusercontent.com/"
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(CountryDetailsAPI::class.java)
    }
}