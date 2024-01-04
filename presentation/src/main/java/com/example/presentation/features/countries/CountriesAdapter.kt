package com.example.presentation.features.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.countries.CountryResponse
import com.example.presentation.databinding.ItemCountryBinding
import com.example.presentation.extensions.click
import com.example.presentation.extensions.loadImage

class CountriesAdapter(private val clickOnCountry: (CountryResponse) -> Unit) :
    ListAdapter<CountryResponse, CountriesAdapter.WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(layoutInflater, parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val currentCountry = getItem(position)
        holder.bind(currentCountry)
    }

    inner class WordViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: CountryResponse) = with(binding) {
            root.click {
                clickOnCountry(country)
            }
            title.text = country.name.common
            imageFlag.loadImage(withCircleCrop = false, urlImage = country.flags.png)
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<CountryResponse>() {
        override fun areItemsTheSame(oldItem: CountryResponse, newItem: CountryResponse): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: CountryResponse,
            newItem: CountryResponse
        ): Boolean {
            return oldItem.name.common == newItem.name.common
        }
    }
}
