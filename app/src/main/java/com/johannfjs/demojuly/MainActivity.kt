package com.johannfjs.demojuly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.johannfjs.demojuly.databinding.ActivityMainBinding
import com.johannfjs.demojuly.db.AppDatabase
import com.johannfjs.demojuly.db.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signIn.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            CoroutineScope(CoroutinesProvider.bgContext).launch {
                val person: Person =
                    AppDatabase.getInstance(this@MainActivity).personDao()
                        .validateUser(username, password)
                if (person == null) {
                    withContext(CoroutinesProvider.uiContext) {
                        Toast.makeText(this@MainActivity, "Usuario no existe", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    withContext(CoroutinesProvider.uiContext) {
                        Toast.makeText(
                            this@MainActivity,
                            "Bienvenido: ${person.name} ${person.lastName} - ${person.email}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        binding.register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}