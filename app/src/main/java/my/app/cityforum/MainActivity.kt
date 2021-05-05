package my.app.cityforum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button2 = findViewById<Button>(R.id.btn2)
        button2.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val button = findViewById<Button>(R.id.btn1)
        button.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }


    }
}