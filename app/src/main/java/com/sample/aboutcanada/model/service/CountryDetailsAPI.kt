package com.sample.aboutcanada.model.service

import com.sample.aboutcanada.model.entity.CountryDetails
import retrofit2.Call
import retrofit2.http.GET

/**
 * This class represents the Country Details API
 */
interface CountryDetailsAPI {
    @get:GET("s/2iodh4vg0eortkl/facts.json")
    val results: Call<CountryDetails?>
}