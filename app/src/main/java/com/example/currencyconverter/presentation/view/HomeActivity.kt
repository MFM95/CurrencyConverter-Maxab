package com.example.currencyconverter.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.currencyconverter.R
import com.example.currencyconverter.presentation.uimodel.ConverterType
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        btnHomeLocalConverter.setOnClickListener { startActivity(CurrenciesActivity.getIntent(this, ConverterType.LOCAL)) }
        btnHomeFixerConverter.setOnClickListener { startActivity(CurrenciesActivity.getIntent(this, ConverterType.FIXER)) }
    }


}