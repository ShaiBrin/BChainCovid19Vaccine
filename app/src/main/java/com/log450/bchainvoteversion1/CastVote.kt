package com.log450.bchainvoteversion1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Model.Block
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.Repository.Blockchain
import com.log450.bchainvoteversion1.Repository.Blockchain.getPreviousDoc
import com.log450.bchainvoteversion1.Repository.Blockchain.getPreviousHash
import com.log450.bchainvoteversion1.Repository.Blockchain.updateReference
import com.log450.bchainvoteversion1.Utils.Constants.EXTRA_STUDENT

class CastVote : AppCompatActivity() {
    private var fbase: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var block : Block

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cast_vote)
        val value = intent.getStringExtra(EXTRA_STUDENT) as String
        block = Block(getPreviousHash(), value)
        fbase.collection("blockchain").document((getPreviousDoc().toInt()+1).toString()).set(block)
        updateReference(block)

    }



}