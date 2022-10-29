package com.udacity.asteroidradar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: IN CASE THE PROJECT DID NOT FIND THE ANDROID_HOME
        //ADD YOUR SDK PATH TO local.properties
        //sdk.dir=F\:\\AllSDKs\\sdk_win
    }
}
