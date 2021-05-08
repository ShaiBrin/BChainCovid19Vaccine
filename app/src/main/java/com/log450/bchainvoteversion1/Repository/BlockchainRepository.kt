package com.log450.bchainvoteversion1.Repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Model.Block
import kotlinx.coroutines.tasks.await

class BlockchainRepository {

    suspend fun getBlockchain(fbase : FirebaseFirestore):ArrayList<Block>{
        val blockchain = arrayListOf<Block>()
        lateinit var block: Block
        fbase.collection("blockchain").get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        block = Block(
                            document.get("previousHash").toString(),
                            document.get("data").toString(),
                            document.get("hash").toString(),
                        )
                        blockchain.add(block)
                    }
                }
            }
            .await()
        return blockchain
    }
    suspend fun setPreviousHash(fbase : FirebaseFirestore, value: String): Boolean {
        lateinit var block: Block
        lateinit var previousHash: String
        lateinit var previousDoc: String
        var blockAdded = false
        fbase.collection("blockchain reference")
            .document("0")
            .get().addOnSuccessListener { document ->
                if (!document.exists()) {

                } else {
                    previousHash = document.get("hash").toString()
                    previousDoc = document.get("data").toString()
                    block = Block(previousHash, value)
                    addBlock(fbase,block, previousDoc.toInt() + 1)

                    updateReference(fbase,block)
                    blockAdded = true
                }
            }.await()
        return blockAdded
    }

    private fun addBlock(fbase : FirebaseFirestore, b: Block, index: Int) {
        fbase.collection("blockchain").document(index.toString()).set(b)
    }

    private fun updateReference(fbase : FirebaseFirestore, b: Block) {
        fbase.collection("blockchain reference")
            .document("0")
            .update("data", FieldValue.increment(1))

        fbase.collection("blockchain reference")
            .document("0")
            .update("hash", b.getHash())
    }

}