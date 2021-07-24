package com.johannfjs.demojuly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.johannfjs.demojuly.databinding.ActivityRegisterBinding
import com.johannfjs.demojuly.db.AppDatabase
import com.johannfjs.demojuly.db.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.register.setOnClickListener {
            //Obtenemos la información de las cajas de texto
            val username = binding.username.text.toString()
            val name = binding.name.text.toString()
            val lastName = binding.lastName.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            //Cargando la informacion en el objeto de tipo Persona
            val person = Person()
            person.username = username
            person.name = name
            person.lastName = lastName
            person.email = email
            person.password = password

            CoroutineScope(CoroutinesProvider.bgContext).launch {
                //Registrando en la base de datos
                AppDatabase.getInstance(this@RegisterActivity).personDao().insert(person)
            }
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}