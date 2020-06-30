package com.svt.training.mindrockers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.svt.training.mindrockers.R
import com.svt.training.mindrockers.api.ApiResponse
import com.svt.training.mindrockers.api.RetrofitBuilder
import com.svt.training.mindrockers.api.User
import com.svt.training.mindrockers.repository.UserRepository
import kotlinx.android.synthetic.main.fragment_user_list.*

/**
 * A simple [Fragment] subclass.
 */
class UserListFragment : Fragment() {

    private lateinit var adapter : UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_user_list, container, false)

        val userRepository = UserRepository(RetrofitBuilder.apiService)

        val viewModelFactory = UserListViewModelFactory(userRepository)

        val userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        adapter = UserListAdapter(arrayListOf())
        recyclerView.adapter = adapter

        userViewModel.userList.observe(this, Observer {
            it?.let {
                apiResponse ->
                when(apiResponse.status){
                    ApiResponse.Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        apiResponse.data?.let { users -> updateList(users) }
                    }
                    ApiResponse.Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Snackbar.make(view, apiResponse.message.toString(), Snackbar.LENGTH_SHORT).show()
                    }
                    ApiResponse.Status.LOADING -> {
                        recyclerView.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })

        return view
    }

    private fun updateList(users: List<User>){
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}
