package com.kempoamikom.historyapp.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.kempoamikom.historyapp.R
import com.kempoamikom.historyapp.data.DataSample
import com.kempoamikom.historyapp.entity.QuizEntity
import com.kempoamikom.historyapp.model.QuizModel
import com.kempoamikom.historyapp.network.AppNetwork
import com.kempoamikom.historyapp.network.service.ApiService
import com.kempoamikom.historyapp.situs.SitusAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_quiz_start.*
import kotlinx.android.synthetic.main.layout_status_failure.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class QuizStart : AppCompatActivity() {

    private var levelSelected: String = ""
    private var typeSelected: String = ""
    private var SCORE_TOTAL: Int = 0
    private var SCORE_GET : Int = 0
    private var QUESTION_NUMBER: Int = 1

    private var listData: MutableList<QuizModel> = ArrayList()

    val service = AppNetwork.buildService(ApiService::class.java)
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_start)

        levelSelected = intent.getStringExtra("level")
        typeSelected = intent.getStringExtra("type")
        initUI()
        fetchData()

    }

    private fun initUI() {

        closeQuizBtn.onClick {
            startActivity(intentFor<Quiz>())
        }

        answerBtn.onClick {

            val answerEssay = essayAnswer.text.toString()
            checkSelectedAnswer(answerEssay)
            essayAnswer.text.clear()
            setQuestionSelected()

        }

        itemACard.onClick {
            itemACard.setBackgroundResource(R.drawable.btn_bg_green)
            selectedAnswer(answerATv.text.toString())
        }

        itemBCard.onClick {
            itemBCard.setBackgroundResource(R.drawable.btn_bg_green)
            selectedAnswer(answerBTv.text.toString())
        }

        itemCCard.onClick {
            itemCCard.setBackgroundResource(R.drawable.btn_bg_green)
            selectedAnswer(answerCTv.text.toString())
        }

        itemDCard.onClick {
            itemDCard.setBackgroundResource(R.drawable.btn_bg_green)
            selectedAnswer(answerDTv.text.toString())
        }

    }

    private fun selectedAnswer(answer: String) {

        Handler().postDelayed(object : Thread() {
            override fun run() {
                itemACard.setBackgroundResource(R.drawable.btn_white)
                itemBCard.setBackgroundResource(R.drawable.btn_white)
                itemCCard.setBackgroundResource(R.drawable.btn_white)
                itemDCard.setBackgroundResource(R.drawable.btn_white)
                checkSelectedAnswer(answer)
                isLog("QUESTION_NUMBER : $QUESTION_NUMBER")
                setQuestionSelected()
            }
        }, 1000)

    }

    private fun onFetchSuccess() {

        quizLayout.visibility = View.VISIBLE
        frame_layout.visibility = View.VISIBLE
        closeQuizBtn.visibility = View.VISIBLE

        if (typeSelected == "multiple choice") {
            multipleLayout.visibility = View.VISIBLE
        } else {
            essayLayout.visibility = View.VISIBLE
        }

    }

    private fun setQuestionSelected() {

        isLog("QUESTION_NUMBER : $QUESTION_NUMBER")

        if (listData.isNotEmpty()) {

            quizLayout.visibility = View.VISIBLE
            for (pos in 0 until listData.size) {

                val position = pos + 1
                val it = listData[pos]
                if (position == QUESTION_NUMBER) {

                    numberQuestionTv.text = "$QUESTION_NUMBER/${listData.size}"
                    questionTv.text = it.question

                    if (typeSelected == "multiple choice") {
                        answerATv.text = it.answer_one
                        answerBTv.text = it.answer_two
                        answerCTv.text = it.answer_three
                        answerDTv.text = it.answer_four
                    }

                    isLog("pos : $position")

                    break
                }
            }

        } else {
            onFailedDataEmpty()
        }

    }

    private fun checkSelectedAnswer(answerSelected: String) {

        for (pos in 0 until listData.size) {

            val position = pos + 1
            val it = listData[pos]
            if (position == QUESTION_NUMBER) {

                if (answerSelected.toLowerCase() == it.answer_true.toLowerCase()) {
                    isLog("true")
                    SCORE_TOTAL = SCORE_TOTAL + SCORE_GET
                }

                isLog("total score : $SCORE_TOTAL")
                if (QUESTION_NUMBER >= listData.size) {
                    startActivity(
                        intentFor<QuizResult>(
                            "score" to SCORE_TOTAL
                        )
                    )
                }

                QUESTION_NUMBER += 1

                break
            }
        }
    }

    private fun fetchData() {

        service.fetchQuiz()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({
                isLog("Success")
                if (it.isNotEmpty()) {
                    isLog("Success Fetch")
                    // successs
                    it.forEach {
                        if (it.type == typeSelected) {
                            listData.add(it)
                        }
                    }
                    SCORE_GET = (1 * 100 ) / listData.size
                    isLog("score get : $SCORE_GET")
                    onFetchSuccess()
                    setQuestionSelected()

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

        frame_layout.visibility = View.VISIBLE
        quizLayout.visibility = View.GONE
        closeQuizBtn.visibility = View.VISIBLE

        status_error_tv.visibility = View.VISIBLE
        status_error_message_tv.visibility = View.VISIBLE

        status_error_tv.text = getString(R.string.failed_connection)
        status_error_message_tv.text = getString(R.string.failed_connection_message)
    }

    private fun onFailedDataEmpty() {

        closeQuizBtn.visibility = View.VISIBLE
        frame_layout.visibility = View.VISIBLE
        quizLayout.visibility = View.GONE
        closeQuizBtn.visibility = View.VISIBLE
        status_error_tv.visibility = View.VISIBLE

        status_error_tv.text = getString(R.string.data_not_available)
    }

    override fun onBackPressed() {
        // no action
    }

    private fun isLog(msg: String) {
        Log.e("QuizStart", msg)
    }

}