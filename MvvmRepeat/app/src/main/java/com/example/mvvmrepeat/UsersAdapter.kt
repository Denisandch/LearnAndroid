package com.example.mvvmrepeat

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmrepeat.databinding.ItemUserBinding
import com.example.mvvmrepeat.model.User
import com.example.mvvmrepeat.screens.UserListItem

interface UserActionListener {
    fun onUserMove(user: User, moveBy: Int)

    fun onUserDelete(user: User)

    fun onUserDetails(user: User)
    fun onUserFire(user: User)

}

class UsersAdapter(
    private val usersListener: UserActionListener
) : RecyclerView.Adapter<UsersViewHolder>(), View.OnClickListener {

    var users = listOf<UserListItem>()
        set(value) {
            val diffCallback = UsersDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)

        binding.buttonMore.setOnClickListener(this)

        return UsersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val userListItem = users[position]
        val user = userListItem.user
        val context = holder.itemView.context

        with(holder.binding) {
            holder.itemView.tag = user
            buttonMore.tag = user

            if(userListItem.isInProgress) {
                buttonMore.visibility = View.GONE
                progressBarItem.visibility = View.VISIBLE
                holder.binding.root.setOnClickListener(null)
            } else {
                buttonMore.visibility = View.VISIBLE
                progressBarItem.visibility = View.GONE
                holder.binding.root.setOnClickListener(this@UsersAdapter)
            }

            textViewCompany.text =
                if (user.company.isNotBlank()) user.company else context.getText(R.string.unemployed)
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
                usersListener.onUserDetails(user)
            }
        }
    }

    private fun showPopupMenu(view: View) {
        val context = view.context
        val popupMenu = PopupMenu(view.context, view)
        val user = view.tag as User
        val position = users.indexOfFirst { it.user.id == user.id }

        popupMenu.menu.add(0, ID_MOVE_UP, Menu.NONE, context.getString(R.string.move_up)).apply {
            isEnabled = position > 0
        }
        popupMenu.menu.add(0, ID_MOVE_DOWN, Menu.NONE, context.getString(R.string.move_down))
            .apply {
                isEnabled = position < users.size - 1
            }
        popupMenu.menu.add(0, ID_REMOVE, Menu.NONE, context.getString(R.string.remove))
        popupMenu.menu.add(0, ID_FIRE, Menu.NONE, context.getString(R.string.fire)).apply {
            isEnabled = user.company.isNotBlank()
        }

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_MOVE_UP -> usersListener.onUserMove(user, -1)
                ID_MOVE_DOWN -> usersListener.onUserMove(user, 1)
                ID_REMOVE -> usersListener.onUserDelete(user)
                ID_FIRE -> usersListener.onUserFire(user)
            }
            return@setOnMenuItemClickListener true
        }

        popupMenu.show()
    }

    companion object {
        private const val ID_MOVE_UP = 1
        private const val ID_MOVE_DOWN = 2
        private const val ID_REMOVE = 3
        private const val ID_FIRE = 4
    }
}

class UsersDiffCallback(
    private val oldList: List<UserListItem>,
    private val newList: List<UserListItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].user.id == newList[newItemPosition].user.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isInProgress == newList[newItemPosition].isInProgress
    }

}

class UsersViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)