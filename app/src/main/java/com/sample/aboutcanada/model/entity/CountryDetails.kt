package com.sample.aboutcanada.model.entity

/**
 * This data class to store the Country details
 */
data class CountryDetails(var title: String, var rows: List<Rows>?)

data class Rows(var title: String?, var description: String?, var imageHref: String?)
