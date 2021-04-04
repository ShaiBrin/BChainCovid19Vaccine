package com.log450.bchainvoteversion1

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.log450.bchainvoteversion1.Actors.Candidate.Candidate
import com.log450.bchainvoteversion1.Actors.Candidate.VaccineStats
import com.log450.bchainvoteversion1.Actors.Voter.Voter
import com.log450.bchainvoteversion1.Repository.FireBaseRepository

import kotlinx.coroutines.Dispatchers

class MainViewModel: ViewModel() {

    private val repository = FireBaseRepository()



    private val vaccineAPI = liveData(Dispatchers.IO){
        emit(repository.getAPI())
    }

    private val candidates = liveData(Dispatchers.IO){
        emit(repository.getCandidatesDB())
    }
    private val voters = liveData(Dispatchers.IO){
        emit(repository.getVotersDB())
    }

    fun getData(): LiveData<ArrayList<Voter>> {
        return voters
    }


    fun getAPI():LiveData<VaccineStats>{
        return vaccineAPI
    }

    fun getCandidates(): LiveData<ArrayList<Candidate>> {
        return candidates
    }





}




