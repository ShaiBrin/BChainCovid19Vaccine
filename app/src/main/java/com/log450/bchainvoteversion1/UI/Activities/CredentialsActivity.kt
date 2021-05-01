package com.log450.bchainvoteversion1.UI.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.Utils.Constants
import com.log450.bchainvoteversion1.Utils.Functions
import com.log450.bchainvoteversion1.ViewModel.ViewModelLogin

class CredentialsActivity : AppCompatActivity() {


    private lateinit var registerBtn: Button
    private lateinit var loginBtn: Button
    private lateinit var mEmail: EditText
    private lateinit var mPassword: EditText
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var viewModel : ViewModelLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credentials)

        registerBtn = findViewById(R.id.Register)
        loginBtn = findViewById(R.id.loginBtn)
        mEmail = findViewById(R.id.loginEmailAddress)
        mPassword = findViewById(R.id.loginPassword)
        viewModel = ViewModelLogin()

        registerBtn.setOnClickListener {
            val intent = Intent(this@CredentialsActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener{
            validateCredentials()
            viewModel.signIN(email,password)

            viewModel.getUserID().observe(this, Observer {
                if (it.toString() != "99") {
                    Toast.makeText(
                        this@CredentialsActivity,
                        "Login Successful",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this@CredentialsActivity, VotingPanelActivity::class.java)
                    intent.putExtra(Constants.EXTRA_ID, it.toString())
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@CredentialsActivity,
                        "Login  failed",
                        Toast.LENGTH_LONG
                    ).show()

                }
            })
        }
    }
    private fun validateCredentials() {
        email = Functions.validateCredentials(mEmail,true)
        if(Functions.validateEmail(email)) {
            password = Functions.validateCredentials(mPassword, false)
        }

        else{
            Toast.makeText(this, "Email address invalid", Toast.LENGTH_LONG).show()
        }
    }



}