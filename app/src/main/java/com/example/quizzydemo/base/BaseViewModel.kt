package com.example.quizzydemo

import androidx.lifecycle.ViewModel
import com.example.quizzydemo.injection.component.DaggerViewModelInjector
import com.example.quizzydemo.injection.component.ViewModelInjector
import com.example.quizzydemo.injection.module.NetworkModule
import com.example.quizzydemo.ui.quizz.QuestionViewModel
import com.example.quizzydemo.ui.quizz.QuizzViewModel


abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is QuizzViewModel -> injector.inject(this)
            is QuestionViewModel -> injector.inject(this)
        }
    }
}