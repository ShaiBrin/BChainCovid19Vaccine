package com.log450.bchainvoteversion1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.log450.bchainvoteversion1.Actors.Candidate.Candidate
import com.log450.bchainvoteversion1.Actors.Candidate.CandidateAdapter
import com.log450.bchainvoteversion1.Actors.Voter.CastVote
import com.log450.bchainvoteversion1.Utils.Constants.EXTRA_STUDENT
import com.log450.bchainvoteversion1.ViewModel.ViewModelVote
import org.antlr.v4.runtime.misc.MurmurHash.finish


class FragmentVoting (private val id:String): Fragment(), CandidateAdapter.OnItemClickListener{
    private val viewModel = ViewModelVote()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstaceState: Bundle?): View{
        val candidateListView = inflater.inflate(R.layout.fragment_voting, container,false) as View

        //The list of Students that is passed in the StudentAdapter observers the viewmodel.
        val recyclerView = candidateListView.findViewById(R.id.recycleCandidate) as RecyclerView
        viewModel.getCandidates().observe(viewLifecycleOwner, Observer<List<Candidate>>{
            recyclerView.adapter = viewModel.getCandidates().value?.let { CandidateAdapter(it as ArrayList<Candidate>, this) }
        })
        recyclerView.layoutManager = LinearLayoutManager(activity)
        return candidateListView
    }


    override fun onItemClicked(candidate: Candidate) {
        viewModel.updateCandidate(candidate.getName())

        viewModel.hasBeenUpdate().observe(this, Observer{
            if(it)
                Toast.makeText(requireContext(), "Your vote has been submitted", Toast.LENGTH_LONG).show()
            else{
                Toast.makeText(requireContext(), "Your vote has not been submitted", Toast.LENGTH_LONG).show()
            }
        })
        castVote()
    }

    private fun castVote(){
        viewModel.updateVoter(id).observe(this,  {
            it
        })

        viewModel.hasBeenAdded().observe(this, Observer {
            it
        })
    }

}