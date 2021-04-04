package com.log450.bchainvoteversion1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Actors.Voter.Menu
import com.log450.bchainvoteversion1.Utils.validateCredentials
import com.log450.bchainvoteversion1.ViewModel.ViewModelRegiser
import androidx.lifecycle.Observer
import com.log450.bchainvoteversion1.Utils.Constants
import com.log450.bchainvoteversion1.Utils.Constants.EXTRA_ID

class Register : AppCompatActivity() {

    private lateinit var mEmail: EditText
    private lateinit var mPassword: EditText
    private lateinit var mAuthenticationKey: EditText
    private lateinit var fauth: FirebaseAuth
    private lateinit var registerBtn: Button


    private lateinit var email: String
    private lateinit var password: String
    private lateinit var authenticationKey: String
    private var fbase = FirebaseFirestore.getInstance()
    private var viewModel = ViewModelRegiser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mEmail = findViewById(R.id.emailRegister)
        mPassword = findViewById(R.id.passwordRegister)
        mAuthenticationKey = findViewById(R.id.authenticationKeyRegister)
        registerBtn = findViewById(R.id.registerBtn)


        fauth = FirebaseAuth.getInstance()


        registerBtn.setOnClickListener {
            validateCredentials()
            //createUserWithKey()
        }
    }


    private fun validateCredentials() {
        email = validateCredentials(mEmail)
        password = validateCredentials(mPassword)
        authenticationKey = validateCredentials(mAuthenticationKey)

        viewModel.createUserWithKey(authenticationKey, email, password)
        viewModel.getUserID().observe(this, Observer {
            if (it.toString().isNotEmpty()) {
                Toast.makeText(
                    this@Register,
                    "Successfully Registered",
                    Toast.LENGTH_LONG
                ).show()
                val intent = Intent(this@Register, Menu::class.java)
                intent.putExtra(EXTRA_ID, it.toString())
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this@Register,
                    "Registration failed",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }


    /* private fun createUserWithKey() {
         fbase.collection("voters").get()
             .addOnSuccessListener { result ->

                 for (document in result) {

                     if (authenticationKey == document.get("authenticationKey")
                             .toString() && document.get("authenticated") == false
                     ) {

                         fauth.createUserWithEmailAndPassword(email, password)
                             .addOnCompleteListener { task: Task<AuthResult> ->
                                 if (task.isSuccessful) {
                                     fbase.collection("voters")
                                         .document(document.get("id").toString())
                                         .update("authenticated", "true")
                                 }
                             }


                         val intent = Intent(this@Register, Menu::class.java)
                         startActivity(intent)
                         finish()
                         break
                     }


                     else {
                         Toast.makeText(
                             this@Register,
                             "Registration failed",
                             Toast.LENGTH_LONG
                         ).show()
                     }
                 }
             }
     }*/

}