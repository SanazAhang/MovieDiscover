package com.hamipishgaman.moviediscover.ui.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hamipishgaman.moviediscover.databinding.FragmentMovieDetailBinding
import com.hamipishgaman.moviediscover.domain.model.MovieDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    private var binding: FragmentMovieDetailBinding? = null
    private lateinit var movie:MovieDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val bundle = arguments
        if (bundle == null) {
            Log.e("MovieDetailFragment", "Did not receive movie information")
            return
        }

        val args = MovieDetailFragmentArgs.fromBundle(bundle)
        movie = args.movie
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.movie = movie

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding!!.root

    }
    
}