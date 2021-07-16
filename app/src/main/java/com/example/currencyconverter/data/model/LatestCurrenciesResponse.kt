package com.example.currencyconverter.data.model

data class LatestCurrenciesResponse(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)