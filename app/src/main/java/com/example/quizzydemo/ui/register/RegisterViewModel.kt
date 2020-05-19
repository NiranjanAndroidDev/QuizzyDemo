package com.example.quizzydemo.ui.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzydemo.model.database.AppDatabase
import com.example.quizzydemo.model.user.UserRepository

class RegisterViewModel(context: Context) : ViewModel() {

    private val userRepository: UserRepository

    init {
        userRepository = UserRepository.getInstance(AppDatabase.getAppDatabase(context).userDao())
    }

    internal fun createUser(username: String, age: String, gender: String) {
        userRepository.insertUser(username, age, gender)
    }

    class Factory internal constructor(ctxt: Context) : ViewModelProvider.Factory {
        private val ctxt: Context

        init {
            this.ctxt = ctxt.applicationContext
        }

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegisterViewModel(ctxt) as T
        }
    }

}