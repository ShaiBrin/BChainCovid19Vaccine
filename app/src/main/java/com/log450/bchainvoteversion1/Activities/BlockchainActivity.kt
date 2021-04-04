package com.log450.bchainvoteversion1.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.log450.bchainvoteversion1.Actors.Candidate.Candidate
import com.log450.bchainvoteversion1.Actors.Candidate.CandidateAdapter
import com.log450.bchainvoteversion1.Model.Block
import com.log450.bchainvoteversion1.Model.BlockchainAdapter
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.ViewModel.ViewModelBlockchain

class BlockchainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = ViewModelBlockchain()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blockchain)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerBlock)
        viewModel.getBlockchain().observe(this, Observer {
          //  Log.d("looooooooooooooooooooooooooooooooooooooooooooL", it[1].getHash())

            recyclerView.adapter = viewModel.getBlockchain().value?.let { BlockchainAdapter(it as ArrayList<Block>, ) }
        })
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}