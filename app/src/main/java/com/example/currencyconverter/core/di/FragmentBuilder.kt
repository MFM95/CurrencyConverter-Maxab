package com.example.currencyconverter.core.di

import com.example.currencyconverter.presentation.view.LocalCurrenciesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [])
    internal abstract fun provideLocalCurrenciesFragment(): LocalCurrenciesFragment

}