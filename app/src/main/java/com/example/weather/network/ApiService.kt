import com.example.weather.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("onecall")
    suspend fun getCurrentWeather(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("lang") lang: String,
        @Query("appid") appid: String?,
        @Query("units") unit: String,
    ): Response<WeatherModel>

}
