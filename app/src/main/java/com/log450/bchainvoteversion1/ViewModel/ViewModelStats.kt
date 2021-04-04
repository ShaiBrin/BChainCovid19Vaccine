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


    fun getPfizerQuantity(): LiveData<Int> {
        return pfizer
    }

    fun getModernaQuantity(): LiveData<Int> {
        return moderna
    }

//    fun getModernaQuantity(): LiveData<Int> {
  //      val vaccines = pfizer.value.toString().toInt() +  moderna.value.toString().toInt()

    //}
}