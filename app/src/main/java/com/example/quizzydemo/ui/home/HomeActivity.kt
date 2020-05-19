package com.example.quizzydemo.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.quizzydemo.R
import com.example.quizzydemo.ui.register.RegistrationActivity
import com.example.quizzydemo.ui.result.ResultActivity

class HomeActivity : AppCompatActivity() {
    private var signUpButton: Button? = null
    private var topResult: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        signUpButton = findViewById<View>(R.id.signup) as Button
        topResult = findViewById<View>(R.id.top_result) as Button


        signUpButton!!.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)

            startActivity(intent)
        }
        topResult!!.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)

            startActivity(intent)
        }
    }
}
