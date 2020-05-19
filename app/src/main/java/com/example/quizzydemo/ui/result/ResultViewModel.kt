package com.example.quizzydemo.ui.result

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzydemo.BaseViewModel
import com.example.quizzydemo.model.database.AppDatabase
import com.example.quizzydemo.model.user.User
import com.example.quizzydemo.model.user.UserDao
import com.example.quizzydemo.model.user.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ResultViewModel(context: Context) : BaseViewModel() {


    private val userRepository: UserRepository


    val resultAdapter: ResultAdapter = ResultAdapter()

    private lateinit var subscription: Disposable

    init {
        userRepository = UserRepository.getInstance(AppDatabase.getAppDatabase(context).userDao())
        getUserList()

    }

     fun getUserList():LiveData<List<User>> {
       return userRepository.userAccountLiveData
    }

    private fun onRetrieveQuestionListSuccess(QuestionList: List<User>) {
        resultAdapter.updateQuestionList(QuestionList)
    }

    private fun onRetrieveQuestionListError() {
    }

    class Factory internal constructor(ctxt: Context) : ViewModelProvider.Factory {
        private val ctxt: Context

        init {
            this.ctxt = ctxt.applicationContext
        }

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ResultViewModel(ctxt) as T
        }
    }

}