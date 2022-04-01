import com.example.todolistapp.model.ListResponse
import retrofit2.Call
import retrofit2.http.*

interface AppApi {
    @GET("lists")
    fun getList(): Call<ArrayList<ListResponse>>

    @FormUrlEncoded
    @POST("lists")
    fun insertList(
        @Field("name") name: String,
        @Field("priority") priority: Int
    ): Call<ArrayList<ListResponse>>
}

