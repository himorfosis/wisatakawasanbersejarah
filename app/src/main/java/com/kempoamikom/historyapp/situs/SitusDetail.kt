package com.kempoamikom.historyapp.situs

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.kempoamikom.historyapp.R
import com.kempoamikom.historyapp.dialog.DialogInfo
import com.kempoamikom.historyapp.network.AppNetwork
import com.kempoamikom.historyapp.video.Video
import kotlinx.android.synthetic.main.activity_situs_detail.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class SitusDetail : AppCompatActivity() {

    private val URL_YOUTUBE = "https://www.youtube.com/watch?v="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_situs_detail)

        initUI()

    }

    private fun initUI() {

        val id = intent.getStringExtra("id")
        val address = intent.getStringExtra("address")
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val foto = intent.getStringExtra("foto")
        val panorama = intent.getStringExtra("panorama")
        val latitude = intent.getStringExtra("latitude")
        val longtitude = intent.getStringExtra("longtitude")
        val video = intent.getStringExtra("video")

        titleTv.text = name
        desctiptionTv.text = description
        addressTv.text = address

        if (video.isNotEmpty()) {
            youtubeImg.visibility = View.VISIBLE
        } else {
            youtubeImg.visibility = View.GONE
        }

        if (panorama.isNotEmpty()) {
            panoramaStatus.visibility = View.VISIBLE
        } else {
            panoramaStatus.visibility = View.GONE
        }

        mapsImg.onClick {
            if (latitude.isNotEmpty() && longtitude.isNotEmpty()) {
                onShowMaps(latitude, longtitude)
            } else {
                onDialogInfo("Yah, Maps tidak tersedia", "")
            }
        }

        youtubeImg.onClick {
            if (video.isEmpty()) {
                onDialogInfo("Yah, Video tidak tersedia", "")
            } else {

                val url = video.replace(URL_YOUTUBE, "")
                val maxData = url.length
                var valueData = ""

                for (x in 0 until maxData) {
                    val it = url[x]
                    if (it.toString() == "&") {
                        valueData = url.substring(0, x)
                        isLog("value data $valueData")
                        break
                    }
                }

                isLog("data video : $url")
                val data = if(valueData.isEmpty()) url else valueData
                isLog("value video : $url")

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$data"))
                intent.putExtra("VIDEO_ID", data)
                startActivity(intent)

//                startActivity(
//                    intentFor<Video>(
//                        "key" to data
//                    )
//                )
            }
        }

        fotoRestoImg.onClick {

            if (panorama.isNotEmpty()) {
                startActivity(intentFor<PanoramaView>(
                        "panorama" to panorama
                    )
                )
            } else {
                onDialogInfo("Yah, Foto Panorama tidak ada", "Situs ini tidak memiliki foto panorama atau 360 derajat")
            }

        }

        isLog("foto : $foto")

        Glide.with(this)
            .load(AppNetwork.URL_IMAGE + "situs/${foto}")
            .thumbnail(0.1f)
            .error(R.drawable.ic_broken_image)
            .into(fotoRestoImg)

        backBtn.onClick {
            finish()
        }

    }

    private fun onDialogInfo(title: String, message: String) {
        val dialog = DialogInfo(
            this@SitusDetail,
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

    private fun isLog(msg: String) {
        Log.e("SitusDetail", msg)
    }

}