package dev.eldinosaur.appbasedatos.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.eldinosaur.appbasedatos.R
import dev.eldinosaur.appbasedatos.databinding.ItemPetsBinding
import dev.eldinosaur.appbasedatos.ui.model.Pet


//1. definir donde se maneja los datos
//2. crear el viewholder
//3. implementar los metodos del adaptador

class PetAdapter(var pets:List<Pet> = emptyList()):RecyclerView.Adapter<PetAdapter.PetAdapterViewHolder>() {

    //Crear viewholder
    inner class PetAdapterViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private var bindingPet:ItemPetsBinding = ItemPetsBinding.bind(itemView)
        fun bind(pet:Pet){
            bindingPet.txtNombre.text = pet.nombre
            bindingPet.txtRaza.text = pet.raza
            bindingPet.txtPreferencias.text = pet.preferencias
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pets,parent,false)
        return  PetAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetAdapterViewHolder, position: Int) {
        val pet = pets[position]
        holder.bind(pet)
    }

    override fun getItemCount(): Int {
        return pets.size
    }
    fun updatePets(pets:List<Pet>){
        this.pets = pets
        notifyDataSetChanged()
    }
}