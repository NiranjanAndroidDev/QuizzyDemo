package com.example.quizzydemo.model.question

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
@TypeConverters(Converters::class)
data class Questions(
    val question: String,
    val answer: String,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "option_values")
    var options: List<String>

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}





