package com.rad.rweather.model.remote.response

import com.google.gson.annotations.SerializedName

data class ListForecastResponse(

    @SerializedName("dt")
    val date: Long?,

    @SerializedName("main")
    val main: MainWeatherResponse?,

    @SerializedName("weather")
    val weather: Array<WeatherItemResponse>?,

    @SerializedName("clouds")
    val clouds: CloudsResponse?,

    @SerializedName("wind")
    val wind: WindResponse?,

    @SerializedName("visibility")
    val visibility: Int?,

    @SerializedName("pop")
    val pop: Double?,

    @SerializedName("rain")
    val rain: RainResponse?,

    @SerializedName("sys")
    val sys: SysResponse?,

    @SerializedName("dt_txt")
    val dateText: String?,
)