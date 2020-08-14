package com.kempoamikom.historyapp.sumber

import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.kempoamikom.historyapp.Homepage
import com.kempoamikom.historyapp.R
import com.kempoamikom.historyapp.dialog.DialogInfo
import com.kempoamikom.historyapp.dialog.DialogLoading
import com.kempoamikom.historyapp.network.AppNetwork
import kotlinx.android.synthetic.main.activity_sumber_detail.*
import kotlinx.android.synthetic.main.toolbar_details.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick


class SumberDetail : AppCompatActivity() {

    val PREVIEW_DOC = "https://docs.google.com/gview?embedded=true&url="
    lateinit var loadingDialog: DialogLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sumber_detail)

        onDialogLoading()
        initUI()

    }

    private fun initUI() {

        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")
        val upload = intent.getStringExtra("upload")

//        titleBarTv.text = title
//
//        backBarBtn.onClick {
//            startActivity(intentFor<SumberActivity>())
//        }

        closeBtn.onClick {
            startActivity(intentFor<SumberActivity>())
        }

        val updateUrl = url.replace(" ", "%20")

        isLog("data url : $url")
        isLog("updateUrl : $updateUrl")

        webView.webViewClient = WebViewClient()
        webView.settings.setSupportZoom(true)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("$PREVIEW_DOC${AppNetwork.URL_FILE}$url")

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress < 100) {
                }
                if (newProgress == 100) {
                    loadingDialog.dismiss()
                }
            }
        }

    }

    private fun isLog(msg: String) {
        Log.e("Sumber", msg)
    }

    private fun onDialogLoading() {
        loadingDialog = DialogLoading(this@SumberDetail)
        loadingDialog.setCancelable(false)
        loadingDialog.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(intentFor<SumberActivity>())
    }

}