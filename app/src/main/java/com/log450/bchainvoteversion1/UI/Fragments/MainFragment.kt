package com.log450.bchainvoteversion1.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.Repository.FireBaseRepository

class MainFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
       ): View? {
         return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FireBaseRepository()
        view.findViewById<Button>(R.id.VoterBtn).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_credentialsActivity)
        }

        view.findViewById<Button>(R.id.CandidateBtn).setOnClickListener{
            val directions = MainFragmentDirections.actionMainFragmentToCandidateActivity("Ishraq")
            Navigation.findNavController(view).navigate(directions)

        }

        view.findViewById<Button>(R.id.ElectoralBtn).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_electoralAuthentication)
        }

        view.findViewById<Button>(R.id.blochainPanel).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_blockchainActivity)
        }

    }
}


