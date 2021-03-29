package com.log450.bchainvoteversion1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Model.Block
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.Utils.Constants.EXTRA_STUDENT

class CastVote : AppCompatActivity() {
    private var fbase: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var block : Block

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cast_vote)
        val value = intent.getStringExtra(EXTRA_STUDENT) as String
        /*block  =  Block("", value);
        fbase.collection("blockchain2").document("1").set(block)
        Log.d("Block", block.getHash());*/
    }
}