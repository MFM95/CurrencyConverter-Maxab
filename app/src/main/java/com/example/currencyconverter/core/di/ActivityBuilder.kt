package com.example.currencyconverter.core.di

import com.example.currencyconverter.presentation.view.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [])
    abstract fun bindHomeActivity(): HomeActivity
}