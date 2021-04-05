package com.log450.bchainvoteversion1.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.ViewModel.ViewModelStats

class FragmentStats : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val statsView =  inflater.inflate(R.layout.fragment_stats, container, false) as View
        val v = ViewModelStats()
        displayStats(statsView,v)
        return statsView
    }

    private fun displayStats(view: View, viewModel: ViewModelStats) {

        viewModel.getAPI().observe(
            viewLifecycleOwner,
            {
                view.findViewById<TextView>(R.id.vaccinatedAnswerAnswer).text = it.all.people_vaccinated.toString()
            })
        viewModel.getPfizerQuantity().observe(
            viewLifecycleOwner,
            {
                view.findViewById<TextView>(R.id.PfizerAnswer).text = it.toString()
            })

        viewModel.getModernaQuantity().observe(
            viewLifecycleOwner,
            {
                view.findViewById<TextView>(R.id.ModernaAnswer).text = it.toString()
            })


        viewModel.getJohsonQuantity().observe(
            viewLifecycleOwner,
            {
                view.findViewById<TextView>(R.id.JohssonaAnswer).text = it.toString()
            })

        viewModel.getVaccineBlockchain().observe(
            viewLifecycleOwner,
            {
                view.findViewById<TextView>(R.id.VaccineBlockChainAnswer).text = it.toString()
            })

    }



}