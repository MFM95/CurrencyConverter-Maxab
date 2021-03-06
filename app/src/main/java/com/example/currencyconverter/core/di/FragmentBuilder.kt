package com.example.currencyconverter.core.di

import com.example.currencyconverter.presentation.view.CalculatorFragment
import com.example.currencyconverter.presentation.view.CurrenciesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [])
    internal abstract fun provideLocalCurrenciesFragment(): CurrenciesFragment

    @ContributesAndroidInjector(modules = [])
    internal abstract fun provideCalculatorFragment(): CalculatorFragment

}