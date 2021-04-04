package com.log450.bchainvoteversion1.Actors.Electoral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.Utils.Constants

class ElectoralActivity : AppCompatActivity() {

    private lateinit var blockchain: Button
    private lateinit var candidates: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electoral)

        //blockchain = findViewById(R.id.ElectoralBlockchainBtn)
        candidates = findViewById(R.id.ElectoralListBtn)
        candidates.setOnClickListener {

        }

        /*
        val id = intent.getIntExtra(Constants.EXTRA_ID,1)
        val firstName = intent.getStringExtra(Constants.EXTRA__FIRST_NAME) as String
        val lastName = intent.getStringExtra(Constants.EXTRA__LAST_NAME) as String
        val key = intent.getStringExtra(Constants.EXTRA_KEY) as String


        val electoral = Electoral(id,firstName,lastName,key)
        blockchain.setOnClickListener {
            electoral.instantiateBlockchain()
            if(electoral.hasBlockchainStarted()) {
                Toast.makeText(this, "Blockchain has already been started", Toast.LENGTH_LONG).show()
            }

            else{
                Toast.makeText(this, "Blockchain is starting", Toast.LENGTH_LONG).show()

            }
        }
    }*/
    }
}