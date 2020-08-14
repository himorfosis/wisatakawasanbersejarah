package com.kempoamikom.historyapp.sumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.kempoamikom.historyapp.Homepage
import com.kempoamikom.historyapp.R
import com.kempoamikom.historyapp.network.AppNetwork
import com.kempoamikom.historyapp.network.service.ApiService
import com.kempoamikom.historyapp.situs.SitusAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_situs.*
import kotlinx.android.synthetic.main.activity_sumber_elektronik.*
import kotlinx.android.synthetic.main.layout_status_failure.*
import kotlinx.android.synthetic.main.toolbar_details.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class SumberActivity : AppCompatActivity() {

    private lateinit var adapterSumber: SumberAdapter
    val service = AppNetwork.buildService(ApiService::class.java)
    private val disposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sumber_elektronik)

        initUI()
        setAdapter()
        fetchData()

    }

    private fun initUI() {

        titleBarTv.text = getString(R.string.sumber)

        backBarBtn.onClick {
                       startActivity(intentFor<Homepage>())

        }
    }

    private fun setAdapter() {

        adapterSumber = SumberAdapter()
        sumberRv.apply {
            layoutManager = LinearLayoutManager(this@SumberActivity)
            adapter = adapterSumber
        }

    }

    private fun fetchData() {

        service.fetchSumber()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({
                isLog("Success")
                if (it.isNotEmpty()) {
                    isLog("Success Fetch")
                    // successs
                    adapterSumber.addAll(it)
                } else {
                    onFailedDataEmpty()
                }

            }, {
                onFailedFetch()
                isLog("Failed")
            }).let {
                disposable.add(it)
            }
    }

    private fun onFailedFetch() {

        status_error_tv.visibility = View.VISIBLE
        status_error_message_tv.visibility = View.VISIBLE

        status_error_tv.text = getString(R.string.failed_connection)
        status_error_message_tv.text = getString(R.string.failed_connection_message)
    }

    private fun onFailedDataEmpty() {

        status_error_tv.visibility = View.VISIBLE

        status_error_tv.text = getString(R.string.data_not_available)
//        status_error_message_tv.text = getString(R.string.data_not_available)
    }

    private fun isLog(msg: String) {
        Log.e("Situs", msg)
    }

}