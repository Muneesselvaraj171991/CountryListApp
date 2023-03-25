package com.country.countryLlst

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.country.countryLlst.data.Country
import com.country.countryLlst.network.CheckInternetConnection
import com.country.countryLlst.viewmodel.CountryViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkConnection = CheckInternetConnection(this)
        val model = ViewModelProvider(this).get(CountryViewModel::class.java)
        networkConnection.observe(this) { isConnected ->
            if (isConnected) {
                model.init()

                model.countryLiveData.observe(this) { countryList: List<Country> ->
                    if (countryList.isNotEmpty()) {
                        setContent {
                            ComposeApp(countryList)
                        }                    }
                }
            }}




    }
}