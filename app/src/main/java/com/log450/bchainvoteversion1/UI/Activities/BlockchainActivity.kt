package com.log450.bchainvoteversion1.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.log450.bchainvoteversion1.Model.Block
import com.log450.bchainvoteversion1.Adapter.BlockchainAdapter
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.ViewModel.ViewModelBlockchain

class BlockchainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = ViewModelBlockchain()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blockchain)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerBlock)
        viewModel.getBlockchain().observe(this, Observer {
            recyclerView.adapter = viewModel.getBlockchain().value?.let { BlockchainAdapter(it as ArrayList<Block>, ) }
        })
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}