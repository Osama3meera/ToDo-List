package com.example.todolistapp.`interface`

interface DeleteListInterface {

    fun delete(id: String)
    fun notifyItemRemoved(isItemRemoved: Boolean)
    fun notifyEdit(isItemEdited : Boolean,id: String)
}