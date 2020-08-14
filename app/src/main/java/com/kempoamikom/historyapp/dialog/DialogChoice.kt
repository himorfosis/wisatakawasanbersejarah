package com.kempoamikom.historyapp.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.kempoamikom.historyapp.R
import kotlinx.android.synthetic.main.dialog_choice.*
import kotlinx.android.synthetic.main.dialog_choice.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DialogChoice(context: Context, title: String, message: String): Dialog(context) {

    lateinit var onClickItem: OnClickItem
    val ACCEPT = "ACCEPT"
    val DISMISS = "DISMISS"

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_choice)

        titleTv.text = title
        desctiptionTv.text = message

        actionBtn.onClick {
            onClickItem.onItemClicked("ACCEPT")
        }

        dismissBtn.onClick {
            onClickItem.onItemClicked("DISMISS")
        }

    }

    interface OnClickItem {
        fun onItemClicked(data: String)
    }

    fun setOnclick(onClickItem: OnClickItem) {
        this.onClickItem = onClickItem

    }

}