package com.example.myquizapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class ResultActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var tv_name : TextView? = null
        var tv_score : TextView ? = null
        var btn_Finish : Button ? = null

        tv_name = findViewById(R.id.tv_name)
        tv_score = findViewById(R.id.tv_score)
        btn_Finish = findViewById(R.id.btn_finish)

        tv_name.text =  intent.getStringExtra(Constants.USER_NAME)

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctQuestions = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)


        tv_score.text = "Your total score is $correctQuestions out of $totalQuestions"

        btn_Finish.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}