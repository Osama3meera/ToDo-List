package com.example.todolistapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.`interface`.DeleteListInterface
import com.example.todolistapp.model.ListResponse

class ListAdapter() :
    RecyclerView.Adapter<ListAdapter.ViewItem>(), DeleteListInterface {

    lateinit var context: Context
    lateinit var items: ArrayList<ListResponse>
    lateinit var deleteListInterface: DeleteListInterface

    constructor(context: Context, items: ArrayList<ListResponse>,deleteListInterface: DeleteListInterface) : this() {
        this.context = context
        this.items = items
        this.deleteListInterface =deleteListInterface
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewItem {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_task, parent, false)

        return ViewItem(itemView)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewItem, position: Int) {

        holder.name.text = items[position].name

        if (items[position].priority > 2) {
            holder.boxLow.visibility = View.VISIBLE
            holder.boxMedium.visibility = View.GONE
            holder.boxHigh.visibility = View.GONE
        } else if (items[position].priority == 2) {
            holder.boxLow.visibility = View.GONE
            holder.boxMedium.visibility = View.GONE
            holder.boxHigh.visibility = View.VISIBLE
        } else if (items[position].priority == 1) {
            holder.boxLow.visibility = View.GONE
            holder.boxMedium.visibility = View.VISIBLE
            holder.boxHigh.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
           deleteListInterface.delete(items[position].id)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.taskNameHigh)
        var boxHigh: TextView = itemView.findViewById(R.id.boxHigh)
        var boxMedium: TextView = itemView.findViewById(R.id.boxMedium)
        var boxLow: TextView = itemView.findViewById(R.id.boxLow)
        var res = itemView.resources
        var highBoxStatus = false
        var mediumBoxStatus = false
        var lowBoxStatus = false

    }

    override fun delete(id: String) {
    }

}