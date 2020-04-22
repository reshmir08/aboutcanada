package com.sample.aboutcanada.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.aboutcanada.R
import com.sample.aboutcanada.helper.DatabaseHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DatabaseHelper.createDatabase(this)
    }
}
