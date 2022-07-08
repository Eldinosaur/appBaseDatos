package dev.eldinosaur.appbasedatos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.eldinosaur.appbasedatos.R
import dev.eldinosaur.appbasedatos.databinding.ActivityRegisterBinding
import dev.eldinosaur.appbasedatos.ui.database.AppDatabase
import dev.eldinosaur.appbasedatos.ui.model.Pet
import java.lang.reflect.Executable
import java.util.concurrent.Executors

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterBinding
    private val appDatabase:AppDatabase by lazy{
        AppDatabase.getInstancia(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        events()
    }
    private fun init(){
        binding.includeRegister.txtTitulo.text = "Registrar Mascota"
        binding.btnGrabar.text = "Registrar"
        binding.edtNombre.requestFocus()
    }
    private fun events(){

        binding.btnGrabar.setOnClickListener{
            val nombre = binding.edtNombre.text.toString()
            val raza = binding.edtRaza.text.toString()
            val preferencias = binding.edtPreferencias.text.toString()

            Executors.newSingleThreadExecutor().execute{
                appDatabase.petDao().insert(Pet(0, nombre,raza, preferencias))
                runOnUiThread{
                    Toast.makeText(this, "Mascota Registrada", Toast.LENGTH_LONG).show()
                    onBackPressed()
                }
            }
        }
    }
    private fun agregar(pet:Pet){

    }
}