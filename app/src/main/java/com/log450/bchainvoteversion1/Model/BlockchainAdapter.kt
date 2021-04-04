package com.log450.bchainvoteversion1.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.log450.bchainvoteversion1.R

class BlockchainAdapter (private val blockChain:ArrayList<Block>) : RecyclerView.Adapter<BlockchainAdapter.MyHolder>(){


    override fun onBindViewHolder(myHolder: MyHolder, position: Int) {
        val block: Block = blockChain[position]
        myHolder.bind(block)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val blockchainView = inflater.inflate(R.layout.item_blockhain, parent, false)
        return MyHolder(blockchainView)
    }


    override fun getItemCount(): Int {
        return blockChain.size
    }

    //The interface implemented for when an item (student) as been clicked.
    interface OnItemClickListener{
        fun onItemClicked(block: Block)
    }

    //How each item will be displayed.
    class MyHolder (itemView: View): RecyclerView.ViewHolder(itemView)
    {
        fun bind(block: Block){
            itemView.findViewById<TextView>(R.id.hash).text = block.getHash()
            itemView.findViewById<TextView>(R.id.data).text = block.getData()

        }
    }

}