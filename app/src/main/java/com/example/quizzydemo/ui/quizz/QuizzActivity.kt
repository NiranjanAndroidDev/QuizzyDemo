package com.example.quizzydemo.ui.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quizzydemo.R
import com.example.quizzydemo.databinding.ActivityQuizzBinding
import com.example.quizzydemo.injection.ViewModelFactory
import com.example.quizzydemo.model.database.AppDatabase
import com.example.quizzydemo.model.question.Questions
import com.example.quizzydemo.model.user.UserRepository
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.quizzydemo.ui.result.ResultActivity
import com.google.android.material.snackbar.Snackbar


class QuizzActivity : AppCompatActivity() {
    var remainingTimer: Int = 10
    var questionIndex: Int = 0

    var score: Int = 0
    private lateinit var viewModel: QuizzViewModel
    lateinit var activityBinding: ActivityQuizzBinding
    lateinit var mQuestions: List<Questions>
    lateinit var userRepository: UserRepository
    private var userName: String? = null

    private var errorToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_quizz)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this))
            .get(QuizzViewModel::class.java)
        userName = intent.getStringExtra("userName");
        activityBinding.textViewHeading.setText("Welcome " + userName)


        viewModel.getRand5Question().observe(this, Observer { result ->
            setView(result)
        })

    }

    private fun setView(question: List<Questions>) {
        mQuestions = question

        if (mQuestions.isNotEmpty())
            setBinding()
    }

    private fun setBinding() {
        if (questionIndex > 0) {
            activityBinding.rgOptions.setOnCheckedChangeListener(null)
            val radioButtonID = activityBinding.rgOptions.checkedRadioButtonId
            val radioButton: RadioButton = activityBinding.rgOptions.findViewById(radioButtonID)
            radioButton.isChecked = false
        }

        if (questionIndex < mQuestions.size) {
            activityBinding.question.setText("" + (questionIndex + 1) + ". " + mQuestions[questionIndex].question)

            var options = mQuestions[questionIndex].options

            activityBinding.A.setText(options[0])

            activityBinding.B.setText(options[1])
            activityBinding.C.setText(options[2])
            activityBinding.D.setText(options[3])
            activityBinding.timer.setText("Remaining time: " + (remainingTimer));

            val timer = object : CountDownTimer((1000 * remainingTimer).toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {

                    remainingTimer--
                    activityBinding.timer.setText("Remaining time: " + (millisUntilFinished / 1000))
                }

                override fun onFinish() {

                    questionIndex++

                    setBinding()
                }
            }
            timer.start()
            activityBinding.rgOptions.setOnCheckedChangeListener { group, checkedId ->
                val radioButtonID = activityBinding.rgOptions.checkedRadioButtonId
                val radioButton: RadioButton = activityBinding.rgOptions.findViewById(radioButtonID)
                if (radioButton.tag.toString().equals(mQuestions[questionIndex].answer)) {

                    timer.cancel()
                    score++
                    questionIndex++

                    remainingTimer += 10

                    setBinding()

                } else {
                    timer.cancel()
                    questionIndex++

                    remainingTimer = 10

                    setBinding()

                }
            }

        } else {
            userRepository = UserRepository.getInstance(AppDatabase.getAppDatabase(this).userDao())
            userName?.let { userRepository.update(score, it) }
            showToast("Your score is " + score)
            val intent = Intent(this, ResultActivity::class.java)

            startActivity(intent)
            finish()

        }

    }

    private fun showToast(errorMessage: String) {
        errorToast =
            Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT)

        errorToast?.show()
    }
}
