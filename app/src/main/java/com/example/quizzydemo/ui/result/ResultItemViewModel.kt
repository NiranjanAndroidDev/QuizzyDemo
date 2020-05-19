package com.example.quizzydemo.ui.result

import androidx.lifecycle.MutableLiveData
import com.example.quizzydemo.BaseViewModel
import com.example.quizzydemo.model.user.User

class ResultItemViewModel : BaseViewModel() {

    private val name = MutableLiveData<String>()
    private val age = MutableLiveData<String>()
    private val score = MutableLiveData<Int>()
    private val gender = MutableLiveData<String>()

    fun bind(user: User) {
        name.value = user.name
        age.value = user.age
        score.value = user.score
        gender.value = user.gender
    }

    fun getName(): MutableLiveData<String> {
        return name
    }

    fun getAge(): MutableLiveData<String> {
        return age
    }
    fun getGender(): MutableLiveData<String> {
        return gender
    }

    fun getScore(): MutableLiveData<Int> {
        return score
    }
}
