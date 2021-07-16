package com.example.currencyconverter.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyconverter.R
import com.example.currencyconverter.core.AppConstants
import com.example.currencyconverter.core.BaseResponse
import com.example.currencyconverter.core.ViewModelFactory
import com.example.currencyconverter.core.addFragment
import com.example.currencyconverter.data.model.LatestRatesResponse
import com.example.currencyconverter.presentation.uimodel.ConverterType
import com.example.currencyconverter.presentation.uimodel.CurrencyUIModel
import com.example.currencyconverter.presentation.viewmodel.CurrencyViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_currencies.*
import javax.inject.Inject


class LocalCurrenciesFragment : Fragment() {

    private lateinit var currenciesAdapter: CurrenciesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CurrencyViewModel>
    private val currencyViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(
            CurrencyViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currencies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setUpRecyclerView()
        currencyViewModel.converterType?.let {
            if (it == ConverterType.LOCAL) {
                displayLocalCurrencies()
            } else {
                getFixerCurrencies()
            }
        }
    }

    private fun setUpRecyclerView() {
        currenciesAdapter = CurrenciesAdapter()
        rvCurrenciesList.layoutManager = LinearLayoutManager(requireContext())
        rvCurrenciesList.adapter = currenciesAdapter
        currenciesAdapter.itemClickLiveData.observe(viewLifecycleOwner, {
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

    private fun getFixerCurrencies() {
        currencyViewModel.getLatestRates()
        currencyViewModel.latestRatesResponse.observe(viewLifecycleOwner, {
            when (it) {
                is BaseResponse.Success -> {
                    ((it.data) as LatestRatesResponse).let { response ->
                        Log.i("latestRatesResponse", response.rates.size.toString())
                    }
                }
                is BaseResponse.Error -> {
                    if (it.message.isNullOrEmpty())
                        Log.i(
                            "latestRatesResponse",
                            it.errorKind.toString() + " - " + getString(R.string.general_error_message)
                        )
                    else
                        Log.i("latestRatesResponse", it.errorKind.toString() + " - " + it.message)
                }
                is BaseResponse.Loading -> {
                    Log.i("latestRatesResponse", "Loading")
                }
            }
        })
    }


    private fun showCalculatorFragment(cur: CurrencyUIModel) {
        val baseCur = AppConstants.BASE_CURRENCY
        requireActivity().addFragment(
            CalculatorFragment.newInstance(cur, baseCur),
            R.id.flFragmentContainer,
            addToBackStack = true
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LocalCurrenciesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}