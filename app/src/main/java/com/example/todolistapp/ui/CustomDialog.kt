package com.example.todolistapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.todolistapp.R
import com.example.todolistapp.vm.MainActivityVM

class CustomDialog : DialogFragment() {
// extends dialog fragment to provide the dialog fragment life cycle

    private lateinit var etTitle: EditText
    private lateinit var tvAdd: TextView
    private lateinit var tvCancel: TextView
    private lateinit var highBox: TextView
    private lateinit var mediumBox: TextView
    private lateinit var lowBox: TextView
    private var boxHighState = false
    private var boxMediumState = false
    private var boxLowState = false
    var priority = 0


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val myInflater = inflater.inflate(R.layout.dialog_box, container, false)

        val vm = ViewModelProvider(this).get(MainActivityVM::class.java)

        etTitle = myInflater.findViewById(R.id.etTitle)
        tvAdd = myInflater.findViewById(R.id.tvAdd)
        tvCancel = myInflater.findViewById(R.id.tvCancel)
        highBox = myInflater.findViewById(R.id.boxHigh)
        mediumBox = myInflater.findViewById(R.id.boxMedium)
        lowBox = myInflater.findViewById(R.id.boxLow)

        tvCancel.setOnClickListener {
            dialog?.dismiss()
        }

        highBox.setOnClickListener {
            if (!boxHighState) {
                highBox.setBackgroundColor(resources.getColor(R.color.red))
                mediumBox.isClickable = false
                lowBox.isClickable = false
                boxHighState = true
                priority = 2
            } else if (boxHighState) {
                highBox.background = resources.getDrawable(R.drawable.red_radius_bg)
                mediumBox.isClickable = true
                lowBox.isClickable = true
                boxHighState = false
                priority = 0
            }
        }

        mediumBox.setOnClickListener {
            if (!boxMediumState) {
                mediumBox.setBackgroundColor(resources.getColor(R.color.blue))
                highBox.isClickable = false
                lowBox.isClickable = false
                boxMediumState = true
                priority = 1
            } else if (boxMediumState) {
                mediumBox.background = resources.getDrawable(R.drawable.blue_radius_bg)
                highBox.isClickable = true
                lowBox.isClickable = true
                boxMediumState = false
                priority = 0
            }
        }

        lowBox.setOnClickListener {
            if (!boxLowState) {
                lowBox.setBackgroundColor(resources.getColor(R.color.green))
                highBox.isClickable = false
                mediumBox.isClickable = false
                boxLowState = true
                priority = 3
            } else if (boxLowState) {
                lowBox.background = resources.getDrawable(R.drawable.green_radius_bg)
                highBox.isClickable = true
                mediumBox.isClickable = true
                boxLowState = false
                priority = 0
            }
        }

        tvAdd.setOnClickListener {

            vm.insetList(etTitle.text.toString(),priority,context)
            vm.getList()
            dialog?.hide()
        }

        return myInflater
    }


}