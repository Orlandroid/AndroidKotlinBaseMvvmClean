package com.example.presentation.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemHomeBinding
import com.example.presentation.extensions.click

class HomeAdapter(private val dataSet: List<Menus>, private val clickOnMenu: (Menus) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: Menus) {
            binding.menu.text = menu.name
            binding.root.click {
                clickOnMenu(menu)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemHomeBinding.inflate(layoutInflater, viewGroup, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    enum class Menus {
        Bored,
        Countries,
        Dogs
    }

}
