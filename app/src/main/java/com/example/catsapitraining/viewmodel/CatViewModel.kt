package com.example.catsapitraining.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsapitraining.api.CatApi
import com.example.catsapitraining.api.CatClient
import com.example.catsapitraining.data.Cat
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CatViewModel: ViewModel() {

    val cat = MutableLiveData<ArrayList<Cat>>()

    init {
        searchCat()
    }

    fun searchCat() {

        CatClient.retrofitService
            .getAllData(5)
            .enqueue(object: Callback<List<Cat>> {
                override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                    Log.d("searchCat", response.message())
                    if (response.isSuccessful) {
                        Log.d("searchCat", "ahay2")
                        cat.postValue(response.body() as ArrayList<Cat>?)
                        Log.d("searchCat", response.toString())
                    }
                }

                override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                    Log.e("Failure", t.message.toString())
                }
            })
    }

    fun getCat(): LiveData<ArrayList<Cat>> {
        return cat
    }
}