package com.example.catsapitraining.ui.catview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catsapitraining.adapter.CatNameAdapter
import com.example.catsapitraining.databinding.FragmentCatListBinding
import com.example.catsapitraining.viewmodel.CatViewModel


class CatListFragment : Fragment() {

    // binding
    private lateinit var binding: FragmentCatListBinding

    // widgets
    private lateinit var rvCat: RecyclerView
    private lateinit var loading: ProgressBar

    // viewModel
    private lateinit var viewModel: CatViewModel

    // adapter
    private lateinit var adapter: CatNameAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init widget
        loading = binding.progressBar

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(CatViewModel::class.java)
        viewModel.searchCat()

        rvCat = binding.rvCat
        rvCat.layoutManager = LinearLayoutManager(context)
        rvCat.setHasFixedSize(true)

        adapter = CatNameAdapter(
            this.requireContext(),
            this.findNavController()
        )
        adapter.notifyDataSetChanged()
        rvCat.adapter = adapter

        viewModel.getCat().observe(this.viewLifecycleOwner) {
            if (it != null) {
                adapter.setList(it)
            }
        }

    }
}