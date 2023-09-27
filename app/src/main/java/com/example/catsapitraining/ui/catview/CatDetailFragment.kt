package com.example.catsapitraining.ui.catview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.catsapitraining.R
import com.example.catsapitraining.data.Cat
import com.example.catsapitraining.databinding.FragmentCatDetailBinding

class CatDetailFragment : Fragment() {

    private lateinit var catData: Cat

    // binding
    private lateinit var binding: FragmentCatDetailBinding

    // widgets
    private lateinit var tvPhoto: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvOrigin: TextView
    private lateinit var tvFriend: TextView
    private lateinit var tvIntel: TextView
    private lateinit var tvGroom: TextView

    // view model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            catData = CatDetailFragmentArgs.fromBundle(it).catData
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val friendScore = (((
            catData.children_friendly +
            catData.family_friendly +
            catData.other_pets_friendly
        ).toDouble())/3.0)

        // init widget
        tvPhoto = binding.tvPhoto
        tvName = binding.tvName
        tvOrigin = binding.tvOrigin
        tvFriend = binding.tvFriend
        tvIntel = binding.tvIntel
        tvGroom = binding.tvGroom

        tvPhoto.apply {
            Glide.with(context)
                .load(catData.image_link)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(tvPhoto)
        }
        tvName.text = catData.name
        tvOrigin.text = catData.origin
        tvFriend.text = String.format("%.1f", friendScore)
        tvIntel.text = catData.intelligence.toDouble().toString()
        tvGroom.text = catData.grooming.toDouble().toString()
    }
}