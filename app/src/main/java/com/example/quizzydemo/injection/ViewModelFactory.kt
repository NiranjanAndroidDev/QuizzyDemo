package com.example.quizzydemo.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.quizzydemo.model.database.AppDatabase
import com.example.quizzydemo.ui.quizz.QuestionViewModel
import com.example.quizzydemo.ui.quizz.QuizzViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizzViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "mydb").build()
            @Suppress("UNCHECKED_CAST")
            return QuizzViewModel(db.questionDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}