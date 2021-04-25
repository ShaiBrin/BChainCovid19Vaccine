package com.log450.bchainvoteversion1.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.log450.bchainvoteversion1.Model.Block
import com.log450.bchainvoteversion1.Repository.FireBaseRepository
import kotlinx.coroutines.Dispatchers

class ViewModelBlockchain : ViewModel() {

    private val repository = FireBaseRepository()

    private val blockchain = liveData(Dispatchers.IO){
        emit(repository.getBlockchain())
    }

    fun getBlockchain():LiveData<ArrayList<Block>>{
        return blockchain
    }
}
