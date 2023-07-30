package com.example.appdenoticiasusuario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appdenoticiasusuario.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        recuperarNoticia()
    }

    private fun recuperarNoticia(){

        db.collection("Noticias").document("noticia").get()
            .addOnCompleteListener { document ->
                if (document.isSuccessful){

                    val titulo = document.result.get("titulo").toString()
                    val noticia = document.result.get("noticia").toString()
                    val data = document.result.get("data").toString()
                    val autor = document.result.get("autor").toString()

                    binding.txtTituloNoticia.text = titulo
                    binding.txtNoticia.text = noticia
                    binding.txtData.text = data
                    binding.txtAutor.text = autor
                }
            }
    }

}