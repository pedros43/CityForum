package my.app.cityforum

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import my.app.cityforum.api.EndPoints
import my.app.cityforum.api.OutputLogin
import my.app.cityforum.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private lateinit var editUsernameView: EditText
    private lateinit var editPasswordView: EditText
    private lateinit var checkboxRemeber: CheckBox
    private lateinit var sharedPreferences: SharedPreferences
    private var lembrar = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editUsernameView = findViewById(R.id.editUsername)
        editPasswordView = findViewById(R.id.editPassword)
        checkboxRemeber = findViewById(R.id.checkBox)

        sharedPreferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
        lembrar = sharedPreferences.getBoolean("lembrar", false)

        if(lembrar){
            val intent = Intent(this@Login, Menu::class.java)
            startActivity(intent);
            finish()
        }
    }

    fun login(view: View) {
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val username = editUsernameView.text.toString()
        val password = editPasswordView.text.toString()
        val checked_remember: Boolean = checkboxRemeber.isChecked
        val call = request.login(username = username, password = password)

        call.enqueue(object : Callback<OutputLogin> {
            override fun onResponse(call: Call<OutputLogin>, response: Response<OutputLogin>){
                if (response.isSuccessful){
                    val c: OutputLogin = response.body()!!
                    if(TextUtils.isEmpty(editUsernameView.text) || TextUtils.isEmpty(editPasswordView.text)) {
                        Toast.makeText(this@Login, "Erro ao fazer login", Toast.LENGTH_LONG).show()
                    }else{
                        if(c.status == "false"){
                            Toast.makeText(this@Login, c.MSG, Toast.LENGTH_LONG).show()
                        }else{
                            val sharedPreferencesEdit : SharedPreferences.Editor = sharedPreferences.edit()
                            sharedPreferencesEdit.putString("username", username)
                            sharedPreferencesEdit.putString("password", password)
                            sharedPreferencesEdit.putInt("id", c.id)
                            sharedPreferencesEdit.putBoolean("lembrar", checked_remember)
                            sharedPreferencesEdit.apply()

                            val intent = Intent(this@Login, Menu::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<OutputLogin>, t: Throwable){
                Toast.makeText(this@Login,"${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}