package com.example.quizzydemo.ui.quizz

import androidx.lifecycle.MutableLiveData
import com.example.quizzydemo.BaseViewModel
import com.example.quizzydemo.model.question.Questions

class QuestionViewModel : BaseViewModel() {

    private val questionHeading = MutableLiveData<String>()

    fun bind(question: Questions) {
        questionHeading.value = question.question
    }


}
