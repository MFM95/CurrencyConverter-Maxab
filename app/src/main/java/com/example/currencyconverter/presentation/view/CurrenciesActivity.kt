package com.example.currencyconverter.presentation.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyconverter.R
import com.example.currencyconverter.core.DaggerFragmentActivity
import com.example.currencyconverter.core.ViewModelFactory
import com.example.currencyconverter.core.addFragment
import com.example.currencyconverter.presentation.uimodel.ConverterType
import com.example.currencyconverter.presentation.uimodel.CurrencyUIModel
import com.example.currencyconverter.presentation.viewmodel.CurrencyViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_converter.*
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
        addFragment(LocalCurrenciesFragment.newInstance(), R.id.flFragmentContainer)
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