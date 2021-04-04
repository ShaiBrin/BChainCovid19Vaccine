package com.log450.bchainvoteversion1.Actors.Electoral

import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Model.Person
import com.log450.bchainvoteversion1.Repository.Blockchain

class Electoral(
    id: Int,
    firstName: String,
    lastName: String,
    private val key:String
) : Person(
    id,
    firstName,
    lastName
) {
    private var blockhainAlreadyStarted : Boolean = false
    private var fbase: FirebaseFirestore = FirebaseFirestore.getInstance()
    fun instantiateBlockchain(){
        fbase.collection("blockchain reference").document("1")
            .get().addOnSuccessListener {
                    document ->
                if(!document.exists()){
                    Blockchain
                    blockhainAlreadyStarted = true


                }
                else{
                }
            }
    }

    fun getKey():String{
        return key
    }

    fun hasBlockchainStarted() : Boolean{
        return blockhainAlreadyStarted
    }
}