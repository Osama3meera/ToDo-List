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

    @DELETE("/lists/{id}")
    fun deleteList(
        @Path("id") id:String
    ): Call<ListResponse>

    @PUT("/lists/{id}")
    fun updateList(
        @Path ("id") id: String,
        @Body listResponse: ListResponse
    ): Call<ListResponse>

}

