package com.example.quizzydemo.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.quizzydemo.model.question.Converters
import com.example.quizzydemo.model.question.Questions
import com.example.quizzydemo.model.question.QuestionsDao
import com.example.quizzydemo.model.user.User
import com.example.quizzydemo.model.user.UserDao

@Database(entities = arrayOf(Questions::class, User::class), version = 10, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionsDao
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mydb"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

            }

            return INSTANCE!!

        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}