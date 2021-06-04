package com.hamipishgaman.moviediscover.ui.movielist

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamipishgaman.moviediscover.R
import com.hamipishgaman.moviediscover.databinding.FragmentMovieListBinding
import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.usecase.movie.DateFilter
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MovieListFragment : Fragment(), View.OnClickListener {

    private val calendar = Calendar.getInstance()
    private var binding: FragmentMovieListBinding? = null
    private val viewModel: ListMovieViewModel by viewModels()
    private val datePickerTo = 0
    private val datePickerFrom = 1
    private lateinit var dateTo: String
    private lateinit var dateFrom: String
    private lateinit var fromDateSetListener: DatePickerDialog.OnDateSetListener
    private lateinit var toDateSetListener: DatePickerDialog.OnDateSetListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fromDateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView(datePickerFrom)
            }

        toDateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView(datePickerTo)
            }

        val movieAdapter = MovieAdapter(MovieListener { movie ->
            navigateToMovieDetail(movie)
        })

        binding!!.recyclerViewMovie.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter

        }

        binding!!.buttonFromDate.setOnClickListener(this)
        binding!!.buttonToDate.setOnClickListener(this)
        binding!!.buttonSetFilter.setOnClickListener(this)

        viewModel.loading.observe(viewLifecycleOwner, { loading ->

            if (loading) {
                binding!!.progressbar.visibility = View.VISIBLE
            } else {
                binding!!.progressbar.visibility = View.GONE
            }
        })

        viewModel.error.observe(viewLifecycleOwner, { throwable ->
            Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_SHORT).show()
        })

        viewModel.movies.observe(viewLifecycleOwner, {
            movieAdapter.submitList(it)
            val layoutManager = binding!!.recyclerViewMovie.layoutManager
            layoutManager?.smoothScrollToPosition(binding!!.recyclerViewMovie, null, 0)
        })

        viewModel.failure.observe(viewLifecycleOwner, { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun navigateToMovieDetail(movie: Model.Movie) {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_movieListFragment_to_movieDetailFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_from_date -> {
                openCalendar(datePickerFrom)
            }
            R.id.button_to_date -> {
                openCalendar(datePickerTo)
            }

            R.id.button_set_filter -> {
                val dateFilter = DateFilter(dateFrom, dateTo)
                viewModel.refresh(dateFilter)
            }
        }
    }


    private fun openCalendar(id: Int) {
        when (id) {
            datePickerFrom -> {
                DatePickerDialog(
                    requireContext(),
                    fromDateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            datePickerTo -> {
                DatePickerDialog(
                    requireContext(),
                    toDateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
    }

    private fun updateDateInView(id: Int) {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        when (id) {
            datePickerFrom -> {

                dateFrom = sdf.format(calendar.time)
                binding!!.buttonFromDate.text = getString(R.string.button_from_set_date, dateFrom)

            }
            datePickerTo -> {

                dateTo = sdf.format(calendar.time)
                binding!!.buttonToDate.text = getString(R.string.button_to_set_date, dateTo)
            }
        }
    }
}