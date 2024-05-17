package com.example.finalprojectstocks.presentation

import android.view.View
import com.google.android.material.snackbar.Snackbar


interface UIStateHandler{}

fun UIStateHandler.showLoading(view: View) {
    view.visibility = View.VISIBLE
}

fun UIStateHandler.hideLoading(view: View) {
    view.visibility = View.GONE
}

fun UIStateHandler.showError(view: View, message: String, rootView: View) {
    view.visibility = View.VISIBLE
    Snackbar.make(rootView, "Error: $message", Snackbar.LENGTH_LONG).show()
}

fun UIStateHandler.hideError(view: View) {
    view.visibility = View.GONE
}

