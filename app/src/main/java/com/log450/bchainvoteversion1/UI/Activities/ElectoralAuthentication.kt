package com.log450.bchainvoteversion1.UI.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.Utils.Functions
import com.log450.bchainvoteversion1.ViewModel.ViewModelElectoral

class ElectoralAuthentication : AppCompatActivity() {

    private lateinit var mElectorKey: EditText
    private lateinit var electorKey: String
    private lateinit var registerBtn: Button
    private lateinit var viewModel:ViewModelElectoral

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electoral_authentication)
        viewModel = ViewModelElectoral()
        mElectorKey = findViewById(R.id.ElectoralAuthentication)
        registerBtn = findViewById(R.id.ElectoralKeyBtn)


        registerBtn.setOnClickListener {
            validateCredentials()
        }
    }

    private fun validateCredentials() {
        electorKey = Functions.validateCredentials(mElectorKey,false)
        viewModel.setKey(electorKey)
        viewModel.isAuthenticated().observe(this, Observer {
            if(it){
                Toast.makeText(this, " Welcome", Toast.LENGTH_LONG).show()
                val intent = Intent(this@ElectoralAuthentication, ElectoralActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }
}