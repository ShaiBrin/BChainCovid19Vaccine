package com.log450.bchainvoteversion1.Network

import com.log450.bchainvoteversion1.Actors.Candidate.VaccineStats
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
var  BASE_URL = "https://covid-api.mmediagroup.fr/v1/";

class VaccineAPI {

    private val client: VaccineService =  Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(VaccineService::class.java)

    suspend fun getAPI(): VaccineStats {
        return client.getVaccinesStats()
    }

    suspend fun updateAPI()  {
         client.getVaccinesStats().all.people_vaccinated + 1
    }

}