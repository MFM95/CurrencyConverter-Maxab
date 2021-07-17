package com.example.currencyconverter.utils.custom.keyboard

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.currencyconverter.R
import kotlinx.android.synthetic.main.item_custom_keyboard.view.*

class CustomKeyboardAdapter(
    private val context: Context, private val items: List<KeyboardButton>,
    private var callback: KeyboardCharactersInterface
) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_custom_keyboard, null)
        val character = items[position].text
        val icon = items[position].icon
        if (!character.isNullOrEmpty()) {
            view.tvKeyboardItemChar.visibility = View.VISIBLE
            view.ivKeyboardItemIcon.visibility = View.GONE
            view.tvKeyboardItemChar.text = character
        } else if (icon != null) {
            view.tvKeyboardItemChar.visibility = View.GONE
            view.ivKeyboardItemIcon.visibility = View.VISIBLE
            view.ivKeyboardItemIcon.setImageDrawable((icon))
        }

        view.isClickable = true

        view.setOnClickListener {
            callback.onCharacterClick(items[position])
        }

        return view
    }


}