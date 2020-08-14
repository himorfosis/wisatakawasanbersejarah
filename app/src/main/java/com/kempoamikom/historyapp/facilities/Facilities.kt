package com.kempoamikom.historyapp.facilities

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
import kotlinx.android.synthetic.main.activity_facilities.*
import kotlinx.android.synthetic.main.layout_status_failure.*
import kotlinx.android.synthetic.main.toolbar_details.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class Facilities : AppCompatActivity() {

    private lateinit var adapterFacilities: FacilitiesAdapter
    val service = AppNetwork.buildService(ApiService::class.java)
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facilities)

        initUI()
        setAdapter()
        fetchData()
//        fetchDataLocal()

    }

    private fun initUI() {

        titleBarTv.text = getString(R.string.fasilitas)

        backBarBtn.onClick {
            backPressed()
        }
    }

    private fun setAdapter() {

        adapterFacilities = FacilitiesAdapter()
        restaurantRv.apply {
            layoutManager = LinearLayoutManager(this@Facilities)
            adapter = adapterFacilities
        }
    }

    private fun fetchData() {

        //        adapterFacilities.addAll(DataSample.fetchFacilities())

        service.fetchFacilities()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({
                isLog("Success")
                if (it.isNotEmpty()) {
                    isLog("Success Fetch")
                    // successs
                    adapterFacilities.addAll(it)
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
        status_error_message_tv.visibility = View.VISIBLE

        status_error_tv.text = getString(R.string.data_not_available)
//        status_error_message_tv.text = getString(R.string.data_not_available)
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