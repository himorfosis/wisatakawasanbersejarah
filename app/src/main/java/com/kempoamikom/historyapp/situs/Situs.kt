package com.kempoamikom.historyapp.situs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.kempoamikom.historyapp.Homepage
import com.kempoamikom.historyapp.R
import com.kempoamikom.historyapp.data.DataSample
import com.kempoamikom.historyapp.network.AppNetwork
import com.kempoamikom.historyapp.network.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_situs.*
import kotlinx.android.synthetic.main.layout_status_failure.*
import kotlinx.android.synthetic.main.toolbar_details.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class Situs : AppCompatActivity() {

    private lateinit var adapterSitus: SitusAdapter
    val service = AppNetwork.buildService(ApiService::class.java)
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_situs)

        initUI()
        setAdapter()
        fetchData()
//        fetchDataLocal()

    }

    private fun initUI() {

        titleBarTv.text = getString(R.string.situs_sejarah)

        backBarBtn.onClick {
            backPressed()
        }

    }

    private fun setAdapter() {

        adapterSitus = SitusAdapter()
        situsRv.apply {
            layoutManager = LinearLayoutManager(this@Situs)
            adapter = adapterSitus
        }

    }

    private fun fetchDataLocal() {
//        adapterSitus.addAll(DataSample.fetchSitus())
    }

    private fun fetchData() {

        service.fetchSitus()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({
                isLog("Success")
                if (it.isNotEmpty()) {
                    isLog("Success Fetch")
                    // successs
                    adapterSitus.addAll(it)

                } else {
                    onFailedDataEmpty()
                }

            }, {
                onFailedFetch()
                isLog("Failed : $it")
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
        status_error_message_tv.visibility = View.VISIBLE

        status_error_tv.text = getString(R.string.data_not_available)
        status_error_message_tv.text = getString(R.string.data_not_available_message)
    }

    private fun isLog(msg: String) {
        Log.e("Situs", msg)
    }

    private fun backPressed() {
        startActivity(intentFor<Homepage>())
    }

    override fun onBackPressed() {
        backPressed()
    }

}