package com.kempoamikom.historyapp.video

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.kempoamikom.historyapp.R
import com.kempoamikom.historyapp.dialog.DialogInfo
import kotlinx.android.synthetic.main.activity_video.*


class Video : YouTubeBaseActivity() {

    val API_KEY = "AIzaSyBqZaL1C0u8KkRhYrMJAtirufDhslQolOw"
    private val URL_YOUTUBE = "https://www.youtube.com/watch?v="
    var URL: String = ""
    var KEY: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        KEY = intent.getStringExtra("key")

        initUI()

    }

    private fun initUI() {

        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
                Configuration.ORIENTATION_PORTRAIT
        }

        youtubeView.initialize(API_KEY, object: YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                youTubePlayer: YouTubePlayer?,
                p2: Boolean
            ) {
//                val data = URL.replace(URL_YOUTUBE, "")
                isLog("data video $KEY")
                youTubePlayer!!.cueVideo(KEY)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                onDialogInfo("Gagal memutar video", "Yah, video gagal di putar, silahkan coba nanti ya")
            }
        })

    }

    private fun isLog(msg: String) {
        Log.e("Video", msg)
    }

    private fun onDialogInfo(title: String, message: String) {
        val dialog = DialogInfo(
            this@Video,
            title,
            message
        )
        dialog.setCancelable(false)
        dialog.show()

    }


}