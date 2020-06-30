package com.svt.training.mindrockers.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.svt.training.mindrockers.R
import com.svt.training.mindrockers.api.User
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListAdapter(private val userList : ArrayList<User>) : RecyclerView.Adapter<UserListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false))
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(user: User){
            itemView.apply {
                textViewUserName.text = user.userName
                textViewUserEmail.text = user.emailId
            }
        }
    }

    fun addUsers(users: List<User>) {
        this.userList.apply {
            clear()
            addAll(users)
        }
    }
}