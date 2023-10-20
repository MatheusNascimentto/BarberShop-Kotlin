package com.example.barbershop.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.barbershop.R
import com.example.barbershop.adapter.ServicosAdapter
import com.example.barbershop.databinding.ActivityHomeBinding
import com.example.barbershop.model.Servicos

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var servicosAdapter: ServicosAdapter
    private val listaServicos: MutableList<Servicos> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome")

        binding.txtNomeUsuario.text = "Bem-vindo,$nome"
        val recyclerViewServicos = binding.recyclerViewServicos
        recyclerViewServicos.layoutManager = GridLayoutManager(this,2)
        servicosAdapter = ServicosAdapter(this,listaServicos)
        recyclerViewServicos.setHasFixedSize(true)
        recyclerViewServicos.adapter = servicosAdapter
        getServicos()

        binding.btAgendar.setOnClickListener {
            val intent = Intent(this,Agendamento::class.java)
            intent.putExtra("nome",nome)
            startActivity(intent)
        }

    }

    private fun getServicos(){

        val servicos1 = Servicos(R.drawable.img1, "Corte de cabelo")
        listaServicos.add(servicos1)

        val servicos2 = Servicos(R.drawable.img2, "Corte de barba")
        listaServicos.add(servicos2)

        val servicos3 = Servicos(R.drawable.img3, "Lavagem de cabelo")
        listaServicos.add(servicos3)

        val servicos4 = Servicos(R.drawable.img4, "Tratamento de cabelo")
        listaServicos.add(servicos4)

    }
}