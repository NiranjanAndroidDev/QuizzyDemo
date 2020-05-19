package com.example.quizzydemo.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var name: String,
    var age: String,
    var gender: String

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var score: Int = 0
}