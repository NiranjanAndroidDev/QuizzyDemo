package com.example.quizzydemo.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.ViewModelProviders
import com.example.quizzydemo.R
import com.example.quizzydemo.model.user.User
import com.example.quizzydemo.ui.quizz.QuizzActivity
import com.example.quizzydemo.ui.result.ResultActivity

class RegistrationActivity : AppCompatActivity() {
    private var registerViewModel: RegisterViewModel? = null
    private var usernameText: EditText? = null
    private var age: EditText? = null
    private var gender: Spinner? = null
    private var resgister: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regisration)
        var list_of_gender = arrayOf("male", "female", "others")

        registerViewModel =
            ViewModelProviders.of(this, RegisterViewModel.Factory(applicationContext))
                .get(RegisterViewModel::class.java)

        usernameText = findViewById<View>(R.id.username) as EditText
        age = findViewById<View>(R.id.age) as EditText
        gender = findViewById<View>(R.id.gender) as Spinner
        resgister = findViewById<View>(R.id.signup) as Button

        val array_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_gender)
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        gender!!.adapter = array_adapter

        resgister!!.setOnClickListener {

            assert(usernameText!!.text.isNotEmpty())
            assert(age!!.text.isNotEmpty())
            registerViewModel!!.createUser(
                usernameText!!.text.toString(), age!!.text.toString(),
                gender!!.selectedItem.toString()
            )

            val intent = Intent(this, QuizzActivity::class.java)
            intent.putExtra("userName", usernameText!!.text.toString())
            intent.putExtra("age", age!!.text.toString())
            intent.putExtra("gender", gender!!.selectedItem.toString())

            assert(usernameText!!.text.isNotEmpty())
            assert(age!!.text.isNotEmpty())
            startActivity(intent)

        }
    }
}
