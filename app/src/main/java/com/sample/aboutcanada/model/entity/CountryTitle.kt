package com.sample.aboutcanada.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_title")
data class CountryTitle(
    @PrimaryKey var title: String
)
