package com.log450.bchainvoteversion1.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.log450.bchainvoteversion1.Repository.FireBaseRepository
import kotlinx.coroutines.Dispatchers

class ViewModelElectoral {

    private val repository = FireBaseRepository()

    private lateinit var key : String

    private val authenticated = liveData(Dispatchers.IO) {
        emit(repository.validateElector(key))
    }

    private val blockchain = liveData(Dispatchers.IO) {
        emit(repository.initiateBlockChain())
    }

    private val initVal = liveData(Dispatchers.IO) {
        emit(repository.initVoters())
    }

    fun setKey(key:String){
        this.key = key
    }

    fun isAuthenticated(): LiveData<Boolean> {
        return authenticated
    }

    fun blockChainExists(): LiveData<Boolean> {
        return blockchain
    }

    fun hasDataBeenInitialized():LiveData<Unit>{
        return initVal
    }

}