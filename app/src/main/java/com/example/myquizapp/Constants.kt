package com.example.myquizapp

object Constants {

    fun getQuestions() : ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val ques1 = Question(
            1, "What country does this flag belong to?", R.drawable.ic_flag_of_argentina, "Armenia", "Australia", "Argentina", "Austria"
        , 3
        )
        questionsList.add(ques1)
        val ques2 = Question(
            1, "What country does this flag belong to?", R.drawable.ic_flag_of_belgium, "Belgium", "Bulgaria", "Bosnia", "Borrusia Dortmund"
            , 1
        )
        questionsList.add(ques2)

        val ques3 = Question(
            1, "What country does this flag belong to?", R.drawable.ic_flag_of_india, "Greenland", "Ireland", "Ipswich Town", "India"
            , 4
        )
        questionsList.add(ques3)

        val ques4 = Question(
            1, "What country does this flag belong to?", R.drawable.ic_flag_of_brazil, "Germany", "Brazil", "England", "United States"
            , 2
        )
        questionsList.add(ques4)

        return questionsList
    }
}