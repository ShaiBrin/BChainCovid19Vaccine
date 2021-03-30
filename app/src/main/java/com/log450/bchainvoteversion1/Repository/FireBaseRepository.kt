package com.log450.bchainvoteversion1.Repository

import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Model.Person



class FireBaseRepository {
    private var fbase: FirebaseFirestore = FirebaseFirestore.getInstance()


    init {

        val people = arrayListOf(
            Person(1, "Frank", "Lucas", false,"FrankL", false),
            Person(2, "Vito", "Corleone", false,"VitoC", false),
            Person(3, "Al", "Capone", false,"AlC", false),
            Person(4, "Donald", "Carducci", false,"DonaldC", false),
            Person(5, "Al", "Pacino", false,"AlP", false)
        )

        for (person in people) {
            fbase.collection("people").document(person.getId().toString()).set(person)
        }


    }
}