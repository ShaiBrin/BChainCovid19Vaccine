package com.log450.bchainvoteversion1.UI.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.log450.bchainvoteversion1.R

import com.log450.bchainvoteversion1.Utils.Constants.EXTRA_STUDENT
import com.log450.bchainvoteversion1.ViewModel.ViewModelCandidate
import com.log450.bchainvoteversion1.ViewModel.ViewModelRegiser
import com.log450.bchainvoteversion1.ViewModel.ViewModelVote

class Vote : AppCompatActivity() {

    private lateinit var radioGroup : RadioGroup
    private lateinit var radioBtn : RadioButton
    private lateinit var castVoteBtn : Button
    private var viewModel = ViewModelVote()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0,0)
        setContentView(R.layout.activity_vote)

        //radioGroup = findViewById(R.id.radioGroup)
        castVoteBtn = findViewById(R.id.voteBtn)


        castVoteBtn.setOnClickListener {

            viewModel.getCandidates().observe(this, Observer {
                Toast.makeText(this, it[0].getName(), Toast.LENGTH_LONG).show()
            })


          //  val selectedCandidate : Int =  radioGroup!!.checkedRadioButtonId

            //radioBtn = findViewById(selectedCandidate)

            //val intent = Intent(this@Vote, CastVote::class.java)
            //intent.putExtra(EXTRA_STUDENT,  radioBtn.text.toString());
            //startActivity(intent)
            //finish()
        }
    }

}