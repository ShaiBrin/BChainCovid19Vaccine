package com.log450.bchainvoteversion1.Network

import com.log450.bchainvoteversion1.Actors.Candidate.VaccineStats
import retrofit2.Call
import retrofit2.http.GET

interface VaccineService {
    @GET("vaccines?country=Canada")
    suspend fun getVaccinesStats(): VaccineStats
}