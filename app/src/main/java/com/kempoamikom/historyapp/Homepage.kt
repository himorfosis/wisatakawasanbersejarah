package com.kempoamikom.historyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kempoamikom.historyapp.about.AboutActivity
import com.kempoamikom.historyapp.dialog.DialogChoice
import com.kempoamikom.historyapp.quiz.Quiz
import com.kempoamikom.historyapp.facilities.Facilities
import com.kempoamikom.historyapp.situs.Situs
import com.kempoamikom.historyapp.sumber.SumberActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initUI()

    }

    private fun initUI() {

        siteCard.onClick {
            startActivity(intentFor<Situs>())

        }

        ebookCard.onClick {
            startActivity(intentFor<SumberActivity>())
        }

        restaurantCard.onClick {
            startActivity(intentFor<Facilities>())
        }

        quizCard.onClick {
            startActivity(intentFor<Quiz>())
        }

        aboutBtn.onClick {
            startActivity(intentFor<AboutActivity>())
        }

    }

    private fun onDialogCloseApp() {

        val dialog = DialogChoice(
            this,
            "Keluar Aplikasi",
            "Apakah Anda yakin akan keluar dari aplikasi ini?"
        )

        dialog.setOnclick(object : DialogChoice.OnClickItem {
            override fun onItemClicked(data: String) {
                if (data == dialog.ACCEPT) {
                    finishAffinity()
                } else {
                    dialog.dismiss()
                }
            }
        })

        dialog.show()

    }

    override fun onBackPressed() {
        onDialogCloseApp()
    }

}