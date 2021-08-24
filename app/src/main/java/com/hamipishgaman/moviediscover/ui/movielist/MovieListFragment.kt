package com.hamipishgaman.moviediscover.ui.movielist

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamipishgaman.moviediscover.MovieApplication
import com.hamipishgaman.moviediscover.R
import com.hamipishgaman.moviediscover.databinding.FragmentMovieListBinding
import com.hamipishgaman.moviediscover.di.factory.ViewModelFactory
import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.model.MovieDetail
import com.hamipishgaman.moviediscover.domain.usecase.movie.DateFilter
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class MovieListFragment : Fragment(), View.OnClickListener {

    private val calendar = Calendar.getInstance()
    private var binding: FragmentMovieListBinding? = null

    //    private val viewModel: ListMovieViewModel by viewModels()
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: ListMovieViewModel
    private val datePickerTo = 0
    private val datePickerFrom = 1
    private val movieAdapter = MovieAdapter()
    private var dateTo: String? = null
    private var dateFrom: String? = null
    private lateinit var fromDateSetListener: DatePickerDialog.OnDateSetListener
    private lateinit var toDateSetListener: DatePickerDialog.OnDateSetListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (apllication as MovieApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[ListMovieViewModel::class.java]
        binding!!.progressbar.visibility = View.GONE
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

        binding!!.recyclerViewMovie.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter

        }

        binding!!.buttonFromDate.setOnClickListener(this)
        binding!!.buttonToDate.setOnClickListener(this)
        binding!!.buttonSetFilter.setOnClickListener(this)


        movieAdapter.setOnItemClickListener(object : MovieAdapter.ClickListener {
            override fun onClick(movie: Model.Movie) {
                navigateToMovieDetail(movie)
            }

        })

        viewModel.movies.observe(viewLifecycleOwner, ::onGetMovie)

        viewModel.loading.observe(viewLifecycleOwner, ::onLoading)

        viewModel.error.observe(viewLifecycleOwner, ::onError)

        viewModel.failure.observe(viewLifecycleOwner, ::onFailure)

        viewModel.dateFrom.observe(viewLifecycleOwner, ::onsetFromDate)

        viewModel.dateTo.observe(viewLifecycleOwner, ::onsetToDate)

    }

    private fun onsetFromDate(fromDate: String?) {
        binding?.buttonFromDate?.text = fromDate
    }

    private fun onsetToDate(toDate: String?) {
        binding?.buttonToDate?.text = toDate
    }

    private fun onGetMovie(event: ConsumableValue<List<Model.Movie>>) {
        event.consume {
            movieAdapter.submitList(it)
            val layoutManager = binding?.recyclerViewMovie?.layoutManager
            layoutManager?.smoothScrollToPosition(binding?.recyclerViewMovie, null, 0)
            if (!dateFrom.isNullOrEmpty() || !dateTo.isNullOrEmpty()) {
                binding!!.buttonFromDate.text = getString(R.string.button_from_set_date, dateFrom)
                binding!!.buttonToDate.text = getString(R.string.button_to_set_date, dateTo)
            }

        }
    }

    private fun onLoading(event: ConsumableValue<Boolean>) {
        event.consume {
            if (it) {
                binding!!.progressbar.visibility = View.VISIBLE
            } else {
                binding!!.progressbar.visibility = View.GONE
            }
        }
    }

    private fun onError(event: ConsumableValue<Throwable>) {
        event.consume {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onFailure(event: ConsumableValue<String>) {

        event.consume {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }

    private fun navigateToMovieDetail(movie: Model.Movie) {
        val movieDetail = MovieDetail(
            movie.id,
            movie.title,
            movie.overView,
            movie.posterURL,
            movie.releaseDate,
            movie.voteAverage
        )
        val action =
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movieDetail)
        findNavController().navigate(action)
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_from_date -> {
                openCalendar(datePickerFrom)
            }
            R.id.button_to_date -> {
                openCalendar(datePickerTo)
            }

            R.id.button_set_filter -> {
                if (!dateFrom.isNullOrEmpty() || !dateTo.isNullOrEmpty()) {
                    val dateFilter = DateFilter(dateFrom, dateTo)
                    viewModel.refresh(dateFilter)
                }
            }
        }
    }

    private fun openCalendar(id: Int) {
        when (id) {
            datePickerFrom -> {

                setCalender(fromDateSetListener)
            }
            datePickerTo -> {
                setCalender(toDateSetListener)
            }
        }
    }

    private fun setCalender(calenderListener: DatePickerDialog.OnDateSetListener) {
        DatePickerDialog(
            requireContext(),
            calenderListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateDateInView(id: Int) {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        when (id) {
            datePickerFrom -> {

                dateFrom = sdf.format(calendar.time)
                binding!!.buttonFromDate.text = getString(R.string.button_from_set_date, dateFrom)
                viewModel.saveDate(ListMovieViewModel.FROM_DATE, dateFrom!!)

            }
            datePickerTo -> {

                dateTo = sdf.format(calendar.time)
                binding!!.buttonToDate.text = getString(R.string.button_to_set_date, dateTo)
                viewModel.saveDate(ListMovieViewModel.TO_DATE, dateTo!!)

            }
        }
    }
}


