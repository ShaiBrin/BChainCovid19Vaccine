package com.log450.bchainvoteversion1.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.log450.bchainvoteversion1.Actors.Candidate.Candidate
import com.log450.bchainvoteversion1.MainViewModel
import com.log450.bchainvoteversion1.Model.VaccineType
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.Repository.FireBaseRepository
import com.log450.bchainvoteversion1.ViewModel.ViewModelBlockchain
import com.log450.bchainvoteversion1.ViewModel.ViewModelVote

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FireBaseRepository()
    }
}