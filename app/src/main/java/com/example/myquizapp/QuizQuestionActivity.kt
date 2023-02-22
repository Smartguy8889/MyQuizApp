package com.example.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition : Int = 1
    private var mQuestionList : ArrayList<Question> ? = null
    private var mSelectedOptionPosition : Int = 0
    private var mUsername : String ? = null
    private var mCorrectAnswers : Int = 0

    private var tv_question : TextView ? = null
    private var tv_op1 : TextView ? = null
    private var tv_op2 : TextView ? = null
    private var tv_op3 : TextView ? = null
    private var tv_op4 : TextView ? = null
    private var tv_progress : TextView ? = null
    private var progressbar : ProgressBar ? = null
    private var btn_Submit : Button ? = null
    private var ivImage : ImageView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUsername = intent?.getStringExtra(Constants.USER_NAME)

        tv_question = findViewById(R.id.tv_question)
        tv_op1 = findViewById(R.id.tv_op1)
        tv_op2 = findViewById(R.id.tv_op2)
        tv_op3 = findViewById(R.id.tv_op3)
        tv_op4 = findViewById(R.id.tv_op4)

        tv_progress = findViewById(R.id.tv_Progress)
        progressbar = findViewById(R.id.progress_Bar)
        btn_Submit = findViewById(R.id.btn_Submit)
        ivImage = findViewById(R.id.ivImage)

        tv_op1?.setOnClickListener(this)
        tv_op2?.setOnClickListener(this)
        tv_op3?.setOnClickListener(this)
        tv_op4?.setOnClickListener(this)
        btn_Submit?.setOnClickListener(this)


        mQuestionList = Constants.getQuestions()
        setQuestion()
        defaultOptionsView()
    }

    private fun setQuestion() {
        defaultOptionsView()
        val question: Question = mQuestionList!![mCurrentPosition - 1]
        ivImage?.setImageResource(question.image)
        progressbar?.progress = mCurrentPosition
        tv_progress?.text = "$mCurrentPosition/${progressbar?.max}"
        tv_question?.text = question.question
        tv_op1?.text = question.optionOne
        tv_op2?.text = question.optionTwo
        tv_op3?.text = question.optionThree
        tv_op4?.text = question.optionFour

        if (mCurrentPosition == mQuestionList!!.size)
        {
            btn_Submit?.text = "FINISH"
        }
        else{
            btn_Submit?.text = "SUBMIT"
        }
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tv_op1?.let {
            options.add(0,it)
        }

        tv_op2?.let {
            options.add(1,it)
        }

        tv_op3?.let {
            options.add(2,it)
        }

        tv_op4?.let {
            options.add(3,it)
        }

        for (option in options)
        {
            option.setTextColor(Color.parseColor("#bcb8b1"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(tv:TextView, selectedPosition : Int){
        defaultOptionsView()

        mSelectedOptionPosition = selectedPosition
        tv.setTextColor(Color.parseColor("#ffd60a"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }

    override fun onClick(view: View?) {
        when(view?.id)
        {
            R.id.tv_op1 ->{
                tv_op1?.let {
                    selectedOptionView(it, 1)
                }
            }

            R.id.tv_op2 ->{
                tv_op2?.let {
                    selectedOptionView(it, 2)
                }
            }

            R.id.tv_op3 ->{
                tv_op3?.let {
                    selectedOptionView(it, 3)
                }
            }

            R.id.tv_op4 ->{
                tv_op4?.let {
                    selectedOptionView(it, 4)
                }
            }

            R.id.btn_Submit->{
                if (mSelectedOptionPosition == 0)
                {
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        }
                        else ->{
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUsername)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else{
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if(question!!.correctAnswer != mSelectedOptionPosition)
                    {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else
                    {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition!! == mQuestionList!!.size)
                    {
                        btn_Submit?.text = "FINISH"
                    }
                    else
                    {
                        btn_Submit?.text = "Go To Next Question"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer : Int, drawableView : Int){
        when (answer)
        {
            1 -> {
                tv_op1?.background = ContextCompat.getDrawable(
                    this, drawableView
                )

            }

            2 -> {
                tv_op2?.background = ContextCompat.getDrawable(
                    this, drawableView
                )

            }

            3 -> {
                tv_op3?.background = ContextCompat.getDrawable(
                    this, drawableView
                )

            }

            4 -> {
                tv_op4?.background = ContextCompat.getDrawable(
                    this, drawableView
                )

            }
        }
    }
}


