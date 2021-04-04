package com.log450.bchainvoteversion1.Actors.Electoral

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Actors.Voter.Menu
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.Utils.Constants

class ElectoralAuthentication : AppCompatActivity() {

    private lateinit var mPassword: EditText
    private lateinit var password: String
    private var fbase: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var view: View
    private lateinit var registerBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electoral_authentication)
        view = findViewById(R.id.ElectoralAuthenticationView)
        mPassword = findViewById(R.id.ElectoralAuthentication)
        registerBtn = findViewById(R.id.ElectoralKeyBtn)


        registerBtn.setOnClickListener {
            validateCredentials()
        }
    }

    private fun validateCredentials() {
        password = mPassword.text.toString().trim()


        if (TextUtils.isEmpty(password)) {
            mPassword.error = "Password is required";
            return
        }


        fbase.collection("electoral")
            .document("0")
            .get()
            .addOnSuccessListener { document ->
                if (document.exists() && (password == document.get("key").toString())) {
                    Toast.makeText(this, " Welcome", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@ElectoralAuthentication, ElectoralActivity::class.java)
                    intent.putExtra(Constants.EXTRA_ID, document.get("id").toString().toInt())
                    intent.putExtra(Constants.EXTRA__FIRST_NAME, document.get("firstName").toString())
                    intent.putExtra(Constants.EXTRA__LAST_NAME, document.get("lastName").toString())
                    intent.putExtra(Constants.EXTRA_KEY, document.get("key").toString())
                    startActivity(intent)
                    finish()
                }

            }


    }


}