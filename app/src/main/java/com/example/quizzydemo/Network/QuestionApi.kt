package com.example.quizzydemo.Network

import io.reactivex.Observable
import com.example.quizzydemo.model.question.Questions
import retrofit2.http.GET


interface QuestionApi {

    @GET("app_test_assignment_quiz.json")
    fun getQuestion(): Observable<List<Questions>>
}