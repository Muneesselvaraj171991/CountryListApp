package com.country.countryLlst.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.country.countryLlst.data.Country
import com.country.countryLlst.network.RemoteCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class CountryViewModel : ViewModel() {
    private val mRemoteCall: RemoteCall = RemoteCall.remoteCallInstance
    private val mCountLivedata = MutableLiveData<List<Country>>()

    fun init() {
        viewModelScope.async(Dispatchers.IO) {
            fetchData()
        }
    }
    private suspend fun fetchData() {
            mRemoteCall.getCountryList(object : RemoteCall.Result {
                override fun onResponse(countryList: List<Country>) {
                    mCountLivedata.postValue(countryList)

                }

                override fun onFailure() {
                    mCountLivedata.postValue(emptyList())
                }

            })
    }

    val countryLiveData: LiveData<List<Country>>
        get() = mCountLivedata
}