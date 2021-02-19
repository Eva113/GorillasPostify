package com.eva.postify.feature_list.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.eva.postify.data.model.Post
import com.eva.postify.feature_list.R
import com.eva.postify.feature_list.databinding.FragmentPostsBinding
import com.eva.postify.feature_list.presentation.ui.adapter.PostsAdapter
import com.eva.postify.feature_list.presentation.viewmodel.PostsViewModel
import com.eva.postify.feature_list.presentation.viewmodel.PostsViewModel.PostsAction.None
import com.eva.postify.feature_list.presentation.viewmodel.PostsViewModel.PostsAction.OpenDetailsAction
import com.eva.postify.feature_list.presentation.viewmodel.PostsViewModel.PostsState.*
import com.eva.postify.navigator.Navigator
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : Fragment() {

    private val viewModel: PostsViewModel by viewModel()
    private val navigator: Navigator by inject()

    private lateinit var binding: FragmentPostsBinding
    private lateinit var adapter: PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PostsAdapter()
        binding.postsRecylerView.apply {
            adapter = this@PostsFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        observeStates()
        observeActions()
        setListeners()
        viewModel.onViewReady()
    }

    private fun setListeners() {
        adapter.onItemClicked = viewModel::onItemClicked
        adapter.onLoadMore = viewModel::onBottomReached
        binding.swipeRefreshLayout.setOnRefreshListener(viewModel::onRefreshClicked)
    }

    private fun observeStates() {
        viewModel.postsState.observe(viewLifecycleOwner, {
            when (it) {
                is ErrorState -> showError(it.error)
                is SuccessState -> showArticles(it.data)
                is LoadingState -> showLoading()
            }
        })
    }

    private fun observeActions() {
        viewModel.postsAction.observe(viewLifecycleOwner, {
            when (it) {
                is OpenDetailsAction -> openDetailsFragment(it.post)
                None -> Unit
            }
        })
    }

    private fun openDetailsFragment(post: Post) {
        navigator.navigateTo(requireActivity(), Navigator.Destination.PostDetails(post.id))
    }

    private fun showError(error: Throwable) {
        hideLoading()
        Snackbar.make(binding.postsRecylerView, R.string.error_retry, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.retry, { viewModel.onRefreshClicked() })
            .show()
    }

    private fun showArticles(articles: List<Post>) {
        hideLoading()
        adapter.setPosts(articles)
    }

    private fun showLoading() {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    private fun hideLoading() {
        binding.swipeRefreshLayout.isRefreshing = false
    }
}