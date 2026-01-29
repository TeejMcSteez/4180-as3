package com.example.assignment3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        // Role Selection
        findViewById<RadioButton>(R.id.studentRadioButton).setOnClickListener {
            intent.putExtra("role", "Student")
            intent.getStringExtra("role")?.let { msg -> Log.d("IntentNameValue", msg) }
        }
        findViewById<RadioButton>(R.id.employeeRadioButton).setOnClickListener {
            intent.putExtra("role", "Employee")
            intent.getStringExtra("role")?.let { msg -> Log.d("IntentNameValue", msg)}
        }
        findViewById<RadioButton>(R.id.otherRadioButton).setOnClickListener {
            intent.putExtra("role", "Other")
            intent.getStringExtra("role")?.let { msg -> Log.d("IntentNameValue", msg) }
        }
        // Submit Button
        findViewById<Button>(R.id.button2).setOnClickListener {
            if (!intent.hasExtra("role")) {
                Toast.makeText(this, "Role does not have value! Please fill out all fields", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val nv = findViewById<TextInputLayout>(R.id.nameInput)
            if (nv.editText?.text.toString() != "null") {
                Toast.makeText(this, "Name does not have a value! Please fill out all fields", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val ev = findViewById<TextInputLayout>(R.id.emailInput)
            if (ev.editText?.text.toString() != "null") {
                Toast.makeText(this, "Email does have a value! Please fill out all fields", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            intent.putExtra("name", nv.editText?.text.toString())

            intent.putExtra("email", ev.editText?.text.toString())

            Log.d("IntentNameValue", intent.getStringExtra("name").toString())
            Log.d("IntentNameValue", intent.getStringExtra("email").toString())

            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
            Log.d("IntentNameValue", "Completed 2nd activity")
        }

    }
}