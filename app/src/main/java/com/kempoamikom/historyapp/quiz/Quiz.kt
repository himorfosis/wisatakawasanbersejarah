package com.kempoamikom.historyapp.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kempoamikom.historyapp.Homepage
import com.kempoamikom.historyapp.R
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.toolbar_details.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class Quiz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        initUI()

    }

    private fun initUI() {

        closeBtn.onClick {
            startActivity(intentFor<Homepage>())
        }

        essayCard.onClick {
            startQuiz(getString(R.string.easy), "essay")

        }

        multipleChoiceCard.onClick {
            startQuiz(getString(R.string.medium),  "multiple choice")
        }

    }

    private fun startQuiz(level: String, type: String) {
        startActivity(
            intentFor<QuizStart>(
                "level" to level,
                "type" to type
            )
        )
    }

    override fun onBackPressed() {
        startActivity(intentFor<Homepage>())
    }

}