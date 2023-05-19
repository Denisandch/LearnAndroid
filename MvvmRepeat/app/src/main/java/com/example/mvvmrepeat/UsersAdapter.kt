package com.example.mvvmrepeat

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmrepeat.databinding.ItemUserBinding
import com.example.mvvmrepeat.model.User

interface UserActionListener {
    fun onUserMove(user: User, moveBy: Int)

    fun onUserDelete(user: User)

    fun onUserDetails(user: User)
}

class UsersAdapter(
    private val usersListener: UserActionListener
) : RecyclerView.Adapter<UsersViewHolder>(), View.OnClickListener {

    var users = listOf<User>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.buttonMore.setOnClickListener(this)

        return UsersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding) {
            holder.itemView.tag = user
            buttonMore.tag = user

            textViewCompany.text = user.company
            textViewUserName.text = user.name

            if (user.photo.isNotBlank()) {
                Glide.with(imageViewAvatar.context)
                    .load(user.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user_avatar)
                    .error(R.drawable.ic_user_avatar)
                    .into(imageViewAvatar)
            } else {
                imageViewAvatar.setImageResource(R.drawable.ic_user_avatar)
            }
        }
    }

    override fun onClick(view: View) {
        val user = view.tag as User

        when (view.id) {
            R.id.button_more -> {
                showPopupMenu(view)
            }

            else -> {
                showPopupMenu(view)
                usersListener.onUserDetails(user)
            }
        }
    }

    private fun showPopupMenu(view: View) {
        val context = view.context
        val popupMenu = PopupMenu(view.context, view)
        val user = view.tag as User
        val position = users.indexOfFirst { user.id == it.id }

        popupMenu.menu.add(0, ID_MOVE_UP, Menu.NONE, context.getString(R.string.move_up)).apply {
            isEnabled = position > 0
        }
        popupMenu.menu.add(0, ID_MOVE_DOWN, Menu.NONE, context.getString(R.string.move_down))
            .apply {
                isEnabled = position < users.size - 1
            }
        popupMenu.menu.add(0, ID_REMOVE, Menu.NONE, context.getString(R.string.remove))

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_MOVE_UP -> usersListener.onUserMove(user, -1)
                ID_MOVE_DOWN -> usersListener.onUserMove(user, 1)
                ID_REMOVE -> usersListener.onUserDelete(user)
            }
            return@setOnMenuItemClickListener true
        }

        popupMenu.show()
    }

    companion object {
        private const val ID_MOVE_UP = 1
        private const val ID_MOVE_DOWN = 2
        private const val ID_REMOVE = 3
    }
}

class UsersViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)