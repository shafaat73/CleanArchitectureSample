package com.phaedra.cleanarchitecturesample.presentation.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.phaedra.cleanarchitecturesample.R
import com.phaedra.cleanarchitecturesample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels()

    @Inject
    lateinit var adapter: PostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupRecyclerView()
        subscribeObservers()

    }

    private fun setupRecyclerView() {
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
        binding.rvPosts.adapter = adapter
        adapter.listener = { view, it, id ->

        }
    }

    private fun subscribeObservers() {

        viewModel.postList.observe(this, Observer {
            if (it.isLoading) {
                binding.animationLoading.visibility = View.VISIBLE
            } else if (it.posts.isNullOrEmpty().not()) {
                adapter.addItems(it.posts)
                binding.animationLoading.visibility = View.GONE
            } else if (it.error.isNullOrEmpty().not()) {
                binding.animationLoading.visibility = View.GONE
                Toast.makeText(this,"${it.error}",Toast.LENGTH_LONG).show()
            }

        })

    }

}