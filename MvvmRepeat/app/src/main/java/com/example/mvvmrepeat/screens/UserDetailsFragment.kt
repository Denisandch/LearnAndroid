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
import com.example.mvvmrepeat.databinding.FragmentUserListBinding

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

        viewModel.userDetails.observe(viewLifecycleOwner) {
            binding.textViewUserName.text = it.user.name
            binding.textViewDescription.text = it.details
            if (it.user.photo.isNotBlank()) {
                Glide.with(this)
                    .load(it.user.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user_avatar)
                    .error(R.drawable.ic_user_avatar)
                    .into(binding.imageViewAvatar)
            } else {
                binding.imageViewAvatar.setImageResource(R.drawable.ic_user_avatar)
            }
        }

        binding.buttonDelete.setOnClickListener{
            viewModel.deleteUser()
            navigator().toast(R.string.user_deleted)
            navigator().goBack()
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