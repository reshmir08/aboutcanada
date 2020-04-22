package com.sample.aboutcanada.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This data class to store the Country details
 */
data class CountryDetails(var title: String, var rows: List<Rows>?)

@Entity(tableName = "country_details")
data class Rows(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo var title: String?,
    @ColumnInfo var description: String?,
    @ColumnInfo(name = "image_url") var imageHref: String?
)
