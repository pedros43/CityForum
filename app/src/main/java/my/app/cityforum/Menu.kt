package my.app.cityforum

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class Menu : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

    }


    fun mapasActivity(view: View){
        val intent = Intent(this@Menu, Mapas::class.java)
        startActivity(intent)
    }

    fun logout(view: View){
        val sharedPreferencesEdit : SharedPreferences.Editor = sharedPreferences.edit()
        sharedPreferencesEdit.clear()
        sharedPreferencesEdit.apply()

        val intent = Intent(this@Menu, Login::class.java)
        startActivity(intent)
        finish()
    }
}