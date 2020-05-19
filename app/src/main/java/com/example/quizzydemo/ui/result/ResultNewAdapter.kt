package com.example.quizzydemo.ui.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzydemo.R
import com.example.quizzydemo.databinding.ItemNewResultBinding
import com.example.quizzydemo.model.user.User

class ResultAdapter: RecyclerView.Adapter<ResultAdapter.ViewHolder>() {
    private lateinit var userList:List<User>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAdapter.ViewHolder {
        val binding: ItemNewResultBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_new_result, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultAdapter.ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return if(::userList.isInitialized) userList.size else 0
    }

    fun updateQuestionList(QuestionList:List<User>){
        this.userList = QuestionList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemNewResultBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = ResultItemViewModel()

        fun bind(result:User){
            viewModel.bind(result)
            binding.item = viewModel
        }
    }
}