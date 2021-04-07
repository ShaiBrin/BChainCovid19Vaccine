package com.log450.bchainvoteversion1.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.Observer
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.Utils.Functions.Companion.validateCredentials
import com.log450.bchainvoteversion1.ViewModel.ViewModelCandidate

class CandidateActivity : AppCompatActivity() {
    private lateinit var mCandidateName: EditText
    private lateinit var radioGroup : RadioGroup
    private lateinit var radioBtn : RadioButton
    private lateinit var submitBtn : Button
    private val viewModel = ViewModelCandidate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate)
        mCandidateName = findViewById(R.id.companyName)
        radioGroup = findViewById(R.id.radioGroupVaccine)
        submitBtn = findViewById(R.id.submitFormBtn)

        submitBtn.setOnClickListener {
            val vaccine : Int = radioGroup!!.checkedRadioButtonId
            radioBtn = findViewById(vaccine)
            viewModel.submitCandidateApplication(validateCredentials(mCandidateName,false), radioBtn.text.toString())
            viewModel.isApplicationCreated().observe(this, Observer {
                if(it){
                    Toast.makeText(this, "Your application has been submitted", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this, "Your application has not been submitted", Toast.LENGTH_LONG).show()
                }
            })
            finish()
        }
    }
}