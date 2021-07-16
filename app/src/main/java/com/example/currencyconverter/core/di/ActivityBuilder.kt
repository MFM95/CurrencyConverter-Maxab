package com.example.currencyconverter.core.di

import com.example.currencyconverter.presentation.view.CurrenciesActivity
import com.example.currencyconverter.presentation.view.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [CurrenciesModule::class])
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [CurrenciesModule::class, FragmentBuilder::class])
    abstract fun bindCurrenciesActivity(): CurrenciesActivity
}