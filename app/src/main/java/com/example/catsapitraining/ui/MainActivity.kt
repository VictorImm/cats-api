package com.example.catsapitraining.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catsapitraining.adapter.CatNameAdapter
import com.example.catsapitraining.databinding.ActivityMainBinding
import com.example.catsapitraining.viewmodel.CatViewModel

class MainActivity : AppCompatActivity() {

    // binding
    private lateinit var binding: ActivityMainBinding

    // widgets
    private lateinit var rvCat: RecyclerView
    private lateinit var loading: ProgressBar

    // viewModel
    private lateinit var viewModel: CatViewModel

    // adapter
    private lateinit var adapter: CatNameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(CatViewModel::class.java)

        // init widgets
        loading = binding.progressBar

        viewModel.searchCat()

        rvCat = binding.rvCat
        rvCat.layoutManager = LinearLayoutManager(this)
        rvCat.setHasFixedSize(true)

        adapter = CatNameAdapter(this@MainActivity)
        adapter.notifyDataSetChanged()
        rvCat.adapter = adapter

        viewModel.getCat().observe(this) {
            if (it != null) {
                adapter.setList(it)
            }
        }
    }
}