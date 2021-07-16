package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.data.model.LatestRatesResponse
import retrofit2.Response

interface FixerRepository {
    suspend fun getLatestRates(accessKey: String, base: String): Response<LatestRatesResponse>
}