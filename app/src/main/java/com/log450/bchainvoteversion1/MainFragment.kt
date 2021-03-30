package com.log450.bchainvoteversion1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Repository.Blockchain
import com.log450.bchainvoteversion1.Repository.FireBaseRepository

class MainFragment : androidx.fragment.app.Fragment() {

    private var fbase: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
       ): View? {
         return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FireBaseRepository()

        view.findViewById<Button>(R.id.loginPanelBtn).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_login)
        }

        view.findViewById<Button>(R.id.RegisterPanelBtn).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_register)
        }

    }


}