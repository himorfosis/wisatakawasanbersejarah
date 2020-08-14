package com.kempoamikom.historyapp.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import com.kempoamikom.historyapp.R
import kotlinx.android.synthetic.main.dialog_info.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DialogInfo(context: Context, title: String, message: String): Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_info)

        if (title.isNotEmpty()) {
            titleTv.text = title
        } else {
            titleTv.visibility = View.GONE
        }

        if (message.isNotEmpty()) {
            desctiptionTv.text = message
        } else {
            desctiptionTv.visibility = View.GONE
        }

        actionBtn.onClick {
            dismiss()
        }

    }


}