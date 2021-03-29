package com.log450.bchainvoteversion1

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.log450.bchainvoteversion1.Repository.Blockchain

class Register : AppCompatActivity() {

    private lateinit var mEmail: EditText
    private lateinit var mPassword: EditText
    private lateinit var mAuthenticationKey: EditText
    private lateinit var fauth: FirebaseAuth
    private lateinit var fbase: FirebaseFirestore
    private lateinit var registerBtn: Button

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var authenticationKey: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mEmail = findViewById(R.id.emailRegister)
        mPassword = findViewById(R.id.passwordRegister)
        mAuthenticationKey = findViewById(R.id.authenticationKeyRegister)
        registerBtn = findViewById(R.id.registerBtn)
        fauth = FirebaseAuth.getInstance()
        fbase = FirebaseFirestore.getInstance();


        registerBtn.setOnClickListener {
            validateCredentials()
            createUserWithKey()
            createBlockchain()
        }

    }

    private fun createBlockchain(){
        val docRef = fbase.collection("blockchain").document("1")
        docRef.get().addOnSuccessListener {
                document ->
            if(!document.exists()){
                Toast.makeText(this, "Does not exists", Toast.LENGTH_LONG).show()
                Blockchain
            }
            else{
                Toast.makeText(this, "Already exists", Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun validateCredentials() {
        email = mEmail.text.toString().trim()
        password = mPassword.text.toString().trim()
        authenticationKey = mAuthenticationKey.text.toString().trim()
        if (TextUtils.isEmpty(email)) {
            mEmail.error = "Email is required";
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mPassword.error = "Password is required";
            return
        }

        if (TextUtils.isEmpty(authenticationKey)) {
            mAuthenticationKey.error = "Authentication key is required";
            return
        }
    }


    private fun createUserWithKey() {
        fbase.collection("people").get()
            .addOnSuccessListener { result ->

                for (document in result) {

                    if (authenticationKey == document.get("publicKey")
                            .toString() && document.get("authenticationKey") == false
                    ) {

                        fauth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task: Task<AuthResult> ->

                                if (task.isSuccessful) {
                                    fbase.collection("people")
                                        .document(document.get("id").toString())
                                        .update("authenticationKey", "true")
                                }
                            }
                        //Toast.makeText(
                          //  this@Register,
                            //"Successfully Registered",
                            //Toast.LENGTH_LONG
                        //).show()
                        val intent = Intent(this@Register, Vote::class.java)
                        startActivity(intent)
                        finish()
                        break
                    }


                    else {
                        //Toast.makeText(
                          //  this@Register,
                            //"Registration failed",
                            //Toast.LENGTH_LONG
                        //)
                          //  .show()
                    }
                }
            }
    }

}