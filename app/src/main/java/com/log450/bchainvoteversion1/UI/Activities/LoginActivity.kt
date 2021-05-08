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
import com.log450.bchainvoteversion1.Utils.Functions.Companion.validateCredentials
import com.log450.bchainvoteversion1.ViewModel.ViewModelLogin

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBtn:Button
    private lateinit var mEmail: EditText
    private lateinit var mPassword: EditText
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var viewModel : ViewModelLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn = findViewById(R.id.loginBtn)
        mEmail = findViewById(R.id.loginEmailAddress)
        mPassword = findViewById(R.id.loginPassword)
        viewModel = ViewModelLogin()

        loginBtn.setOnClickListener{
            validateCredentials()
            viewModel.signIN(email,password)

            viewModel.getUserID().observe(this, Observer {
                if (it.toString() != "99") {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Successful",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this@LoginActivity, VotingPanelActivity::class.java)
                    intent.putExtra(Constants.EXTRA_ID, it.toString())
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login  failed",
                        Toast.LENGTH_LONG
                    ).show()

                }
            })
        }
    }
    private fun validateCredentials() {
        email = validateCredentials(mEmail,true)
        if(Functions.validateEmail(email)) {
            password = validateCredentials(mPassword, false)
        }

        else{
            Toast.makeText(this, "Email address invalid", Toast.LENGTH_LONG).show()
        }
    }

}