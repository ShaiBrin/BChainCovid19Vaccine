package com.log450.bchainvoteversion1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Repository.Blockchain
import com.log450.bchainvoteversion1.Repository.FireBaseRepository


class MainActivity : AppCompatActivity() {

    private var fbase: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fbase.collection("blockchain").document("1")
        .get().addOnSuccessListener {
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

}