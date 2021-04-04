package com.log450.bchainvoteversion1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.log450.bchainvoteversion1.Repository.FireBaseRepository
import com.log450.bchainvoteversion1.ViewModel.ViewModelVote

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FireBaseRepository()

        val v = ViewModelVote()
        v.getCandidates().observe(this, Observer {
          //  Toast.makeText(applicationContext, it[0].getName(), Toast.LENGTH_LONG).show()
        })
    }
}