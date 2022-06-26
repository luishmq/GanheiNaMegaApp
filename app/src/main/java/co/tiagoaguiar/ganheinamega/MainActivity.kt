package co.tiagoaguiar.ganheinamega

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import co.tiagoaguiar.ganheinamega.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding:ActivityMainBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        val txtResult: TextView = viewBinding.textView2
        viewBinding.button.setOnClickListener {
            val text = viewBinding.editNumber.text.toString()
            numberGenerator(text, txtResult)
        }
        prefs = getSharedPreferences("banco", Context.MODE_PRIVATE)
        val result = prefs.getString("result", null)
        if(result != null){
            txtResult.text = "Ultima aposta: $result"
        }
    }
    private fun numberGenerator(text: String, txtResult: TextView){
        if(text.isNotEmpty()){
            val qtd = text.toInt()

            if(qtd >= 6 && qtd <= 15){
                val numbers = mutableSetOf<Int>()
                val random = Random

                while(true){
                    val number = random.nextInt(60)
                    numbers.add(number + 1)

                    if(numbers.size == qtd){
                        break
                    }
                }
                txtResult.text = numbers.joinToString(" - ")

                val editor = prefs.edit()
                editor.putString("result",txtResult.text.toString())
                editor.apply()
            }
            else{
                Toast.makeText(this,"Informe um número entre 6 e 15",Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(this,"Informe um número entre 6 e 15",Toast.LENGTH_LONG).show()
        }
    }
}