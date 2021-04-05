package com.log450.bchainvoteversion1.UI.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.log450.bchainvoteversion1.Utils.validateCredentials
import com.log450.bchainvoteversion1.ViewModel.ViewModelRegiser
import androidx.lifecycle.Observer
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.Utils.Constants.EXTRA_ID
import com.log450.bchainvoteversion1.Utils.validateEmail

class RegisterActivity : AppCompatActivity() {

    private lateinit var mEmail: EditText
    private lateinit var mPassword: EditText
    private lateinit var mAuthenticationKey: EditText
    private lateinit var registerBtn: Button


    private lateinit var email: String
    private lateinit var password: String
    private lateinit var authenticationKey: String
    private var viewModel = ViewModelRegiser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mEmail = findViewById(R.id.emailRegister)
        mPassword = findViewById(R.id.passwordRegister)
        mAuthenticationKey = findViewById(R.id.authenticationKeyRegister)
        registerBtn = findViewById(R.id.registerBtn)
        registerBtn.setOnClickListener {
            validateCredentials()
        }
    }

    private fun validateCredentials() {
        email = validateCredentials(mEmail, true)
        if (validateEmail(email)) {
            password = validateCredentials(mPassword, false)
            authenticationKey = validateCredentials(mAuthenticationKey, false)

            viewModel.createUserWithKey(authenticationKey, email, password)
            viewModel.getUserID().observe(this, Observer { it ->
                if (it.toString() != "99") {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Successfully Registered",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this@RegisterActivity, VotingPanelActivity::class.java)
                    intent.putExtra(EXTRA_ID, it.toString())
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Registration failed",
                        Toast.LENGTH_LONG
                    ).show()

                }
            })

        } else {

            Toast.makeText(this, "Email address invalid", Toast.LENGTH_LONG).show()
        }
    }

}