package com.example.weather.favoritesfragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.FragmentFavoritesBinding
import com.example.weather.homefragment.view.DaysAdapter
import com.example.weather.model.FavoriteModel


class FavoritesFragment : Fragment() {


    lateinit var binding: FragmentFavoritesBinding
    var favoritesList:MutableList<FavoriteModel> = mutableListOf()
lateinit var favoritesAdapter: FavoritesAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        favoritesAdapter= FavoritesAdapter()
binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

favoritesList.add(FavoriteModel("Masr","1".toDouble(),"2".toDouble()))
        binding.favoritesRecyclerView.apply {
            this.adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.VERTICAL
            }
        }
        println("el lis"+favoritesList.size)
        favoritesAdapter.submitList(favoritesList)

        binding.addFavoriteButton.setOnClickListener {
            navigateToMap()
        }
    }

    fun navigateToMap()
    {

       val action=FavoritesFragmentDirections.actionFavoritesFragment2ToMapsFragment("","")
      Navigation.findNavController(requireActivity(), R.id.fragmentView).navigate(action)
    }
}