package com.log450.bchainvoteversion1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var mEmail : EditText
    private lateinit var mPassword : EditText
    private lateinit var fauth: FirebaseAuth
    private lateinit var registerBtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mEmail = findViewById(R.id.userEmail)
        mPassword = findViewById(R.id.UserPassword)
        registerBtn = findViewById(R.id.registerBtn)

        fauth = FirebaseAuth.getInstance()

        if(fauth.currentUser != null){
            val intent = Intent(this@Register, MainActivity::class.java)
            startActivity(intent)
        }


        registerBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val email = mEmail.text.toString().trim();
                val password = mPassword.text.toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return
                }

                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {task: Task<AuthResult> ->

                    if(task.isSuccessful){
                        Toast.makeText(this@Register, "Successfully Registered", Toast.LENGTH_LONG).show()
                        val user = fauth.currentUser
                        val intent = Intent(this@Register, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this@Register, "Registration failed", Toast.LENGTH_LONG).show()
                    }



                }

            }
        })

    }
}