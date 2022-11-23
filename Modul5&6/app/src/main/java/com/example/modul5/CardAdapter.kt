package com.example.modul5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CardAdapter(private val listCard: ArrayList<Card>):
    RecyclerView.Adapter<CardAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).
            inflate(R.layout.item_card, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.nameCard.text = listCard[position].id.toString()
        holder.deskCard.text = listCard[position].name.toString()

        val img = listCard[position].img

        Picasso.get().load(img).into(holder.imgCard)

        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listCard[holder.adapterPosition])
        }

    /*val (name,description,photo) = listCard[position]
        holder.imgCard.setImageResource(photo)
        holder.nameCard.text = name
        holder.deskCard.text = description
        */
    }

    override fun getItemCount(): Int = listCard.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imgCard  : ImageView = itemView.findViewById(R.id.card_image)
        var nameCard : TextView = itemView.findViewById(R.id.card_id)
        var deskCard : TextView = itemView.findViewById(R.id.card_text)
    }


    interface OnItemClickCallback{
        fun onItemClicked(data:Card)
    }

}