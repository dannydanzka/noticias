package com.dramirez.noticias

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

data class Noticia(var titulo: String, var contenido: String, var autor: String)

class MainActivity : Activity() {

    private val noticias = mutableListOf<Noticia>() // Arreglo de objetos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura el contenido de la actividad manualmente
        setContentView(R.layout.activity_main)

        // Referencias a los componentes
        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etContenido = findViewById<EditText>(R.id.etContenido)
        val etAutor = findViewById<EditText>(R.id.etAutor)

        val btnCapturar = findViewById<Button>(R.id.btnCapturar)
        val btnBuscar = findViewById<Button>(R.id.btnBuscar)
        val btnActualizar = findViewById<Button>(R.id.btnActualizar)
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)

        // Funcionalidad de Capturar
        btnCapturar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val contenido = etContenido.text.toString()
            val autor = etAutor.text.toString()

            if (titulo.isEmpty() || contenido.isEmpty() || autor.isEmpty()) {
                Toast.makeText(this, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                noticias.add(Noticia(titulo, contenido, autor))
                Toast.makeText(this, "Noticia capturada", Toast.LENGTH_SHORT).show()
                limpiarCampos(etTitulo, etContenido, etAutor)
            }
        }

        // Funcionalidad de Buscar
        btnBuscar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val noticia = noticias.find { it.titulo == titulo }

            if (noticia != null) {
                etContenido.setText(noticia.contenido)
                etAutor.setText(noticia.autor)
                Toast.makeText(this, "Noticia encontrada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Noticia no encontrada", Toast.LENGTH_SHORT).show()
            }
        }

        // Funcionalidad de Actualizar
        btnActualizar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val noticia = noticias.find { it.titulo == titulo }

            if (noticia != null) {
                val nuevoContenido = etContenido.text.toString()
                val nuevoAutor = etAutor.text.toString()

                if (nuevoContenido.isEmpty() || nuevoAutor.isEmpty()) {
                    Toast.makeText(this, "Por favor, llene todos los campos para actualizar", Toast.LENGTH_SHORT).show()
                } else {
                    noticia.contenido = nuevoContenido
                    noticia.autor = nuevoAutor
                    Toast.makeText(this, "Noticia actualizada", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Noticia no encontrada para actualizar", Toast.LENGTH_SHORT).show()
            }
        }

        // Funcionalidad de Eliminar
        btnEliminar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val eliminado = noticias.removeIf { it.titulo == titulo }

            if (eliminado) {
                Toast.makeText(this, "Noticia eliminada", Toast.LENGTH_SHORT).show()
                limpiarCampos(etTitulo, etContenido, etAutor)
            } else {
                Toast.makeText(this, "Noticia no encontrada para eliminar", Toast.LENGTH_SHORT).show()
            }
        }

        // Funcionalidad de Limpiar
        btnLimpiar.setOnClickListener {
            limpiarCampos(etTitulo, etContenido, etAutor)
            Toast.makeText(this, "Campos limpiados", Toast.LENGTH_SHORT).show()
        }
    }

    private fun limpiarCampos(etTitulo: EditText, etContenido: EditText, etAutor: EditText) {
        etTitulo.text.clear()
        etContenido.text.clear()
        etAutor.text.clear()
    }
}
