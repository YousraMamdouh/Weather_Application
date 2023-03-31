package com.example.weather.favoritesfragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.currentWeather.viewModel.CurrentWeatherViewModelFactory
import com.example.weather.database.ConcreteLocalSource
import com.example.weather.databinding.FragmentFavoritesBinding
import com.example.weather.databinding.FragmentHomePageBinding
import com.example.weather.favoritesfragment.viewmodel.FavoritesViewModel
import com.example.weather.favoritesfragment.viewmodel.FavoritesViewModelFactory
import com.example.weather.homefragment.view.DaysAdapter
import com.example.weather.homefragment.view.HomePageFragmentArgs
import com.example.weather.homefragment.viewmodel.CurrentWeatherViewModel
import com.example.weather.model.FavoriteModel
import com.example.weather.model.Repository
import com.example.weather.network.WeatherClient


class FavoritesFragment : Fragment() {


    lateinit var binding: FragmentFavoritesBinding
    lateinit var favoritesList:LiveData<List<FavoriteModel>>
lateinit var favoritesAdapter: FavoritesAdapter

    val args: FavoritesFragmentArgs by navArgs()

    private lateinit var favoriteViewModel:FavoritesViewModel
    private lateinit var favoriteViewModelFactory:FavoritesViewModelFactory




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        favoritesAdapter= FavoritesAdapter()
binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        favoritesList=favoriteViewModel.getAllFavorites()
//favoritesAdapter.submitList(favoritesList)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//favoritesList.add(FavoriteModel("Masr","1".toDouble(),"2".toDouble()))

        binding.favoritesRecyclerView.apply {
            this.adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.VERTICAL
            }
        }

       favoriteViewModelFactory = FavoritesViewModelFactory(
            Repository.getInstance(
                WeatherClient.getInstance(),
                ConcreteLocalSource(requireContext()), requireActivity()
            )
        )

        favoriteViewModel = ViewModelProvider(
            this,
            favoriteViewModelFactory
        ).get(FavoritesViewModel::class.java)

//        println("el lis"+favoritesList.size)
//        favoritesAdapter.submitList(favoritesList)


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