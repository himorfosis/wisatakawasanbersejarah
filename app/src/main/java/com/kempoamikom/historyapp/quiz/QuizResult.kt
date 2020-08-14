package com.kempoamikom.historyapp.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kempoamikom.historyapp.Homepage
import com.kempoamikom.historyapp.R
import kotlinx.android.synthetic.main.activity_quiz_result.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class QuizResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)

        initUI()

    }

    private fun initUI() {

        val totalScore = intent.getIntExtra("score", 0)
        isLog("score total $totalScore")

        totalScoreTv.text = totalScore.toString()

        ulangCard.onClick {
            startActivity(intentFor<Quiz>())

        }

        berandaCard.onClick {
            startActivity(intentFor<Homepage>())

        }

        quizCard.onClick {
            startActivity(intentFor<Quiz>())
        }


    }

    override fun onBackPressed() {
        // no action
    }

    private fun isLog(msg: String) {
        Log.e("QuizResult", msg)
    }

}