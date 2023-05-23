package com.example.mvvmrepeat.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.mvvmrepeat.R
import com.example.mvvmrepeat.databinding.FragmentUserDetailsBinding
import com.example.mvvmrepeat.tasks.SuccessResult

class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    private val viewModel: UserDetailsViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadUser(requireArguments().getLong(ARG_USER_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)

        viewModel.actionGoBack.observe(viewLifecycleOwner) {
            it.getValue()?.let { user ->
                navigator().goBack()
            }
        }

        viewModel.actionShowToast.observe(viewLifecycleOwner) {
            it.getValue()?.let { messageRes ->
                navigator().toast(messageRes)
            }
        }

        viewModel.state.observe(viewLifecycleOwner) {
            binding.contentContainer.visibility = if (it.showContent) {
                val userDetails = (it.userDetailsResult as SuccessResult).data
                binding.textViewUserName.text = userDetails.user.name
                binding.textViewDescription.text = userDetails.details
                if (userDetails.user.photo.isNotBlank()) {
                    Glide.with(this)
                        .load(userDetails.user.photo)
                        .circleCrop()
                        .placeholder(R.drawable.ic_user_avatar)
                        .error(R.drawable.ic_user_avatar)
                        .into(binding.imageViewAvatar)
                } else {
                    binding.imageViewAvatar.setImageResource(R.drawable.ic_user_avatar)
                }
                View.VISIBLE
            } else {
                View.GONE
            }
            binding.progressBar.visibility = if (it.showProgress) View.VISIBLE else View.GONE
            binding.buttonDelete.isEnabled = it.enableDeleteButton
        }

        binding.buttonDelete.setOnClickListener {
            viewModel.deleteUser()
        }

        return binding.root
    }


    companion object {

        private const val ARG_USER_ID = "ARG_USER_ID"

        fun newInstance(userId: Long): UserDetailsFragment {
            val fragment = UserDetailsFragment()
            fragment.arguments = bundleOf(ARG_USER_ID to userId)
            return fragment
        }
    }
}