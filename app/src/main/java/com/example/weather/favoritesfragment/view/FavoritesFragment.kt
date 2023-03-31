package com.example.weather.favoritesfragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.database.ConcreteLocalSource
import com.example.weather.databinding.FragmentFavoritesBinding
import com.example.weather.favoritesfragment.viewmodel.FavoritesViewModel
import com.example.weather.favoritesfragment.viewmodel.FavoritesViewModelFactory
import com.example.weather.model.FavoriteModel
import com.example.weather.model.Repository
import com.example.weather.network.WeatherClient


class FavoritesFragment : Fragment() {


    lateinit var binding: FragmentFavoritesBinding
lateinit var favoritesAdapter: FavoritesAdapter

    val args: FavoritesFragmentArgs by navArgs()

    private lateinit var favoriteViewModel:FavoritesViewModel
    private lateinit var favoriteViewModelFactory:FavoritesViewModelFactory




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
//if(args.locality.equals("0"))
         //   println("mama ya sety ${args.latitude}")
        favoritesAdapter= FavoritesAdapter()
binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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


        if(!args.locality.equals("0")) {
            println("ana keda m3aya value")
            println("dol ahom ${args.latitude}")
            favoriteViewModel.insetToFavorites(
                FavoriteModel(
                    args.locality,
                    args.latitude.toDouble(),
                    args.longitude.toDouble()
                )
            )

            favoritesAdapter.notifyDataSetChanged()
        }
      //   favoriteViewModel.insetToFavorites(FavoriteModel("omek","3".toDouble(),"5".toDouble()))


        favoriteViewModel.favorites.observe(requireActivity())
        {
            if(it!=null)
                favoritesAdapter.submitList(it)
        }
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