package com.example.quizzydemo.ui.quizz

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzydemo.BaseViewModel
import com.example.quizzydemo.Network.QuestionApi
import com.example.quizzydemo.injection.module.NetworkModule
import com.example.quizzydemo.model.question.Questions
import com.example.quizzydemo.model.question.QuestionsDao
import com.example.quizzydemo.model.user.User
import com.example.quizzydemo.ui.result.ResultViewModel
import com.example.quizzydemo.utils.BASE_URL
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Inject

class QuizzViewModel(private val questionDao: QuestionsDao) : BaseViewModel() {
    @Inject
    lateinit var QuestionApi: QuestionApi


    private lateinit var subscription: Disposable

    init {
        loadQuestions()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadQuestions() {
        subscription = Observable.fromCallable { questionDao.allQuestion }
            .concatMap { dbQuestionList ->
                if (dbQuestionList.isEmpty())
                    QuestionApi.getQuestion().concatMap { apiQuestionList ->
                        questionDao.insertAll(*apiQuestionList.toTypedArray())
                        Observable.just(apiQuestionList)
                    }
                else
                    Observable.just(dbQuestionList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveQuestionListStart() }
            .doOnTerminate { onRetrieveQuestionListFinish() }
            .subscribe(
                { result -> onRetrieveQuestionListSuccess(result) },
                { onRetrieveQuestionListError() }
            )
    }


    private fun onRetrieveQuestionListStart() {
        var s: String
    }

    private fun onRetrieveQuestionListFinish() {
        var s: String
    }

    private fun onRetrieveQuestionListSuccess(QuestionList: List<Questions>) {
        getRand5Question()
    }

    internal fun getRand5Question(): LiveData<List<Questions>> {
        var questions: LiveData<List<Questions>>
        questions = questionDao.rand5Question
        return questions
    }

    private fun onRetrieveQuestionListError() {
        var s: String
    }

}