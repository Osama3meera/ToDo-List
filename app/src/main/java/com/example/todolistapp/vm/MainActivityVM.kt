package com.example.todolistapp.vm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistapp.Repo
import com.example.todolistapp.model.ListResponse

class MainActivityVM : ViewModel() {

    fun getList() : MutableLiveData<ArrayList<ListResponse>>{

        val repo = Repo()

        return repo.getList()

    }

    fun insetList(name: String, priority: Int, context: Context?) : MutableLiveData<ArrayList<ListResponse>> {
        val repo = Repo()

        return repo.insertList(name,priority, context!!)
    }
}