package com.log450.bchainvoteversion1.UI.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.log450.bchainvoteversion1.R

class CredentialsActivity : AppCompatActivity() {


    private lateinit var registerBtn: Button
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credentials)

        registerBtn = findViewById(R.id.RegisterPanelBtn)
        loginBtn = findViewById(R.id.LoginPanelBtn)

        registerBtn.setOnClickListener {
            val intent = Intent(this@CredentialsActivity, Register::class.java)
            startActivity(intent)
        }
        
        loginBtn.setOnClickListener {
            val intent = Intent(this@CredentialsActivity, Login::class.java)
            startActivity(intent)
        }

    }
}