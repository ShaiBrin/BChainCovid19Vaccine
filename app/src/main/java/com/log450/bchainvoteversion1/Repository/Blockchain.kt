package com.log450.bchainvoteversion1.Repository

import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Model.Block
import java.util.*

object Blockchain {

    private var fbase: FirebaseFirestore = FirebaseFirestore.getInstance()
    init {
        val genesis = Block("0","0")
        fbase.collection("blockchain").document("0").set(genesis)
        fbase.collection("blockchain reference").document("0").set(genesis)
    }
}