package com.example.currencyconverter.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyconverter.R
import com.example.currencyconverter.utils.AppConstants
import com.example.currencyconverter.core.BaseResponse
import com.example.currencyconverter.core.ViewModelFactory
import com.example.currencyconverter.utils.addFragment
import com.example.currencyconverter.data.model.LatestRatesResponse
import com.example.currencyconverter.presentation.uimodel.ConverterType
import com.example.currencyconverter.presentation.uimodel.CurrencyUIModel
import com.example.currencyconverter.presentation.viewmodel.CurrencyViewModel
import com.example.currencyconverter.utils.NetworkErrorsHelper
import com.example.currencyconverter.utils.isNetworkConnected
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_currencies.*
import javax.inject.Inject


class CurrenciesFragment : Fragment() {

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
                observeOnLatestRates()
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
        val localCurr = LinkedHashMap<String, Double>()
        localCurr["EGP"] = 18.55
        localCurr["USD"] = 1.18
        localCurr["GBP"] =  0.85
        localCurr["CAD"] = 1.48
        localCurr["CHF"] = 1.08
        localCurr["AUD"] = 1.58
        localCurr["JPY"] = 130.0
        localCurr["SAR"] = 4.43
        localCurr["AED"] = 4.33
        localCurr["KWD"] = 0.35
        localCurr["QAR"] = 4.30
        localCurr["OMR"] = 0.45
        showResults(localCurr)
    }

    private fun getFixerCurrencies() {
        if (requireActivity().isNetworkConnected()) {
            currencyViewModel.getLatestRates()
        } else {
            showError(getString(R.string.internet_error_message))
        }
    }

    private fun observeOnLatestRates() {
        currencyViewModel.latestRatesResponse.observe(viewLifecycleOwner, {
            when (it) {
                is BaseResponse.Success -> {
                    showResults(((it.data) as LatestRatesResponse).rates)
                }
                is BaseResponse.Error -> {
                    handleErrors(it)
                }
                is BaseResponse.Loading -> {
                    showLoading()
                }
            }
        })
    }

    private fun handleErrors(error: BaseResponse.Error) {
        when (error.errorKind) {
            NetworkErrorsHelper.ErrorKind.SERVER_DOWN -> {
                showError(getString(R.string.server_down_error_message))
            }
            NetworkErrorsHelper.ErrorKind.TIME_OUT -> {
                showError(getString(R.string.time_out_error_message))
            }
            NetworkErrorsHelper.ErrorKind.UNEXPECTED -> {
                if (error.message.isNullOrEmpty())
                    showError(getString(R.string.general_error_message))
                else
                    showError(error.message)
            }
            else -> showError(getString(R.string.general_error_message))

        }

    }

    private fun showError(message: String) {
        Snackbar.make(clCurrencyRootView, message, Snackbar.LENGTH_LONG)
            .setAction("DISMISS") { }
            .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
            .show()
    }


    private fun showCalculatorFragment(cur: CurrencyUIModel) {
        val baseCur = AppConstants.BASE_CURRENCY
        requireActivity().addFragment(
            CalculatorFragment.newInstance(cur, baseCur),
            R.id.flFragmentContainer,
            addToBackStack = true
        )
    }

    private fun showLoading() {
        clBaseCurrency.visibility = View.GONE
        rvCurrenciesList.visibility = View.GONE
        progressLoading.visibility = View.VISIBLE

    }

    private fun showResults(list: HashMap<String, Double>) {
        progressLoading.visibility = View.GONE
        clBaseCurrency.visibility = View.VISIBLE
        rvCurrenciesList.visibility = View.VISIBLE
        currenciesAdapter.results.clear()
        currenciesAdapter.results.addAll(CurrencyUIModel.map(list))
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CurrenciesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}