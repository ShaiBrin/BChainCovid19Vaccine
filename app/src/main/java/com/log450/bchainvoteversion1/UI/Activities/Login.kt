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
import com.log450.bchainvoteversion1.ViewModel.ViewModelLogin

class Login : AppCompatActivity() {

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
                        this@Login,
                        "Login Successful",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this@Login, VotingPanel::class.java)
                    intent.putExtra(Constants.EXTRA_ID, it.toString())
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@Login,
                        "Login  failed",
                        Toast.LENGTH_LONG
                    ).show()

                }
            })
        }
    }




    private fun validateCredentials() {
        email = com.log450.bchainvoteversion1.Utils.validateCredentials(mEmail)
        password = com.log450.bchainvoteversion1.Utils.validateCredentials(mPassword)

    }

}