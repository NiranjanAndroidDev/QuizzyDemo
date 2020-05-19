package com.example.quizzydemo.model.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @get:Query("select  * from user order by score desc limit 5")
    val top5: LiveData<List<User>>

    @Insert
    fun insert(account: User)

    @Query("UPDATE user SET score = :scoreNew WHERE name=:nName")
    fun updateUserScore(scoreNew: Int, nName: String)
}