package com.example.currencyconverter.data.source.remote

import com.example.currencyconverter.data.model.LatestRatesResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface FixerAPIService {

    @POST("/latest")
    suspend fun getLatestRates(
        @Query("access_key") access_key: String,
        @Query("base") base: String
    ): Response<LatestRatesResponse>
}