package com.log450.bchainvoteversion1.Actors.Voter

import com.log450.bchainvoteversion1.Model.Person

class Voter(
    id: Int,
    firstName: String,
    lastName: String,
    private val authenticationKey: String,

) : Person(id, firstName, lastName) {

    private val authenticated:Boolean = false
    private val hasVoted:Boolean = false

    fun getAuthenticated(): Boolean {
        return authenticated
    }

    fun getAuthenticationKey(): String {
        return authenticationKey
    }

    fun getHasVoted(): Boolean {
        return hasVoted
    }
}