package com.example.todolistapp

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment

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

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var myInflater = inflater.inflate(R.layout.dialog_box, container, false)

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
            } else if (boxHighState) {
                highBox.background = resources.getDrawable(R.drawable.red_radius_bg)
                mediumBox.isClickable = true
                lowBox.isClickable = true
                boxHighState = false
            }
        }

        mediumBox.setOnClickListener {
            if (!boxMediumState) {
                mediumBox.setBackgroundColor(resources.getColor(R.color.blue))
                highBox.isClickable = false
                lowBox.isClickable = false
                boxMediumState = true
            } else if (boxMediumState) {
                mediumBox.background = resources.getDrawable(R.drawable.blue_radius_bg)
                highBox.isClickable = true
                lowBox.isClickable = true
                boxMediumState = false
            }
        }

        lowBox.setOnClickListener {
            if (!boxLowState) {
                lowBox.setBackgroundColor(resources.getColor(R.color.green))
                highBox.isClickable = false
                mediumBox.isClickable = false
                boxLowState = true
            } else if (boxLowState) {
                lowBox.background = resources.getDrawable(R.drawable.green_radius_bg)
                highBox.isClickable = true
                mediumBox.isClickable = true
                boxLowState = false
            }
        }

        return myInflater
    }


}