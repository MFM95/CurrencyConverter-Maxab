package com.example.currencyconverter.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.presentation.uimodel.CurrencyUIModel
import kotlinx.android.synthetic.main.item_currency.view.*

class CurrenciesAdapter : RecyclerView.Adapter<CurrenciesAdapter.CurrencyViewHolder>() {

    var results = ArrayList<CurrencyUIModel>()
    val itemClickLiveData by lazy { MutableLiveData<CurrencyUIModel>() }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(results[position])
        holder.itemView.setOnClickListener {
            itemClickLiveData.value = results[position]
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }

    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: CurrencyUIModel) {
            itemView.tvCurrencyName.text = item.currency
            itemView.tvCurrencyValue.text = String.format("%.2f", item.rate)
            itemView.viewDivider.isVisible = (results.indexOf(item) != results.size - 1)
        }

    }

}