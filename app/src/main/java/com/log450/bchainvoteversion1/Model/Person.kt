package com.log450.bchainvoteversion1.Model

open class Person(
    private val id: Int,
    private val firstName: String,
    private val lastName: String,
) {
    fun getId(): Int {
        return id
    }

    fun getFirstName(): String {
        return firstName
    }

    fun getLastName(): String {
        return lastName
    }

}

