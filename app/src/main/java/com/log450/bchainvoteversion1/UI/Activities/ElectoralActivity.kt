package com.log450.bchainvoteversion1.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.Utils.Constants
import com.log450.bchainvoteversion1.ViewModel.ViewModelElectoral

class ElectoralActivity : AppCompatActivity() {

    private lateinit var blockchain: Button
    private lateinit var viewModel: ViewModelElectoral

    private lateinit var candidates: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electoral)
        viewModel = ViewModelElectoral()
        blockchain = findViewById(R.id.ElectoralBlockchainBtn)


        blockchain.setOnClickListener {
            viewModel.blockChainExists().observe(this, Observer {
                if(!it){
                    Toast.makeText(this, "Blockchain is starting", Toast.LENGTH_LONG).show()
                }

                else{
                    Toast.makeText(this, "Blockchain has already been started", Toast.LENGTH_LONG).show()
                }
            })

    }
    }
}