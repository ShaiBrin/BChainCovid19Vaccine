package com.log450.bchainvoteversion1.Actors.Candidate

import com.google.gson.annotations.SerializedName

data class VaccineStats (

    @SerializedName("All")
    val all:Vaccine
)

data class Vaccine(

    @SerializedName("administered")
    val administered: Double,

    @SerializedName("people_vaccinated")
    val people_vaccinated: Int,

    @SerializedName("people_partially_vaccinated")
    val people_partially_vaccinated: Int,

    @SerializedName("country")
    val country: String,

    @SerializedName("population")
    val population: Int,

    @SerializedName("sq_km_area")
    val area: Int,

    @SerializedName("life_expectancy")
    val lifeExpectancy: Double,

    @SerializedName("elevation_in_meters")
    val elevation: Int,

    @SerializedName("continent")
    val continent: String,

    @SerializedName("abbreviation")
    val abbrevation: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("iso")
    val iso: String,

    @SerializedName("capital_city")
    var capital: String,

    @SerializedName("updated")
    var update: String,
)


