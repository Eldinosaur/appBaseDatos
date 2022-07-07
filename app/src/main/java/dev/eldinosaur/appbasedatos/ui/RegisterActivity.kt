package dev.eldinosaur.appbasedatos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.eldinosaur.appbasedatos.R
import dev.eldinosaur.appbasedatos.databinding.ActivityRegisterBinding
import dev.eldinosaur.appbasedatos.ui.database.AppDatabase

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
        
    }
}