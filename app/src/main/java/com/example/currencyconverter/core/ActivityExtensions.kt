package com.example.currencyconverter.core

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.io.File
import java.util.*


inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun FragmentActivity.addFragment(
    fragment: Fragment,
    frameId: Int,
    addToBackStack: Boolean = false,
    tag: String = ""
) {
    supportFragmentManager.inTransaction {
        add(frameId, fragment)
        if (addToBackStack) {
            addToBackStack(tag)
        }
    }
}

