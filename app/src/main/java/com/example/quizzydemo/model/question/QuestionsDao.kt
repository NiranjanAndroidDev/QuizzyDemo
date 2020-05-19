package com.example.quizzydemo.model.question

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuestionsDao {

    @get:Query("SELECT  * FROM questions ORDER BY RANDOM() LIMIT 5")
    val rand5Question: LiveData<List<Questions>>

    @get:Query("SELECT * FROM questions")
    val allQuestion: List<Questions>

    @Insert
    fun insertAll(vararg users: Questions)
}