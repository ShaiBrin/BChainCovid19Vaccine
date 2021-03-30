package com.log450.bchainvoteversion1.Model

class Candidate (
    private val nbVotes:Number,
    private val name: String,
){
    fun getNbVotes():Number {
        return nbVotes
    }

    fun getName():String{
        return name
    }

}