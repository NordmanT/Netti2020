package com.example.netti2020

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*
import kotlin.collections.ArrayList
import kotlin.time.hours


interface PortsService {
   @GET("winter-navigation/ports")
    fun getCurrentPortsData( /*  @Query("lat") lat: String  */ ): Call<Ports>
}
interface VrService {
    @GET("train-locations/latest/")
    fun getCurrentPortsData( /*  @Query("lat") lat: String  */): Call<ArrayList<TrainsItem>>
}

class MainActivity : AppCompatActivity() {

   // private lateinit var mRecyclerView: RecyclerView
   // private var mAdapter: CustomAdapter? = null


    var BaseUrl="https://rata.digitraffic.fi/api/v1/"
    //var BaseUrl="https://meri.digitraffic.fi/api/v1/"
    //var BaseUrl="https://tie.digitraffic.fi/api/v1/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // val lista = arrayOf("fggg","hhghghg")

        // Get a handle to the RecyclerView.
      //  mRecyclerView = findViewById(R.id.recyclerView);
        // Create an adapter and supply the data to be displayed.
      //  mAdapter = CustomAdapter(lista)
        // Connect the adapter with the RecyclerView.
       // mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
       // mRecyclerView.setLayoutManager( LinearLayoutManager(this));

        getCurrentData()
    }
    internal fun getCurrentData() {
        val textView = findViewById<TextView>(R.id.textView)
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())   // json -> object
            .build()

        // Request

        val service = retrofit.create(VrService::class.java)
       // val service = retrofit.create(PortsService::class.java)
        val call = service.getCurrentPortsData( /* parametrit */ )

        // Response
       // call.enqueue(object : Callback<Ports>{
       // call.enqueue(object : Callback<ArrayList<TrainsItem>>{
        call.enqueue(object : Callback<ArrayList<TrainsItem>>{


            override fun onResponse(call: Call<ArrayList<TrainsItem>>, response: Response<ArrayList<TrainsItem>>) {
          // override fun onResponse(call: Call<Ports>, response: Response<Ports>) {
          // override fun onResponse(call: Call<WeatherStation>, response: Response<WeatherStation>) {
                if (response.code() == 200) {    // = HTTP OK
                    // tässä portsResponse on tyyppiä Ports
                    val portsResponse = response.body()!!
                    // kullakin rajapinnalla on omat propertyt vastauksessa(response), tässä .features...
                    //textView.text = "Port 1 = " + portsResponse!!.features[0].properties.name+portsResponse.features[0].properties.seaArea
                    textView.text = portsResponse[0].trainNumber.toString()+"speed="+portsResponse[0].speed+ portsResponse[0].location
                   // textView.text = portsResponse.sensorValues.toString()+ portsResponse.id
                }
            }
            // virhetilanne

            //override fun onFailure(call: Call<Ports>, t: Throwable) {
           override fun onFailure(call: Call<ArrayList<TrainsItem>>, t: Throwable) {
                textView.text = t.message
            }

        })
    }
}
