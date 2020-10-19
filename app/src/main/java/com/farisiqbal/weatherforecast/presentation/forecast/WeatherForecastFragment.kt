package com.farisiqbal.weatherforecast.presentation.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.farisiqbal.weatherforecast.R
import com.farisiqbal.weatherforecast.data.api.ResultLoad
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecast
import com.farisiqbal.weatherforecast.data.repository.WeatherForecastRepository
import com.farisiqbal.weatherforecast.domain.usecase.GetWeatherForecastDataUseCase
import com.farisiqbal.weatherforecast.presentation.forecast.adapter.WeatherForecastListAdapter
import com.farisiqbal.weatherforecast.utils.setVisible
import com.farisiqbal.weatherforecast.utils.toDegree
import kotlinx.android.synthetic.main.weather_forecast_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class WeatherForecastFragment : Fragment() {

    private val viewModel: WeatherForecastViewModel by viewModel()
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
        viewModel.weatherDataResult.observe(viewLifecycleOwner, Observer {
            layoutLoading.setVisible(false)
            layoutError.setVisible(false)

            when (it) {
                is ResultLoad.Success -> {
                    tvCurrentLocation.text = it.data.city.name
                    renderForecasts(it.data.list)
                }
                is ResultLoad.Error -> {
                    layoutError.setVisible(true)
                }
                is ResultLoad.Loading -> {
                    layoutLoading.setVisible(true)
                }
            }
        })

        // viewModel.weatherForecastResponse.observe(viewLifecycleOwner, Observer { data ->
        //     tvCurrentLocation.text = data.city.name
        //     val forecasts = data.list
        //     adapter.updateData(forecasts)
        //     if (forecasts.isNotEmpty()) {
        //         tvCurrentTemperature.text = forecasts.first().main.temp.toDegree()
        //     }
        // })
        //
        // viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
        //     layoutLoading.setVisible(isLoading)
        // })
        //
        // viewModel.isError.observe(viewLifecycleOwner, Observer { isError ->
        //     layoutError.setVisible(isError)
        // })

        viewModel.setNewQuery(DEFAULT_CITY_QUERY)
    }

    fun bindUI() {
        setupRecyclerView()
        setupSearchView()
        setupErrorLayout()
    }

    private fun setupRecyclerView() {
        adapter = WeatherForecastListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setNewQuery(query ?: "")
                viewModel.fetchForecastData()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupErrorLayout() {
        ivErrorIcon.setOnClickListener {
            viewModel.fetchForecastData()
        }
    }

    private fun renderForecasts(data: List<WeatherForecast>) {
        adapter.updateData(data)
        if (data.isNotEmpty()) {
            tvCurrentTemperature.text = data.first().main.temp.toDegree()
        }
    }

    companion object {
        const val DEFAULT_CITY_QUERY: String = "Jakarta"
    }
}
