package com.example.weather.favoritesfragment.view


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.FavoriteItemsBinding
import com.example.weather.model.FavoriteModel


class FavoritesAdapter: ListAdapter<FavoriteModel, FavoritesAdapter.FavoritesViewHolder>(DaysDiffUtil()) {
    lateinit var context: Context
    lateinit var binding: FavoriteItemsBinding
    inner class FavoritesViewHolder(var binding:FavoriteItemsBinding): RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        context=parent.context
        val inflater:LayoutInflater=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding= FavoriteItemsBinding.inflate(inflater,parent,false)
        return FavoritesViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {

        val currentObj=getItem(position)
        holder.binding.countryNameView.text=currentObj.locality

    }
}

class DaysDiffUtil:DiffUtil.ItemCallback<FavoriteModel,>(){
    override fun areItemsTheSame(oldItem: FavoriteModel, newItem: FavoriteModel): Boolean {
        return oldItem.lat==newItem.lat
    }
    override fun areContentsTheSame(oldItem: FavoriteModel, newItem:FavoriteModel): Boolean {
        return oldItem.equals(newItem)
    }


}