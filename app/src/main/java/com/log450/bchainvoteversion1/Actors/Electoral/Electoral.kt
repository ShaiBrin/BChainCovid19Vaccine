package com.log450.bchainvoteversion1.Actors.Electoral

import com.log450.bchainvoteversion1.Model.Person

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
    fun getKey():String{
        return key
    }
}