package com.example.mvvmrepeat.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmrepeat.UserActionListener
import com.example.mvvmrepeat.UsersAdapter
import com.example.mvvmrepeat.databinding.FragmentUserListBinding
import com.example.mvvmrepeat.model.User
import com.example.mvvmrepeat.model.UsersListener
import com.example.mvvmrepeat.tasks.EmptyResult
import com.example.mvvmrepeat.tasks.ErrorResult
import com.example.mvvmrepeat.tasks.PendingResult
import com.example.mvvmrepeat.tasks.SuccessResult

class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private lateinit var adapter: UsersAdapter

    private val viewModel: UsersListViewModel by viewModels { factory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        adapter = UsersAdapter(viewModel)

        viewModel.users.observe(viewLifecycleOwner) {
            hideAll()
            when (it) {
                is SuccessResult -> {
                    binding.recycler.visibility = View.VISIBLE
                    adapter.users = it.data
                }

                is ErrorResult -> {
                    binding.containerTryAgain.visibility = View.VISIBLE
                }

                is PendingResult -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is EmptyResult -> {
                    binding.textViewNoUsers.visibility = View.VISIBLE
                }
            }
        }

        viewModel.actionShowDetails.observe(viewLifecycleOwner){
            it.getValue()?.let{
                user ->navigator().showDetails(user)
            }
        }

        viewModel.actionShowToast.observe(viewLifecycleOwner){
            it.getValue()?.let{
                    messageRes -> navigator().toast(messageRes)
            }
        }

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = adapter

        val itemAnimator = binding.recycler.itemAnimator
        if (itemAnimator is DefaultItemAnimator) {
            itemAnimator.supportsChangeAnimations = false
        }

        return binding.root
    }

    private fun hideAll() {
        binding.recycler.visibility = View.GONE
        binding.containerTryAgain.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.textViewNoUsers.visibility = View.GONE
    }

}