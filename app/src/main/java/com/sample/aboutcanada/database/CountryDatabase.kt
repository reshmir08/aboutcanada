package com.sample.aboutcanada.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.aboutcanada.model.entity.CountryTitle
import com.sample.aboutcanada.model.entity.Rows

@Database(entities = [(Rows::class), (CountryTitle::class)], version = 1)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDAO
}