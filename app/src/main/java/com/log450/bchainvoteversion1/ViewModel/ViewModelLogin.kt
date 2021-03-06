package com.log450.bchainvoteversion1.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.log450.bchainvoteversion1.Repository.FireBaseRepository
import kotlinx.coroutines.Dispatchers

class ViewModelLogin {

    private val repository = FireBaseRepository()
    private lateinit var email:String
    private lateinit var password:String


    private val userID = liveData(Dispatchers.IO) {
        emit(repository.retrieveUserID(email,password))
    }

    fun signIN(email: String, password: String) {
        this.email = email
        this.password = password
    }

    fun getUserID(): LiveData<Int> {
        return userID
    }

}