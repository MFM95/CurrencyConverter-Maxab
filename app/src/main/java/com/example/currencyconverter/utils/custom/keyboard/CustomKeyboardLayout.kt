package com.example.currencyconverter.utils.custom.keyboard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.currencyconverter.R
import kotlinx.android.synthetic.main.layout_custom_keyboard.view.*

class CustomKeyboardLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs),
    KeyboardCharactersInterface {

    private lateinit var keyboardAdapter: CustomKeyboardAdapter
    private var items: List<KeyboardButton>? = null
    private lateinit var listener: KeyboardListener

    init {

        val inflater = context
            ?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_custom_keyboard, this, true)

        items = arrayListOf(
            KeyboardButton("1"),
            KeyboardButton("2"),
            KeyboardButton("3"),
            KeyboardButton("4"),
            KeyboardButton("5"),
            KeyboardButton("6"),
            KeyboardButton("7"),
            KeyboardButton("8"),
            KeyboardButton("9"),
            KeyboardButton("."),
            KeyboardButton("0"),
            KeyboardButton("", ContextCompat.getDrawable(context, R.drawable.ic_delete)),
        )
        keyboardAdapter = CustomKeyboardAdapter(context, items!!, this)
        gridKeyboard.adapter = keyboardAdapter

        view.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                gridKeyboard.getChildAt(0).requestFocus()
            }
        }
    }


    fun setListener(callback: KeyboardListener) {
        this.listener = callback
    }

    override fun onCharacterClick(button: KeyboardButton) {
        listener.onKeyboardClickListener(button)
    }

}