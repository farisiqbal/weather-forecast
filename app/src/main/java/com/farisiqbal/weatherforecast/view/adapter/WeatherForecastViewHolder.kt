package com.farisiqbal.weatherforecast.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.farisiqbal.weatherforecast.data.api.response.DailyWeatherData
import com.farisiqbal.weatherforecast.utils.getTemperatureRangeText
import com.farisiqbal.weatherforecast.utils.setWeatherIconUrl
import kotlinx.android.synthetic.main.weather_card_item.view.*

/**
 * Created by farisiqbal on 30/05/2020
 */
class WeatherForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: DailyWeatherData) {
        itemView.apply {
            val weather = item.weather.firstOrNull()
            tvWeatherDate.text = item.dtTxt // todo use formatting
            ivWeatherIcon.setWeatherIconUrl(context, weather?.icon)
            tvWeatherDescription.text = weather?.description
            tvWeatherTemperature.text = item.main.getTemperatureRangeText()
        }
    }
}