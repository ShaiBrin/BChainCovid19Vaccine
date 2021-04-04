package com.log450.bchainvoteversion1.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

import com.log450.bchainvoteversion1.Repository.FireBaseRepository
import kotlinx.coroutines.Dispatchers

class ViewModelRegiser : ViewModel() {

    private val repository = FireBaseRepository()
    private lateinit var authenticationKey: String
    private lateinit var email:String
    private lateinit var password:String


    private val userCreated = liveData(Dispatchers.IO) {
        emit(repository.createUserWithKey(authenticationKey,email,password))
    }

    fun createUserWithKey(authenticationKey: String, email: String, password: String) {
        this.authenticationKey = authenticationKey
        this.email = email
        this.password = password
    }

    fun getUserID():LiveData<Int>{
        return userCreated
    }

}