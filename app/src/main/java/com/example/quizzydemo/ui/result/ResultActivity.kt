package com.example.quizzydemo.ui.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizzydemo.R
import com.example.quizzydemo.databinding.ActivityResultBinding
import com.google.android.material.snackbar.Snackbar

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var viewModel: ResultViewModel
    private lateinit var resultAdapter :ResultAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        binding.userList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProviders.of(this, ResultViewModel.Factory(this)).get(ResultViewModel::class.java)
        resultAdapter=ResultAdapter()

        viewModel.getUserList().observe(this, Observer {
            result -> resultAdapter.updateQuestionList(result)
        })

        binding.userList.adapter=resultAdapter
    }
}
