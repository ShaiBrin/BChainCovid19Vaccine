package com.log450.bchainvoteversion1.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.log450.bchainvoteversion1.Model.VaccineType
import com.log450.bchainvoteversion1.Repository.FireBaseRepository
import com.log450.bchainvoteversion1.Utils.getVaccineFromApplication
import kotlinx.coroutines.Dispatchers

class ViewModelCandidate: ViewModel() {

    private val repository = FireBaseRepository()
    private lateinit var name:String
    private lateinit var vaccine :  VaccineType

    private val applicationSubmitted = liveData(Dispatchers.IO){
        emit(repository.createApplication(name,vaccine))
    }

    fun submitCandidateApplication(name:String, vaccinee: String){
        this.name = name
        this.vaccine = getVaccineFromApplication(vaccinee)
    }

    fun isApplicationCreated(): LiveData<Boolean> {
        return applicationSubmitted
    }
}