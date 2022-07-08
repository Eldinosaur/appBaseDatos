package dev.eldinosaur.appbasedatos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.eldinosaur.appbasedatos.databinding.ActivityPetsBinding
import dev.eldinosaur.appbasedatos.ui.database.AppDatabase
import dev.eldinosaur.appbasedatos.ui.utils.Constants
import java.util.concurrent.Executors

class PetsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPetsBinding
    //private lateinit var adapter:PetAdapter
    private val adapter:PetAdapter by lazy{
        PetAdapter()
    }
    private val appDatabase:AppDatabase by lazy {
        AppDatabase.getInstancia(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setupAdapter()
        eventos()
        loadData()
    }
    private fun eventos(){
        binding.fabRegister.setOnClickListener{
           startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    private fun init(){
        binding.includePets.txtTitulo.text = "Mis Mascotas"
    }
    private fun setupAdapter(){
        binding.rvPets.adapter = adapter
        adapter.setOnClickListenerPetEdit = {
            var bundle = Bundle().apply{
                putSerializable(Constants.KEY_PET, it)
            }
            val intent = Intent(this,RegisterActivity::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)
        }
        adapter.setOnClickListenerPetDelete = {
            Executors.newSingleThreadExecutor().execute{
                appDatabase.petDao().delete(it)
                runOnUiThread{
                    Toast.makeText(this, "Mascota Eliminada", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun loadData(){
        appDatabase.petDao().getPets().observe(this,{
            pets -> adapter.updatePets(pets)
        })
    }
}