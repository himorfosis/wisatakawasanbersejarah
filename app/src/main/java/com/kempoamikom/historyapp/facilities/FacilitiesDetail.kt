package com.kempoamikom.historyapp.facilities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kempoamikom.historyapp.R
import com.kempoamikom.historyapp.dialog.DialogInfo
import com.kempoamikom.historyapp.network.AppNetwork
import kotlinx.android.synthetic.main.activity_facilities_detail.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class FacilitiesDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facilities_detail)

        initUI()

    }

    private fun initUI() {

        val id = intent.getStringExtra("id")
        val address = intent.getStringExtra("address")
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val foto = intent.getStringExtra("foto")
        val latitude = intent.getStringExtra("latitude")
        val longtitude = intent.getStringExtra("longtitude")

        titleTv.text = name
        desctiptionTv.text = description
        addressTv.text = address

        mapsImg.onClick {

            if (latitude.isEmpty() && longtitude.isEmpty()) {
                onShowMaps(latitude, longtitude)
            } else {
                onDialogInfo("Yah, Maps tidak tersedia", "")
            }

        }

        Glide.with(this)
            .load(AppNetwork.URL_IMAGE + "fasilitas/${foto}")
            .thumbnail(0.1f)
            .error(R.drawable.ic_broken_image)
            .into(fotoRestoImg)

        backBtn.onClick {
            finish()
        }

    }

    private fun onDialogInfo(title: String, message: String) {
        val dialog = DialogInfo(
            this@FacilitiesDetail,
            title,
            message
        )
        dialog.setCancelable(false)
        dialog.show()

    }

    private fun onShowMaps(lat: String, lon: String) {
        val gmmIntentUri = Uri.parse("geo:0,0?q=$lat,$lon")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }
    }

}