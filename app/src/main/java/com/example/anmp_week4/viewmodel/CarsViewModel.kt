package com.example.anmp_week4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmp_week4.model.CarModelItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CarsViewModel(application: Application)
    : AndroidViewModel(application) {
    val carLD = MutableLiveData<ArrayList<CarModelItem>>()
    val carLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTagCars"
    private var queue: RequestQueue? = null

    fun refresh() {
        carLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/cars/cars.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<CarModelItem>>() { }.type
                val result = Gson().fromJson<List<CarModelItem>>(it, sType)
                carLD.value = result as ArrayList<CarModelItem>?
                loadingLD.value = false
                Log.d("showvolley", result.toString())
            }, {
                Log.d("showvolley", it.toString())
                carLoadErrorLD.value = true
                loadingLD.value = false
            }
        )
        stringRequest.retryPolicy = DefaultRetryPolicy(
            6000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}