package com.example.currencyconverter.core.di

import com.example.currencyconverter.utils.AppConstants
import com.example.currencyconverter.core.RetrofitServiceGenerator
import com.example.currencyconverter.data.repository.FixerRepositoryImpl
import com.example.currencyconverter.data.source.remote.FixerAPIService
import com.example.currencyconverter.domain.repository.FixerRepository
import dagger.Module
import dagger.Provides


@Module
class CurrenciesModule {

    @Provides
    fun provideFixerAPIService(): FixerAPIService =
        RetrofitServiceGenerator().createService(
            FixerAPIService::class.java,
            AppConstants.FIXER_API_BASE_URL
        )

    @Provides
    fun provideFixerRepository(fixerRepositoryImpl: FixerRepositoryImpl): FixerRepository =
        fixerRepositoryImpl


}