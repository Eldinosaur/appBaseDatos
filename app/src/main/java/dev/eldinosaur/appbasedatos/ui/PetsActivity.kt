package dev.eldinosaur.appbasedatos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.eldinosaur.appbasedatos.databinding.ActivityPetsBinding

class PetsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPetsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventos()
    }
    private fun eventos(){
        binding.fabRegister.setOnClickListener{
           startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}