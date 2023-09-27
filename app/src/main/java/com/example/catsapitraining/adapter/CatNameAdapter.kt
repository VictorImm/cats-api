package com.example.catsapitraining.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.catsapitraining.R
import com.example.catsapitraining.data.Cat
import com.example.catsapitraining.databinding.RowCatBinding
import com.example.catsapitraining.ui.catview.CatListFragmentDirections

class CatNameAdapter(
    private val context: Context,
    private val navController: NavController
): RecyclerView.Adapter<CatNameAdapter.CatViewHolder>() {

    private val list = ArrayList<Cat>()

    fun setList(cats: ArrayList<Cat>) {
        list.clear()
        list.addAll(cats)
        notifyDataSetChanged()
    }

    inner class CatViewHolder(
        catView: View
    ): RecyclerView.ViewHolder(catView) {
        val tvPicture: ImageView = itemView.findViewById(R.id.tv_picture)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.row_cat,
                    parent,
                    false
                )

        return CatViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CatNameAdapter.CatViewHolder, position: Int) {
        val cat = list[position]

        Glide.with(context)
            .load(cat.image_link)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(holder.tvPicture)

        holder.tvName.text = cat.name

        holder.itemView.setOnClickListener {
            val action = CatListFragmentDirections.actionCatListFragmentToCatDetailFragment(cat)
            navController.navigate(action)
        }
    }
}