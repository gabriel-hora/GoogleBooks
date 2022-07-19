package com.example.googlebooks.ui.dialog

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import com.example.googlebooks.R

class LoadingDialog() {

    private lateinit var dialog: AlertDialog
    private lateinit var activity: FragmentActivity

    fun loadingDialog(activity: FragmentActivity?) {
        if (activity != null) {
            this.activity = activity
        }
    }

    fun startLoadingDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        val inflater: LayoutInflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_dialog, null))
        builder.setCancelable(true)

        dialog = builder.create()
        dialog.show()
    }

    fun dismissDialog() {
        dialog.dismiss()
    }
}