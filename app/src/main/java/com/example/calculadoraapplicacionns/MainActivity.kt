package com.example.calculadoraapplicacionns

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    private var weight: Int = 170
    private var age: Int = 18
    private var height: Int = 73
    private var male: Boolean = true
    private lateinit var  tvWeight: TextView
    private lateinit var  btnFurtherWeight: Button
    private lateinit var btnLessWeight: Button
    private lateinit var btnFurtherAge: Button
    private lateinit var btnLessAge: Button
    private lateinit var tvAge: TextView
    private lateinit var cvMale: MaterialCardView
    private lateinit var cvFemale: MaterialCardView
    private lateinit var tvHeight: TextView
    private lateinit var sbHeight: SeekBar
    private lateinit var cvCalculate: MaterialCardView
    private fun limitless(num: Int, type: String): Int{
        return when(type){
            "age" -> {
                if(num < 1) 1 else num
            }
            "weight" -> {
                if(num < 20) 20 else num
            }
            "height" -> {
                if(num < 50) 50  else num
            }
            else -> num
        }
    }
    private fun updateColorGender(){
        val colorNoSelection = ContextCompat.getColor(this, R.color.cardBackground)
        val colorSelection =  ContextCompat.getColor(this, R.color.cardBackgroundSelect)
        if(male){
            cvMale.setCardBackgroundColor(colorSelection)
            cvFemale.setCardBackgroundColor(colorNoSelection)
        }else{
            cvMale.setCardBackgroundColor(colorNoSelection)
            cvFemale.setCardBackgroundColor(colorSelection)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvWeight = findViewById(R.id.textViewWeight)
        btnFurtherWeight = findViewById(R.id.btnFurtherWeight)
        btnLessWeight = findViewById(R.id.btnLessWeight)

        btnFurtherAge = findViewById(R.id.btnFurtherAge)
        btnLessAge = findViewById(R.id.btnLessAge)
        tvAge = findViewById(R.id.textViewAge)

        cvMale = findViewById(R.id.cardMale)
        cvFemale = findViewById(R.id.cardFemale)

        tvHeight = findViewById(R.id.textViewTotalInches)
        sbHeight = findViewById(R.id.seekBarInches)

        cvCalculate = findViewById(R.id.cardViewCalculate)

        btnFurtherWeight.setOnClickListener {
            weight++
            tvWeight.text = weight.toString()
        }
        btnLessWeight.setOnClickListener {
            weight = limitless(weight-1,"weight")
            tvWeight.text = weight.toString()
        }
        btnFurtherAge.setOnClickListener {
            age++
            tvAge.text = age.toString()
        }
        btnLessAge.setOnClickListener{
            age = limitless(age-1,"age")
            tvAge.text = age.toString()
        }
        cvMale.setOnClickListener {
            male = true
            updateColorGender()
        }
        cvFemale.setOnClickListener {
            male = false
            updateColorGender()
        }
        sbHeight.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                height = progress
                tvHeight.text = height.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        cvCalculate.setOnClickListener {
            val heightDouble = height.toDouble()
            val calc = (weight.toDouble() / (heightDouble * heightDouble)) * 703.0
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("result",calc)
            startActivity(intent)
        }
    }
}