package dev.eldinosaur.appbasedatos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.widget.Toast
import dev.eldinosaur.appbasedatos.R
import dev.eldinosaur.appbasedatos.databinding.ActivityRegisterBinding
import dev.eldinosaur.appbasedatos.ui.database.AppDatabase
import dev.eldinosaur.appbasedatos.ui.model.Pet
import dev.eldinosaur.appbasedatos.ui.utils.Constants
import java.lang.reflect.Executable
import java.util.concurrent.Executors

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterBinding
    private var id = 0
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
        val bundle = intent.extras
        bundle?.let {
            val pet = bundle.getSerializable(Constants.KEY_PET) as Pet
            binding.includeRegister.txtTitulo.text = "Actualizar Mascota"
            binding.btnGrabar.text = "Actualizar"
            id = pet.id
            binding.edtNombre.setText(pet.nombre)
            binding.edtRaza.setText(pet.raza)
            binding.edtPreferencias.setText(pet.preferencias)
        }?: run {
            binding.includeRegister.txtTitulo.text = "Registrar Mascota"
            binding.btnGrabar.text = "Registrar"
            binding.edtNombre.setText("")
            binding.edtRaza.setText("")
            binding.edtPreferencias.setText("")
        }
        binding.edtNombre.requestFocus()

    }
    private fun events(){

        binding.btnGrabar.setOnClickListener{
            val nombre = binding.edtNombre.text.toString()
            val raza = binding.edtRaza.text.toString()
            val preferencias = binding.edtPreferencias.text.toString()

            if(id==0){
                agregar(Pet(0,nombre, raza, preferencias))
            }else{
                actualizar(Pet(id,nombre, raza, preferencias))
            }

        }
    }
    private fun agregar(pet:Pet){
        Executors.newSingleThreadExecutor().execute{
            appDatabase.petDao().insert(pet)
            runOnUiThread{
                Toast.makeText(this, "Mascota Registrada", Toast.LENGTH_LONG).show()
                onBackPressed()
            }
        }
    }
    private fun actualizar(pet:Pet){
        Executors.newSingleThreadExecutor().execute{
            appDatabase.petDao().update(pet)
            runOnUiThread{
                Toast.makeText(this, "Mascota Actualizada", Toast.LENGTH_LONG).show()
                onBackPressed()
            }
        }
    }
}