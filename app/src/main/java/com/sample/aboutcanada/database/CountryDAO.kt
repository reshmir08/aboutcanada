package com.sample.aboutcanada.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.aboutcanada.model.entity.CountryTitle
import com.sample.aboutcanada.model.entity.Rows

@Dao
interface CountryDAO {
    @Insert
    fun saveCountryDetails(rows: List<Rows>?): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveCountryTitle(title: CountryTitle): Long

    @Query(value = "Delete from country_details")
    fun clearCountryDetails()

    @Query(value = "Select * from country_details")
    fun getCountryDetails(): LiveData<List<Rows>>

    @Query(value = "Select count(*) from country_details")
    fun getCountryCount(): Int

    @Query(value = "Select title from country_title")
    fun getCountryTitle(): String
}