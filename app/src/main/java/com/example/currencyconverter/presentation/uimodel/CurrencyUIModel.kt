package com.example.currencyconverter.presentation.uimodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CurrencyUIModel(
    var currency: String,
    var rate: Double
): Parcelable {
    companion object {
        fun map(list: ArrayList<Pair<String, Double>>): ArrayList<CurrencyUIModel> {
            val result = ArrayList<CurrencyUIModel>()
            list.forEach {
                result.add(CurrencyUIModel(it.first, it.second))
            }
            return result
        }
    }
}
