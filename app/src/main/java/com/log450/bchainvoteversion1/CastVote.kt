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
    private lateinit var block: Block
    private var previousHash: String = "u"
    private lateinit var previousDoc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cast_vote)
        val value = intent.getStringExtra(EXTRA_STUDENT) as String
        setPreviousHash(value)


    }

    fun setPreviousHash(value: String) {
        fbase.collection("blockchain reference").document("0")
            .get().addOnSuccessListener { document ->
                if (!document.exists()) {

                } else {
                    previousHash = document.get("hash").toString()
                    previousDoc = document.get("data").toString()

                    block = Block(previousHash, value)
                    addBlock(block,previousDoc.toInt()+1)
                    updateReference(block)
                    Toast.makeText(this, "The document exists $previousHash", Toast.LENGTH_LONG)
                        .show()


                }
            }

      /*  fbase.collection("blockchain reference")
            .document("1")
            .update("data", (previousDoc.toInt()+1).toString())

        fbase.collection("blockchain reference")
            .document("1")
            .update("hash", block.getHash())*/


    }

    fun addBlock(b:Block, index:Int){
        fbase.collection("blockchain").document(index.toString()).set(b)
    }

    fun updateReference(b: Block){
        fbase.collection("blockchain reference")
            .document("0")
            .update("data", (previousDoc.toInt()+1).toString())

        fbase.collection("blockchain reference")
            .document("0")
            .update("hash", b.getHash())
    }


}