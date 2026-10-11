package com.example.calculadoraapplicacionns

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView

class ResultActivity : AppCompatActivity() {
    private lateinit var tvResultTitle: TextView
    private lateinit var tvResult: TextView
    private lateinit var tvResultSubtitle: TextView
    private lateinit var cvReCalculate: MaterialCardView
    private fun changeText(type: String){
        tvResultTitle.text = type
        tvResultSubtitle.text = type
    }
    private fun updateColorText(type: String ){
        when (type){
            "NORMAL" -> tvResultTitle.setTextColor(ContextCompat.getColor(this,R.color.resultNormal))
            "UNDERWEIGHT" -> tvResultTitle.setTextColor(ContextCompat.getColor(this,R.color.resultUnderWeight))
            "OVERWEIGHT" -> tvResultTitle.setTextColor(ContextCompat.getColor(this,R.color.resultOverWeight))
            "OBESITY" -> tvResultTitle.setTextColor(ContextCompat.getColor(this,R.color.resultObesity))
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val result = intent.getDoubleExtra("result",0.0)
        tvResultTitle = findViewById(R.id.textViewResulTitle)
        tvResult = findViewById(R.id.textViewResult)
        tvResultSubtitle = findViewById(R.id.textViewSubtitleResult)
        cvReCalculate = findViewById(R.id.cardViewReCalculate)
        val resultadoText = String.format("%.2f", result)
        tvResult.text = resultadoText
        when {
            result < 18.5 -> {
                changeText("UNDERWEIGHT")
                updateColorText("UNDERWEIGHT")
            }
            result >= 18.5 && result <= 24.9 -> {
                changeText("NORMAL")
                updateColorText("NORMAL")
            }
            result >= 25.0 && result <= 29.9-> {
                changeText("OVERWEIGHT")
                updateColorText("OVERWEIGHT")
            }
            else ->{
                changeText("OBESITY")
                updateColorText("OBESITY")
            }
        }
        cvReCalculate.setOnClickListener{
            finish()
        }

    }
}