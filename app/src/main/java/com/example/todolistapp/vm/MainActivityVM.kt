package com.example.todolistapp.vm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistapp.Repo
import com.example.todolistapp.model.ListResponse

class MainActivityVM : ViewModel() {
    private val repo = Repo()

    fun getList() : MutableLiveData<ArrayList<ListResponse>> {

        return repo.getList()

    }

    fun insetList(name: String, priority: Int, context: Context?) : MutableLiveData<ArrayList<ListResponse>> {

        return repo.insertList(name,priority, context!!)
    }

    fun deleteList(id :String) : MutableLiveData<ListResponse>{

        return repo.deleteList(id)
    }

    fun updateList(listResponse: ListResponse) : MutableLiveData<ListResponse>{
        return repo.updateList(listResponse)
    }
}