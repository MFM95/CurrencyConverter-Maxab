package com.example.currencyconverter.domain.interactor

import com.example.currencyconverter.utils.AppConstants
import com.example.currencyconverter.data.model.LatestRatesResponse
import com.example.currencyconverter.data.repository.FixerRepositoryImpl
import retrofit2.Response
import javax.inject.Inject

class GetLatestRatesUseCase @Inject constructor(private val fixerRepositoryImpl: FixerRepositoryImpl) {

    suspend fun getLatestRates(): Response<LatestRatesResponse> {
        val accessKey = AppConstants.FIXER_API_KEY
        val base = AppConstants.BASE_CURRENCY
        return fixerRepositoryImpl.getLatestRates(accessKey, base)
    }
}