package com.country.countryLlst.network

import com.country.countryLlst.data.Country
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.HttpURLConnection
import java.net.URL

class RemoteCall private constructor() {
    private val mGson: Gson
    private var mUrl: URL? = null
    init {
        mGson = Gson()
        mUrl = URL(URL)
    }
    fun getCountryList(result: Result) {
        try {
            val urlConnection = mUrl!!.openConnection() as HttpURLConnection
            val data = urlConnection.inputStream.bufferedReader().readText()
            val typeOfT =
                TypeToken.getParameterized(MutableList::class.java, Country::class.java).type
            result.onResponse(mGson.fromJson(data, typeOfT))
        } catch (e: Exception) {
         result.onFailure()
        }
    }
    interface Result {
        fun onResponse(countryList: List<Country>)
        fun onFailure()
    }
    companion object {
        private const val URL = "https://restcountries.com/v3.1/all"
        private var sRemoteCall: RemoteCall? = null
        @get:Synchronized
        val remoteCallInstance: RemoteCall
            get() {
                if (sRemoteCall == null) {
                    sRemoteCall = RemoteCall()
                }
                return sRemoteCall!!
            }
    }
}