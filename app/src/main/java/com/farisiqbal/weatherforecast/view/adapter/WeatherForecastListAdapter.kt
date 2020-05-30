package com.farisiqbal.weatherforecast.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farisiqbal.weatherforecast.R
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecast

/**
 * Created by farisiqbal on 30/05/2020
 */
class WeatherForecastListAdapter : RecyclerView.Adapter<WeatherForecastViewHolder>() {

    private val items: MutableList<WeatherForecast> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        return WeatherForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.weather_card_item, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateData(newItems: List<WeatherForecast>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}