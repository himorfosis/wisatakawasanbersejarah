package com.kempoamikom.historyapp.situs

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.gjiazhe.panoramaimageview.GyroscopeObserver
import com.kempoamikom.historyapp.R
import com.kempoamikom.historyapp.dialog.DialogInfo
import com.kempoamikom.historyapp.network.AppNetwork
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_panorama_view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import pl.rjuszczyk.panorama.viewer.PanoramaGLSurfaceView


class PanoramaView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panorama_view)

        initUI()

    }

    private fun initUI() {

        closeBtn.onClick {
            finish()
        }

        val panorama = intent.getStringExtra("panorama")
        isLog(AppNetwork.URL_IMAGE + "situs/$panorama")

        Glide.with(this)
            .asBitmap()
            .load(AppNetwork.URL_IMAGE + "situs/$panorama")
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    panoramaView.setPanoramaBitmap(resource)
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap

                    val dialog= DialogInfo(this@PanoramaView, "Yah, tidak mendukung Panorama",
                        "Maaf smartphone yang anda gunakan belum support untuk menggunakan fitur panorama")
                    dialog.show()
                    dialog.setOnDismissListener {
                        finish()
                    }
                }
            })

    }

    private fun isLog(msg: String) {
        Log.e("Panorama", msg)
    }

    override fun onResume() {
        super.onResume()
        panoramaView.onResume()
    }

    override fun onPause() {
        super.onPause()
        panoramaView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        panoramaView.clearFindViewByIdCache()
    }

}