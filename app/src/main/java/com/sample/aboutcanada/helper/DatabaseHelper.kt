package com.sample.aboutcanada.helper

import android.content.Context
import androidx.room.Room
import com.sample.aboutcanada.database.CountryDatabase

object DatabaseHelper {
    @Volatile
    private var INSTANCE: CountryDatabase? = null

    fun getInstance(): CountryDatabase? = INSTANCE

    fun createDatabase(context: Context): CountryDatabase =
        INSTANCE ?: synchronized(this) {
            INSTANCE ?: bindDatabase(context).also { INSTANCE = it }
        }

    private fun bindDatabase(context: Context) =
        Room.databaseBuilder(context.applicationContext, CountryDatabase::class.java, "country-db")
            .allowMainThreadQueries()
            .build()
}