package com.example.todolistapp.ui


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.`interface`.DeleteListInterface
import com.example.todolistapp.adapter.ListAdapter
import com.example.todolistapp.databinding.ActivityMainBinding
import com.example.todolistapp.vm.MainActivityVM

class MainActivity : AppCompatActivity(), DeleteListInterface {

    lateinit var binding: ActivityMainBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vm = ViewModelProvider(this).get(MainActivityVM::class.java)

        binding.addFloatingBtn.setOnClickListener {
            CustomDialog("Add","").show(supportFragmentManager, "Add Fragment")
        }

        vm.getList().observe(this, {
            val listOfTasks = it
            binding.recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val adapter = ListAdapter(this, listOfTasks, this)
            binding.recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun delete(id: String) {
        val vm = ViewModelProvider(this).get(MainActivityVM::class.java)
        vm.deleteList(id)
        vm.getList().observe(this, {
            val listOfTasks = it
            binding.recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val adapter = ListAdapter(this, listOfTasks, this)
            binding.recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

    override fun notifyItemRemoved(isItemRemoved: Boolean) {
        if (isItemRemoved)
            binding.removeFloatingBtn.visibility = View.VISIBLE
        else
            binding.removeFloatingBtn.visibility = View.GONE
    }

    override fun notifyEdit(isItemEdited: Boolean, id: String) {
        CustomDialog("Edit",id).show(supportFragmentManager, "Edit Fragment")

    }
}