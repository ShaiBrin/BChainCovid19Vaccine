package com.log450.bchainvoteversion1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Login : AppCompatActivity() {

    private lateinit var loginBtn:Button
    private lateinit var registerBtn:Button
    private lateinit var loginEmail:EditText
    private lateinit var loginPassword:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        registerBtn = findViewById(R.id.RegisterBtn)

        registerBtn.setOnClickListener{
            val intent = Intent(this@Login, Register::class.java)
            startActivity(intent)
            finish()
        }
    }






}