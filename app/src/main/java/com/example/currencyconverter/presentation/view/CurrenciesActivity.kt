package com.example.currencyconverter.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.R
import com.example.currencyconverter.core.DaggerFragmentActivity
import com.example.currencyconverter.core.ViewModelFactory
import com.example.currencyconverter.utils.addFragment
import com.example.currencyconverter.presentation.uimodel.ConverterType
import com.example.currencyconverter.presentation.viewmodel.CurrencyViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class CurrenciesActivity : DaggerFragmentActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CurrencyViewModel>
    private val currencyViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(
            CurrencyViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_converter)
        getData()
    }

    private fun getData() {
        intent?.apply {
            currencyViewModel.converterType = getSerializableExtra(EXTRA_CONVERT_TYPE) as ConverterType
            showCurrenciesFragment()
        }
    }

    private fun showCurrenciesFragment() {
        addFragment(CurrenciesFragment.newInstance(), R.id.flFragmentContainer)
    }

    companion object {
        private const val EXTRA_CONVERT_TYPE = "extra_convert_type"
        fun getIntent(context: Context, type: ConverterType): Intent {
            val intent = Intent(context, CurrenciesActivity::class.java)
            val extras: Bundle = Bundle().apply {
                putSerializable(EXTRA_CONVERT_TYPE, type)
            }
            intent.apply { putExtras(extras) }
            return intent
        }
    }

}