package com.example.assignment3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class OptionalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_optional)

        val nameInput = findViewById<TextInputEditText>(R.id.editNameInput)
        val emailInput = findViewById<TextInputEditText>(R.id.editEmailInput)

        if (intent != null && intent.extras != null) {
            val name = intent.getStringExtra("name") ?: ""
            val email = intent.getStringExtra("email") ?: ""
            val role = intent.getStringExtra("role") ?: ""

            nameInput.setText(name)
            emailInput.setText(email)

            intent.putExtra("oldName", name)
                .putExtra("oldEmail", email)
                .putExtra("oldRole", role)

            when (role) {
                "Student" -> findViewById<RadioButton>(R.id.editStudentRadioButton).isChecked = true
                "Employee" -> findViewById<RadioButton>(R.id.editEmployeeRadioButton).isChecked = true
                "Other" -> findViewById<RadioButton>(R.id.editOtherRadioButton).isChecked = true
                else -> Log.d("IntentNameValue", "role is not valid")
            }
        }

        findViewById<RadioButton>(R.id.editStudentRadioButton).setOnClickListener {
            intent.putExtra("role", "Student")
        }
        findViewById<RadioButton>(R.id.editEmployeeRadioButton).setOnClickListener {
            intent.putExtra("role", "Employee")
        }
        findViewById<RadioButton>(R.id.editOtherRadioButton).setOnClickListener {
            intent.putExtra("role", "Other")
        }

        findViewById<Button>(R.id.cancelButton).setOnClickListener {
            val newIntent = Intent(this, ThirdActivity::class.java)
            newIntent.putExtra("name", intent.getStringExtra("oldName"))
                .putExtra("email", intent.getStringExtra("oldEmail"))
                .putExtra("role", intent.getStringExtra("oldRole"))
            startActivity(newIntent)
            finish()
        }

        findViewById<Button>(R.id.submitButton).setOnClickListener {
            val newIntent = Intent(this, ThirdActivity::class.java)
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val role = intent.getStringExtra("role") ?: ""

            if (name == "null" || name.trim() == "") {
                Toast.makeText(this, "There is no name set!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (email == "null" || email.trim() == "") {
                Toast.makeText(this, "There is no email set!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (role == "null" || role.trim() == "") {
                Toast.makeText(this, "There is no role set!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            newIntent.putExtra("name", name)
                .putExtra("email", email)
                .putExtra("role", role)
            startActivity(newIntent)
            finish()
        }
    }
}