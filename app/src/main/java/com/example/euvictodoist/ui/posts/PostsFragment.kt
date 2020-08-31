package com.example.euvictodoist.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.euvictodoist.databinding.FragmentPostsBinding
import com.example.euvictodoist.viewmodels.PostsViewModel
import org.koin.android.ext.android.inject

class PostsFragment : Fragment() {

    val postsViewModel : PostsViewModel by inject()
    lateinit var binding: FragmentPostsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPostsBinding.inflate(LayoutInflater.from(context), container, false)
        binding.apply {
            lifecycleOwner = this@PostsFragment
            viewModel = postsViewModel // Setting the .xml ViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        binding.loading.visibility = View.VISIBLE
        postsViewModel.requestPostsList() // Requests the TODOS list from the API.
        postsViewModel.postList.observe({lifecycle}) {
            postsViewModel.setPostList(it)
            binding.rvPostList.scheduleLayoutAnimation()
            binding.loading.visibility = View.GONE
        }
    }
}