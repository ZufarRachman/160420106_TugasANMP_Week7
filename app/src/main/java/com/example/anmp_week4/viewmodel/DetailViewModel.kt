package com.example.anmp_week4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.anmp_week4.model.Student
import com.google.gson.Gson

class DetailViewModel(application: Application):
    AndroidViewModel(application){

    val studentLD = MutableLiveData<Student>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun fetch(studentId: String) {
        if ( queue == null){
            queue = Volley.newRequestQueue(getApplication())
        }
        val url = "http://adv.jitusolution.com/student.php?id=$studentId"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { respone ->
                val student = Gson().fromJson(respone.toString(), Student::class.java)
                studentLD.value = student
            },
            { error ->
                Log.d(TAG, error.toString())
            }
        )
        jsonObjectRequest.tag = TAG
        queue?.add(jsonObjectRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}