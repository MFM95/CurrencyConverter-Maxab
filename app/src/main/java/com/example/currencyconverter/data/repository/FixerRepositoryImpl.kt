package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.model.LatestRatesResponse
import com.example.currencyconverter.data.source.remote.FixerAPIService
import com.example.currencyconverter.domain.repository.FixerRepository
import retrofit2.Response
import javax.inject.Inject

class FixerRepositoryImpl @Inject constructor(private val fixerAPIService: FixerAPIService) :
    FixerRepository {
    override suspend fun getLatestRates(accessKey: String, base: String): Response<LatestRatesResponse> {
        return fixerAPIService.getLatestRates(accessKey, base)
    }
}