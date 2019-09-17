package com.e.oving2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.oving2.R
import kotlin.random.Random

class RandomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val randLimit = intent.getIntExtra("limit", 0)

        val rand = Random.nextInt(0, randLimit)

        setResult(Activity.RESULT_OK, Intent().putExtra("result", rand))
        finish()
      //  Toast.makeText(this, "Ditt tall: $rand", Toast.LENGTH_SHORT).show()
    }
}
