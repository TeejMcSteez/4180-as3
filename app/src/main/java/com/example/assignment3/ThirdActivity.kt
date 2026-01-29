package com.example.assignment3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)

        val nameVal = findViewById<TextView>(R.id.nameValue)
        val emailVal = findViewById<TextView>(R.id.emailValue)
        val roleVal = findViewById<TextView>(R.id.roleValue)
        if (intent != null && intent.extras != null) {
            val name = intent.getStringExtra("name")
            val email = intent.getStringExtra("email")
            val role = intent.getStringExtra("role")

            nameVal.text = name
            emailVal.text = email
            roleVal.text = role

            intent.putExtra("name", name)
                .putExtra("email", email)
                .putExtra("role", role)
        } else {
            Toast.makeText(this, "Could not get values from passed intent", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            val nextIntent = Intent(this, OptionalActivity::class.java)
            nextIntent.putExtra("name", intent.getStringExtra("name").toString())
                .putExtra("email", intent.getStringExtra("email").toString())
                .putExtra("role", intent.getStringExtra("role").toString())
            startActivity(nextIntent)
        }
    }
}