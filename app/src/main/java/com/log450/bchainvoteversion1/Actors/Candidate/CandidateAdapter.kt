package com.log450.bchainvoteversion1.Actors.Candidate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.log450.bchainvoteversion1.R

class CandidateAdapter(private val candidateList:ArrayList<Candidate>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<CandidateAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyHolder {
        val context = parent.context
        val candidateView = LayoutInflater.from(context).inflate(R.layout.item_candidate, parent, false)
        return MyHolder(candidateView)
    }

    override fun getItemCount(): Int {
        return candidateList.size
    }

    override fun onBindViewHolder(myHolder: MyHolder, position: Int) {
        val candidate: Candidate = candidateList[position]
        myHolder.bind(candidate,itemClickListener)
    }

    interface OnItemClickListener{
        fun onItemClicked(candidate: Candidate)
    }

    class MyHolder (itemView:View): RecyclerView.ViewHolder(itemView)
    {
        fun bind(candidate: Candidate, clickListener: OnItemClickListener){
            itemView.findViewById<TextView>(R.id.CandidateName).text = candidate.getName()
            itemView.findViewById<TextView>(R.id.CandidateVaccine).text = candidate.getVaccine().toString()
            itemView.setOnClickListener {
                clickListener.onItemClicked(candidate)
            }
        }
    }
}