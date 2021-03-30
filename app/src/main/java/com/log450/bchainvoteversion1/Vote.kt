package com.log450.bchainvoteversion1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

import com.log450.bchainvoteversion1.Utils.Constants.EXTRA_STUDENT

class Vote : AppCompatActivity() {

    private lateinit var radioGroup : RadioGroup
    private lateinit var radioBtn : RadioButton
    private lateinit var castVoteBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0,0)
        setContentView(R.layout.activity_vote)

        radioGroup = findViewById(R.id.radioGroup)
        castVoteBtn = findViewById(R.id.voteBtn)


        castVoteBtn.setOnClickListener {
            val selectedCandidate : Int =  radioGroup!!.checkedRadioButtonId

            radioBtn = findViewById(selectedCandidate)

            val intent = Intent(this@Vote, CastVote::class.java)
            intent.putExtra(EXTRA_STUDENT,  radioBtn.text.toString());
            startActivity(intent)
            finish()
        }

    }




}