package com.log450.bchainvoteversion1.Network

import com.log450.bchainvoteversion1.Actors.Candidate.VaccineStats
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VaccineService {
    @GET("vaccines?")
    suspend fun getVaccinesStats(@Query("country") country:String): VaccineStats
}