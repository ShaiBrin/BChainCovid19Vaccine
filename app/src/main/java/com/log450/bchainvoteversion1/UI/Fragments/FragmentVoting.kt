package com.log450.bchainvoteversion1.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.log450.bchainvoteversion1.Actors.Candidate.Candidate
import com.log450.bchainvoteversion1.Adapter.CandidateAdapter
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.ViewModel.ViewModelVote


class FragmentVoting (private val id:String): Fragment(), CandidateAdapter.OnItemClickListener{
    private val viewModel = ViewModelVote()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstaceState: Bundle?): View{
        val candidateListView = inflater.inflate(R.layout.fragment_voting, container,false) as View

        val recyclerView = candidateListView.findViewById(R.id.recycleCandidate) as RecyclerView
        viewModel.getCandidates().observe(viewLifecycleOwner, Observer<List<Candidate>>{
            recyclerView.adapter = viewModel.getCandidates().value?.let { CandidateAdapter(it as ArrayList<Candidate>, this) }
        })
        recyclerView.layoutManager = LinearLayoutManager(activity)
        viewModel.setID(id)
        return candidateListView
    }


    override fun onItemClicked(candidate: Candidate) {
        viewModel.hasAlreadyVoted().observe(this, Observer{
            if(!it){
                viewModel.updateCandidate(candidate.getName())

                viewModel.updateVoter(id).observe(this,  {

                    if(it){
                        viewModel.hasBeenUpdate().observe(this, Observer {
                            if (it)
                                Toast.makeText(
                                    requireContext(),
                                    "Your vote has been submitted",
                                    Toast.LENGTH_LONG
                                ).show()

                            else {
                                Toast.makeText(
                                    requireContext(),
                                    "Your vote has not been submitted",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        })
                        castVote()
                    }
                })
            }

            else{
                Toast.makeText(
                    requireContext(),
                    "You already submitted your vote.",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

    }


    private fun castVote(){

        viewModel.hasBeenAdded().observe(this, Observer {
            it
        })
    }

}