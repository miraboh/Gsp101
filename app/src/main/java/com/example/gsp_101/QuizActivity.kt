package com.example.gsp_101

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*

var quizs = ArrayList<Quiz>()
var numberOfGoodAnswers: Int = 0
var currentQuizIndex: Int = 0


class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        quizs.add(Quiz("What is the capital of Nigeria","Lagos", "Abuja", "Ibadan", 2))
        quizs.add(Quiz("What is the capital of Cameroon","Bafoussam", "Yaounde", "Kribi", 2))
        quizs.add(Quiz("What is the capital of France","Anger", "Marsseile", "Paris", 3))
        quizs.add(Quiz("What is the capital of England","Chelsea", "London", "Cape-Town", 2))


        showQuestion(quizs[currentQuizIndex])
    }

     fun showQuestion(quiz: Quiz){
        txtQuestions.text = quiz.question
        answer1.text = quiz.answer1
        answer2.text = quiz.answer2
        answer3.text = quiz.answer3
    }

    fun handleAnswer(answerID: Int){
        val quiz = quizs.get(currentQuizIndex)

        if (quiz.isCorrect(answerID)){
            numberOfGoodAnswers++
            Toast.makeText(this, "+1", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "+0", Toast.LENGTH_LONG).show()
        }

        currentQuizIndex++

        if (currentQuizIndex >= quizs.size) { // Partie terminé

            val alert = AlertDialog.Builder(this)
            alert.setTitle("Quiz Finish!")
            alert.setMessage("You got: " + numberOfGoodAnswers + "Good answers")
            alert.setPositiveButton("OK") { dialogInterface: DialogInterface?, i: Int ->
                finish()
            }
            alert.show()
            finish()

        } else { // On continue la partie
            showQuestion(quizs.get(currentQuizIndex))
        }

    }

    fun onClickAnswerOne(view: View){
        handleAnswer(1)

     }

    fun onClickAnswerTwo(view: View){
        handleAnswer(2)

    }

    fun onClickAnswerThree(view: View){
        handleAnswer(3)

    }
}
