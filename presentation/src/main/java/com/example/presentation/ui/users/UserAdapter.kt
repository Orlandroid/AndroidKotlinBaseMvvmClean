package com.example.presentation.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.remote.User
import com.example.presentation.extensions.click
import com.example.presentation.databinding.ItemUserBinding


class UserAdapter(private val clickOnUser: (User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var listOfCategories: List<User> = arrayListOf()

    fun setData(lista: List<User>) {
        listOfCategories = lista
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, clickOnUser: (User) -> Unit) = with(binding) {
            root.click {
                clickOnUser(user)
            }
            tvNombre.text = user.name.fullName()
            tvCorreo.text = user.email
            tvNumero.text = user.number.toString()
            tvId.text = user._id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfCategories[position], clickOnUser)
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }


}
