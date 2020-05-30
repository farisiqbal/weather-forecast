package com.farisiqbal.weatherforecast.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.farisiqbal.weatherforecast.R
import com.farisiqbal.weatherforecast.utils.setVisible
import com.farisiqbal.weatherforecast.utils.toDegree
import com.farisiqbal.weatherforecast.view.adapter.WeatherForecastListAdapter
import kotlinx.android.synthetic.main.weather_forecast_fragment.*

class WeatherForecastFragment : Fragment() {

    private lateinit var viewModel: WeatherForecastViewModel
    private lateinit var adapter: WeatherForecastListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_forecast_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel()
        bindUI()
    }

    fun bindViewModel() {
        val factory = WeatherForecastViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(WeatherForecastViewModel::class.java)

        viewModel.weatherForecastResponse.observe(viewLifecycleOwner, Observer { data ->
            tvCurrentLocation.text = data.city.name
            val forecasts = data.list
            adapter.updateData(forecasts)
            if (forecasts.isNotEmpty()) {
                tvCurrentTemperature.text = forecasts.first().main.temp.toDegree()
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            layoutLoading.setVisible(isLoading)
        })

        // todo improve error handling better than default requirements. should be able to set new query
        viewModel.isError.observe(viewLifecycleOwner, Observer { isError ->
            layoutError.setVisible(isError)
        })

        // initial request
        viewModel.setNewQuery("jakarta")
        viewModel.getForecastData()
    }

    fun bindUI() {
        // setup recyclerView
        adapter = WeatherForecastListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // setup searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setNewQuery(query ?: "")
                viewModel.getForecastData()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        // error
        ivErrorIcon.setOnClickListener {
            viewModel.getForecastData()
        }
    }
}
