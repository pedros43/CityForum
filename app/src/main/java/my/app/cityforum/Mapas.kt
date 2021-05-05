package my.app.cityforum

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import my.app.cityforum.api.EndPoints
import my.app.cityforum.api.Problemas
import my.app.cityforum.api.ServiceBuilder
import retrofit2.Call


class Mapas : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var shared_preferences: SharedPreferences
    private lateinit var problemas: List<Problemas>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapas)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        shared_preferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getReports()
        var coordenadas: LatLng
        val user_id = shared_preferences.getInt("id", 0)

        call.enqueue(object : retrofit2.Callback<List<Problemas>> {
            override fun onResponse(call: retrofit2.Call<List<Problemas>>, response: retrofit2.Response<List<Problemas>>){
                if (response.isSuccessful){
                    problemas = response.body()!!
                    for (problema in problemas){
                        coordenadas = LatLng(problema.latitude.toDouble(), problema.longitude.toDouble())
                        if(problema.user_id == user_id){
                            mMap.addMarker(MarkerOptions().position(coordenadas).title(problema.id.toString()).snippet(problema.titulo + "-" + problema.descr))
                                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        }else{
                            mMap.addMarker(MarkerOptions().position(coordenadas).title(problema.id.toString()).snippet(problema.titulo + "-" + problema.descr))
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<Problemas>>, t: Throwable){
                Toast.makeText(this@Mapas,"${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

      val gmr = LatLng(41.4418, -8.29563)
        mMap.addMarker(MarkerOptions().position(gmr).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gmr))
    }
}