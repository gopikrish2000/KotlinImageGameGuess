package sample.gopi.com.fastapplication.imagegameguess.networklayer

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface GameApi {
    @GET("/services/feeds/photos_public.gne")
    fun getRandomGameImages(@QueryMap queryMap: HashMap<String, String>): Observable<ResponseBody>
}