package com.rad.rweather.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rad.rweather.core.domain.model.forecast.ListForecast
import com.rad.rweather.core.utils.DateFormatter.getDay
import com.rad.rweather.core.utils.ForecastSort
import com.rad.rweather.core.utils.getLottieSrc
import com.rad.rweather.databinding.DailyForecastRowBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DailyAdapter: RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {

    private var listForecast = ArrayList<ListForecast>()

    fun setData(data: List<ListForecast>?) {
        if (data==null) return

        listForecast.clear()
        listForecast.addAll(ForecastSort.sortDaily(data))
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view = DailyForecastRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val data = listForecast[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listForecast.size

    inner class DailyViewHolder(private val binding: DailyForecastRowBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(forecast: ListForecast) {
            binding.apply {
                val date = forecast.dateText?.let { getDay(it) }
                tvDay.text = date

                tvMinTemp.text = forecast.main?.tempMin!!.toInt().toString() + "°"
                tvMaxTemp.text = forecast.main.tempMax!!.toInt().toString() + "°"
                val img = forecast.weather?.get(0)?.icon
                CoroutineScope(Dispatchers.Default).launch {

                    val path = getLottieSrc(img!!)
                    withContext(Dispatchers.Main) {
                        lavWeather.setAnimation(path)
                    }
                }

            }
        }

    }
}