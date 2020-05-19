package com.example.quizzydemo.model.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserRepository private constructor(private val userAccountDao: UserDao) {
    val userAccountLiveData: LiveData<List<User>> = userAccountDao.top5

    fun insertUser(username: String, age: String, gender: String) {
        val account = User(username, age, gender)
        userAccountDao.insert(account)
    }

    fun update(score: Int, name: String) {

        userAccountDao.updateUserScore(score, name)
    }


    companion object {
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao): UserRepository {
            if (instance == null) {
                instance = UserRepository(userDao)
            }
            return instance!!
        }
    }
}
