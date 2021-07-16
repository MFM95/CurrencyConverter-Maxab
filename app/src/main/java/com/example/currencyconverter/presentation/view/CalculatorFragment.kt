package com.example.currencyconverter.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.currencyconverter.R
import com.example.currencyconverter.presentation.uimodel.CurrencyUIModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_calculator.*


class CalculatorFragment : Fragment() {


    private var baseCurrency: String? = ""
    private var selectedCurrency: CurrencyUIModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun getData() {
        arguments?.let {
            baseCurrency = it.getString(ARG_BASE_CUR)?: ""
            selectedCurrency = it.getParcelable(ARG_SELECTED_CUR)
        }
    }

    private fun init() {
//        requireActivity().showKeyboard()
        tvBaseCurrency.text = baseCurrency
        tvSelectedCurrency.text = selectedCurrency?.currency
        tvSelectedRate.text = selectedCurrency?.rate.toString()
//        etBaseRate.addTextChangedListener()
    }

    private fun calculate(baseRate: Double) {

    }

    companion object {
        private const val ARG_BASE_CUR = "arg_base_currency"
        private const val ARG_SELECTED_CUR = "arg_selected`_currency"

        @JvmStatic
        fun newInstance(selectedCurrency: CurrencyUIModel, baseCurrency: String) =
            CalculatorFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_SELECTED_CUR, selectedCurrency)
                    putString(ARG_BASE_CUR, baseCurrency)
                }
            }
    }
}