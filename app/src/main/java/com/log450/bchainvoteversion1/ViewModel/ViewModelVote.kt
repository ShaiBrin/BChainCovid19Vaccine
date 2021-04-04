package com.log450.bchainvoteversion1.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.log450.bchainvoteversion1.Actors.Candidate.Candidate
import com.log450.bchainvoteversion1.Repository.FireBaseRepository
import kotlinx.coroutines.Dispatchers

class ViewModelVote : ViewModel() {

    private val repository = FireBaseRepository()
    private lateinit var candidateName : String


    private val blockAdded = liveData(Dispatchers.IO){
        emit(repository.setPreviousHash(candidateName))
    }

    private val candidateUpdate = liveData(Dispatchers.IO){
        emit(repository.voteCandidate(candidateName))
    }

    private val candidates = liveData(Dispatchers.IO){
        emit(repository.getCandidatesDB())
    }

    fun updateVoter(id:String) = liveData(Dispatchers.IO){
        emit(repository.updateVote(id))
    }

    fun updateCandidate(name:String){
        this.candidateName = name
    }


    fun getCandidates(): LiveData<ArrayList<Candidate>> {
        return candidates
    }

    fun hasBeenUpdate():LiveData<Boolean> {
        return candidateUpdate
    }

    fun hasBeenAdded():LiveData<Boolean>{
        return blockAdded
    }




}