package com.kempoamikom.historyapp.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kempoamikom.historyapp.R
import kotlinx.android.synthetic.main.toolbar_details.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        initUI()

    }

    private fun initUI() {

        titleBarTv.text = "Tentang Aplikasi"
        backBarBtn.onClick {
            finish()
        }

    }

}