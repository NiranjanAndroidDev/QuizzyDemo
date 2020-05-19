package com.example.quizzydemo.injection.component

import com.example.quizzydemo.injection.module.NetworkModule
import com.example.quizzydemo.ui.quizz.QuestionViewModel
import com.example.quizzydemo.ui.quizz.QuizzViewModel

import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified QuestionListViewModel.
     * @param QuestionListViewModel QuestionListViewModel in which to inject the dependencies
     */
    fun inject(quizziewModel: QuizzViewModel)
    /**
     * Injects required dependencies into the specified QuestionViewModel.
     * @param QuestionViewModel QuestionViewModel in which to inject the dependencies
     */
    fun inject(quizzQustionViewModel: QuestionViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}