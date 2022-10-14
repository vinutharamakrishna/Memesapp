package com.example.retrofitupdate.adapters

import com.example.retrofitupdate.model.Meme

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitupdate.R
import com.squareup.picasso.Picasso


class ViewPagerAdapter(val context: Context, val memes : List<Meme>): RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    class ViewPagerViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val imageview : ImageView = itemView.findViewById<ImageView>(R.id.memeimage)
        val title : TextView = itemView.findViewById(R.id.memeName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memes_item,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curMeme = memes[position]



        // Picasso.with(holder).load(curCard.imageURL).into(imageview)
        // holder.imageview.setImageResource(curMeme.url)
        Picasso.with(context).load(curMeme.url).into(holder.imageview)
        holder.title.text = curMeme.name


    }




    override fun getItemCount(): Int {
        return memes.size
    }
}



