package com.e.oving2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

const val RANDOM_REQUEST = 1
const val RANDOM_REQUEST1 = 2
const val RANDOM_REQUEST2 = 3

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.my_button)
        val addButton = findViewById<Button>(R.id.add_button)
        val multiplyButton = findViewById<Button>(R.id.multiply_button)
        val leftNumberView = findViewById<TextView>(R.id.leftNumberView)
        val rightNumberView = findViewById<TextView>(R.id.rightNumberView)
        val answerEdit = findViewById<EditText>(R.id.editAnswerText)
        val editUpperLimit = findViewById<EditText>(R.id.editUpperLimit)

        button.setOnClickListener {
            startActivityForResult(Intent(this, RandomActivity()::class.java)
                .putExtra("limit", editUpperLimit.text.toString().toInt()), RANDOM_REQUEST)
        }

        addButton.setOnClickListener {
            val sum = leftNumberView.text.toString().toInt() + rightNumberView.text.toString().toInt()
            if (sum == answerEdit.text.toString().toInt()) {
                Toast.makeText(this, resources.getString(R.string.correct), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "${resources.getString(R.string.wrong)} $sum", Toast.LENGTH_SHORT).show()
            }
            newNumbers()
        }

        multiplyButton.setOnClickListener {
            val product = leftNumberView.text.toString().toInt() * rightNumberView.text.toString().toInt()
            if (product == answerEdit.text.toString().toInt()) {
                Toast.makeText(this, resources.getString(R.string.correct), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "${resources.getString(R.string.wrong)} $product", Toast.LENGTH_SHORT).show()
            }
            newNumbers()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "Ingen tall hentet", Toast.LENGTH_SHORT).show()
        }

        when(requestCode) {
            RANDOM_REQUEST -> Toast.makeText(this, "Ditt tall: ${data?.getIntExtra("result", 0)}", Toast.LENGTH_SHORT).show()

            RANDOM_REQUEST1 -> leftNumberView.text = data?.getIntExtra("result", 0).toString()

            RANDOM_REQUEST2 -> rightNumberView.text = data?.getIntExtra("result", 0).toString()
        }
    }

    private fun newNumbers() {
        startActivityForResult(Intent(this, RandomActivity()::class.java)
            .putExtra("limit", editUpperLimit.text.toString().toInt()), RANDOM_REQUEST1)

        startActivityForResult(Intent(this, RandomActivity()::class.java)
            .putExtra("limit", editUpperLimit.text.toString().toInt()), RANDOM_REQUEST2)
    }

}
