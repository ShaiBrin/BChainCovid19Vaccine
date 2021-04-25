package com.log450.bchainvoteversion1.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.log450.bchainvoteversion1.Actors.Candidate.VaccineStats
import com.log450.bchainvoteversion1.Repository.FireBaseRepository
import kotlinx.coroutines.Dispatchers

class ViewModelStats: ViewModel() {
    private val repository = FireBaseRepository()
    private lateinit var countryName : String

    private val pfizer = liveData(Dispatchers.IO){
        emit(repository.getVaccineType("PFIZER"))
    }

    private val moderna = liveData(Dispatchers.IO){
        emit(repository.getVaccineType("MODERNA"))
    }

    private val johson = liveData(Dispatchers.IO){
        emit(repository.getVaccineType("JOHNSON"))
    }

    private val vaccine = liveData(Dispatchers.IO){
        emit(repository.getTotalVaccine())
    }

    private val vaccineAPI = liveData(Dispatchers.IO){
        emit(repository.getAPI(countryName))
    }

    fun setName(countryName:String){
        this.countryName = countryName
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

    fun getAPI():LiveData<VaccineStats>{
        return vaccineAPI
    }


}