package com.log450.bchainvoteversion1.Model

class Person(
    private val id: Number,
    private val firstName: String,
    private val lastName: String,
    private val authenticated: Boolean,
    private val authenticateKey:String,
    private val hasVoted: Boolean
) {
    fun getId(): Number {
        return id
    }

    fun getFirstName(): String {
        return firstName
    }

    fun getLastName(): String {
        return lastName
    }

    fun getauthenticationKey(): Boolean {
        return authenticated
    }

    fun getPublicKey():String{
        return authenticateKey
    }

    fun hasVoted():Boolean{
        return hasVoted
    }
}

