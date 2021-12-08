package com.example.euvictodoist.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.euvictodoist.adapters.PostsAdapter
import com.example.euvictodoist.bindingadapter.prepareAdapter
import com.example.euvictodoist.databinding.FragmentPostsBinding
import com.example.euvictodoist.models.PostResponse
import com.example.euvictodoist.ui.comments.CommentsActivity
import com.example.euvictodoist.viewmodels.PostsViewModel
import org.koin.android.ext.android.inject

class PostsFragment : Fragment(), PostsAdapter.PostsAdapterCallback {

    private val postsViewModel : PostsViewModel by inject()
    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private var postsAdapter = PostsAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPostsBinding.inflate(LayoutInflater.from(context), container, false)
        binding.apply {
            lifecycleOwner = this@PostsFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(id: Int) {
        startActivity(CommentsActivity.openCommentsActivity(requireContext(), id))
    }

    private fun initFragment() {
        binding.loading.visibility = View.VISIBLE
        postsViewModel.requestPostsList() // Requests the TODOS list from the API.
        postsViewModel.postList.observe(viewLifecycleOwner, Observer {
            setupData(it)
            binding.loading.visibility = View.GONE
        })
    }

    private fun setupData(data: ArrayList<PostResponse>) {
        postsAdapter.apply {
            setData(data)
        }
        binding.rvPostList.prepareAdapter(postsAdapter)
        binding.rvPostList.scheduleLayoutAnimation()
    }
}