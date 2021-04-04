package com.log450.bchainvoteversion1.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.log450.bchainvoteversion1.Repository.FireBaseRepository
import kotlinx.coroutines.Dispatchers

class ViewModelStats: ViewModel() {
    private val repository = FireBaseRepository()

    private val pfizer = liveData(Dispatchers.IO){
        emit(repository.getVaccines("PFIZER"))
    }

    private val moderna = liveData(Dispatchers.IO){
        emit(repository.getVaccines("MODERNA"))
    }

    private val johson = liveData(Dispatchers.IO){
        emit(repository.getVaccines("JOHNSON"))
    }

    private val vaccine = liveData(Dispatchers.IO){
        emit(repository.getTotal())
    }


    fun getPfizerQuantity(): LiveData<Int> {
        return pfizer
    }

    fun getModernaQuantity(): LiveData<Int> {
        return moderna
    }

    fun getJohsonQuantity(): LiveData<Int> {
        return johson
    }

    fun getVaccineBlockchain(): LiveData<Int> {
        return vaccine
    }


}