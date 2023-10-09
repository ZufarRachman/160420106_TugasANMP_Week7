package com.example.anmp_week4.model

import com.google.gson.annotations.SerializedName

data class Student(
    val id:String?,
    @SerializedName("student_name")
    val name:String?,
    @SerializedName("birth_of_date")
    val bod:String?,
    val phone:String?,
    @SerializedName("photo_url")
    val photoUrl:String
)
data class CarModelItem(
    @SerializedName("color")
    val color: String,
    @SerializedName("features")
    val features: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("make")
    val make: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("specs")
    val specs: Specs,
    @SerializedName("year")
    val year: Int
)

data class Specs(
    @SerializedName("engine")
    val engine: String,
    @SerializedName("fuel_type")
    val fuelType: String,
    @SerializedName("transmission")
    val transmission: String
)

