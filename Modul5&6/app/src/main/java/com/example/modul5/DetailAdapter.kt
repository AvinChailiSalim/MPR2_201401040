package com.example.modul5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class DetailAdapter(private val listAnime: ArrayList<String>)
    :RecyclerView.Adapter<DetailAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvItemFact.text = listAnime[position]
    }

    class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val tvItemFact : TextView = view.findViewById(R.id.tv_item_detail)
    }

    override fun getItemCount(): Int {
        return listAnime.size
    }

}