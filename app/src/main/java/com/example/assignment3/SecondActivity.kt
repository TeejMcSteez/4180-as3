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
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        val nv = findViewById<TextInputLayout>(R.id.nameInputLayout)
        val ev = findViewById<TextInputLayout>(R.id.emailInputLayout)
        if (intent != null && intent.extras != null) {
            val name = intent.getStringExtra("name")
            val email = intent.getStringExtra("email")
            val role = intent.getStringExtra("role")

            nv.editText?.setText(name)
            ev.editText?.setText(email)
            when (role) {
                "Student" -> findViewById<RadioButton>(R.id.studentRadioButton).isChecked = true
                "Employee" -> findViewById<RadioButton>(R.id.employeeRadioButton).isChecked = true
                "Other" -> findViewById<RadioButton>(R.id.otherRadioButton).isChecked = true
                else -> Log.d("IntentNameValue", "role is not valid")
            }
        }

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
            val name = nv.editText?.text.toString()
            val email = ev.editText?.text.toString()
            if (name == "null" ||
                email == "null" ||
                name.trim() == "" ||
                email.trim() == ""
                ) {
                Toast.makeText(this, "Invalid name or email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            intent.putExtra("name", name)
                .putExtra("email", email)

            Log.d("IntentNameValue", intent.getStringExtra("name").toString())
            Log.d("IntentNameValue", intent.getStringExtra("email").toString())

            val nextIntent = Intent(this, ThirdActivity::class.java)
            nextIntent.putExtra("name", name)
                .putExtra("email", email)
                .putExtra("role", intent.getStringExtra("role"))
            startActivity(nextIntent)
            finish()
        }

    }
}