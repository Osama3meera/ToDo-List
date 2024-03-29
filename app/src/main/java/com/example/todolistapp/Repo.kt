package com.example.todolistapp

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.todolistapp.model.ListResponse
import com.example.todolistapp.retrofit.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repo {


    fun getList(): MutableLiveData<ArrayList<ListResponse>> {

        val getListResponse = MutableLiveData<ArrayList<ListResponse>>()
        RestClient.getService().getList().enqueue(object : Callback<ArrayList<ListResponse>> {
            override fun onResponse(call: Call<ArrayList<ListResponse>>, response: Response<ArrayList<ListResponse>>) {

                if (response.isSuccessful) {
                    getListResponse.value = response.body()
                }
            }

            override fun onFailure(call: Call<ArrayList<ListResponse>>, t: Throwable) {
                Log.d("failed", t.message.toString())
            }

        })

        return getListResponse
    }

    fun insertList(name :String,priority :Int,context: Context):MutableLiveData<ArrayList<ListResponse>>{

        val insertListResponse = MutableLiveData<ArrayList<ListResponse>>()
        RestClient.getService().insertList(name, priority).enqueue(object : Callback<ArrayList<ListResponse>>{
            override fun onResponse(
                call: Call<ArrayList<ListResponse>>,
                response: Response<ArrayList<ListResponse>>
            ) {
                if (response.isSuccessful) {
               Toast.makeText(context,"Task inserted Successfully!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<ListResponse>>, t: Throwable) {
                Log.d("failed", t.message.toString())
            }

        })
        return insertListResponse
    }

    fun deleteList(id :String):MutableLiveData<ListResponse>{
        val deleteListResponse = MutableLiveData<ListResponse>()
        RestClient.getService().deleteList(id).enqueue(object : Callback<ListResponse>{
            override fun onResponse(call: Call<ListResponse>, response: Response<ListResponse>) {

                Log.w(id, "Deleted id is {}")
                deleteListResponse.value = response.body()

            }

            override fun onFailure(call: Call<ListResponse>, t: Throwable) {

            }

        })
        return deleteListResponse
    }

    fun updateList(listResponse: ListResponse) : MutableLiveData<ListResponse>{
        val updateListResponse = MutableLiveData<ListResponse>()
        RestClient.getService().updateList(listResponse.id,listResponse).enqueue(object : Callback<ListResponse>{
            override fun onResponse(call: Call<ListResponse>, response: Response<ListResponse>) {

            }

            override fun onFailure(call: Call<ListResponse>, t: Throwable) {

            }

        })
        return updateListResponse
    }
}