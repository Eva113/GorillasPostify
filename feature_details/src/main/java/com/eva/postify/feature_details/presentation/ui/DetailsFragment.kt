package com.eva.postify.feature_details.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.eva.postify.data.model.PostDetails
import com.eva.postify.feature_details.R
import com.eva.postify.feature_details.databinding.FragmentDetailsBinding
import com.eva.postify.feature_details.presentation.viewmodel.DetailsViewModel
import com.eva.postify.feature_details.presentation.viewmodel.DetailsViewModel.DetailsState.ErrorState
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private val viewModel: DetailsViewModel by viewModel()

    private lateinit var binding: FragmentDetailsBinding

    private var id: String = "0"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        observeStates()
        id = requireArguments().getString("postId", "-1")
        viewModel.onDetailsOpened(id)
        return binding.root
    }

    private fun observeStates() {
        viewModel.detailsState.observe(viewLifecycleOwner, {
            when (it) {
                is ErrorState -> showError(it.error)
                is DetailsViewModel.DetailsState.SuccessState -> {
                    hideLoading()
                    showDetails(it.details)
                }
                is DetailsViewModel.DetailsState.LoadingState -> showLoading()
                else -> {
                }
            }
        })
    }

    private fun showError(error: Throwable) {
        AlertDialog.Builder(requireContext())
            .setMessage(resources.getString(R.string.error_retry))
            .setPositiveButton(R.string.retry) { dialog, btn ->
                viewModel.onDetailsOpened(id)
            }
            .create()
            .show()
        println(error)
    }


    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loading.visibility = View.GONE
    }

    private fun showDetails(details: PostDetails) {
        binding.detailsUser.text = details.name
        binding.detailsUsername.text = details.username
        binding.detailsTitle.text = details.postTitle
        binding.detailsDescription.text = details.postDescription
    }
}