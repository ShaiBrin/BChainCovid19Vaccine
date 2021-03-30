package com.log450.bchainvoteversion1.Repository

import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Model.Block
import java.util.*

object Blockchain {

    private var fbase: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var blocks : MutableList<Block> = mutableListOf()
    private lateinit var previousHash:String
    private lateinit var previousDoc:String

    init {
        val genesis = Block("0","1")
        fbase.collection("blockchain").document("0").set(genesis)
        fbase.collection("blockchain reference").document("0").set(genesis)
        setPreviousHash()
    }


    private fun setPreviousHash(){
        fbase.collection("blockchain reference").document("0")
            .get().addOnSuccessListener {
                    document ->
                if(!document.exists()){

                }
                else{
                    previousHash =  document.get("hash").toString()
                    previousDoc =  document.get("data").toString()
                }
            }
    }


    fun getPreviousHash():String{
        return previousHash
    }

    fun getPreviousDoc():String{
        return previousDoc
    }

    fun updateReference(b:Block){
        fbase.collection("blockchain reference")
            .document("0")
            .update("data", (previousDoc.toInt()+1).toString())


        fbase.collection("blockchain reference")
            .document("0")
            .update("hash", b.getHash())

        setPreviousHash()
    }



}