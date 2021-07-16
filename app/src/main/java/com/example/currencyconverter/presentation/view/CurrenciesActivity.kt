package com.example.currencyconverter.presentation.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyconverter.R
import com.example.currencyconverter.core.addFragment
import com.example.currencyconverter.presentation.uimodel.CurrencyUIModel
import kotlinx.android.synthetic.main.activity_converter.*

class CurrenciesActivity : AppCompatActivity() {


    private lateinit var currenciesAdapter: CurrenciesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
        setUpRecyclerView()
        displayLocalCurrencies()
    }

    private fun setUpRecyclerView() {
        currenciesAdapter = CurrenciesAdapter()
        rvCurrenciesList.layoutManager = LinearLayoutManager(this)
        rvCurrenciesList.adapter = currenciesAdapter
        currenciesAdapter.itemClickLiveData.observe(this, Observer {
            Log.i("itemClickLiveData", it.currency)
            showCalculatorFragment(it)
        })
    }


    private fun displayLocalCurrencies() {
        val localCurr = ArrayList<Pair<String, Double>>()
        localCurr.add(Pair("EGP", 18.55))  // Egyptian Pound
        localCurr.add(Pair("USD", 1.18))  // US Dollar
        localCurr.add(Pair("GBP", 0.85))  // British Pound
        localCurr.add(Pair("CAD", 1.48))  // Canadian Dollar
        localCurr.add(Pair("CHF", 1.08))  // Swiss Franc
        localCurr.add(Pair("AUD", 1.58))  // Australian Dollar
        localCurr.add(Pair("JPY", 130.0))  // Japanese Yen
        localCurr.add(Pair("SAR", 4.43))  // Saudi Riyal
        localCurr.add(Pair("AED", 4.33))  // UAE Dirham
        localCurr.add(Pair("KWD", 0.35))  // Kuwaiti Dinar
        localCurr.add(Pair("QAR", 4.30))  // Qatar Riyal
        localCurr.add(Pair("OMR", 0.45))  // Rial Omani
        localCurr.add(Pair("OMR", 0.45))  // Rial Omani
        currenciesAdapter.results.clear()
        currenciesAdapter.results.addAll(CurrencyUIModel.map(localCurr))
    }

    private fun displayFixerCurrencies() {

    }

    private fun showCalculatorFragment(cur: CurrencyUIModel) {
        val baseCur = "EUR"
        addFragment(CalculatorFragment.newInstance(cur, baseCur), R.id.flCalculatorFragmentContainer, addToBackStack = true)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, CurrenciesActivity::class.java)
        }
    }

}