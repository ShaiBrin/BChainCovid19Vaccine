package com.log450.bchainvoteversion1.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.ViewModel.ViewModelElectoral

class ElectoralActivity : AppCompatActivity() {

    private lateinit var startBlockchainBtn: Button
    private lateinit var initBtn: Button
    private lateinit var viewModel: ViewModelElectoral


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electoral)
        viewModel = ViewModelElectoral()
        startBlockchainBtn = findViewById(R.id.ElectoralBlockchainBtn)
        initBtn = findViewById(R.id.initData)

        startBlockchainBtn.setOnClickListener {
            viewModel.blockChainExists().observe(this,  {
                if(!it){
                    Toast.makeText(this, "Blockchain is starting", Toast.LENGTH_LONG).show()
                }

                else{
                    Toast.makeText(this, "Blockchain has already been started", Toast.LENGTH_LONG).show()
                }
            })
        }

        initBtn.setOnClickListener {
            viewModel.hasDataBeenInitialized().observe(this,  {
                    Toast.makeText(this, "Voters are initialized", Toast.LENGTH_LONG).show()
            })
        }
    }
}