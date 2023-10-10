package com.goodaysolutions.waltmartchallenge.common.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.goodaysolutions.waltmartchallenge.R
import java.text.DecimalFormat

val decimalFormat = DecimalFormat("#,##0.00")

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.findRootNavController() =
    activity?.findNavController(R.id.fr_core_activity_nav_host_fragment)

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Double.setFormattedCurrency(includeMoney: Boolean = false): String {
    var amountFormatted = "$${decimalFormat.format(this)}"
    if (includeMoney) amountFormatted += " MXN"
    return amountFormatted
}

