package com.example.todolistapp.ui


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.`interface`.DeleteListInterface
import com.example.todolistapp.adapter.ListAdapter
import com.example.todolistapp.databinding.ActivityMainBinding
import com.example.todolistapp.vm.MainActivityVM

class MainActivity : AppCompatActivity(),DeleteListInterface {





    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vm = ViewModelProvider(this).get(MainActivityVM::class.java)

        binding.addFloatingBtn.setOnClickListener {
            CustomDialog().show(supportFragmentManager, "Custom Fragment")
        }

        vm.getList().observe(this, Observer {
            val listOfTasks = it
            binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val adapter = ListAdapter(this, listOfTasks,this)
            binding.recyclerView.adapter = adapter
        })
    }
    override fun delete(id: String) {
        val vm = ViewModelProvider(this).get(MainActivityVM::class.java)
        vm.deleteList(id)
    }

}