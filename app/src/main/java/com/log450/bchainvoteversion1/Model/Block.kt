package com.log450.bchainvoteversion1.Model

import com.log450.bchainvoteversion1.Utils.hash

data class Block(
    private val previousHash: String,
    private val data: String,
    private var hash: String = ""
) {
    init {
        calculateHash()
    }

    private fun calculateHash() {
        hash = "$previousHash$data".hash()
    }

    fun getHash():String{
        return hash
    }

    fun getPreviousHash():String{
        return previousHash
    }

    fun getData():String{
        return data
    }

}