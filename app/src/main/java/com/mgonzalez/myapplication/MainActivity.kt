package com.mgonzalez.myapplication

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var dataset = arrayListOf<Contact>()
        dataset.add(Contact("Marcos González", "648121212", "marcos@gmail.com"))
        dataset.add(Contact("Jaume Tudela", "68763434", "jaume@gmail.com"))
        dataset.add(Contact("Javier Berzosa", "14335332", "javier@gmail.com"))
        dataset.add(Contact("Monica Gil", "235343434", "monica@gmail.com"))
        dataset.add(Contact("Cynthia Garcia", "648125678", "cynthia@gmail.com"))

        val customAdapter = ContactAdapter(dataset)

        val recViewAgenda: RecyclerView = findViewById(R.id.recViewAgenda)
        recViewAgenda.layoutManager = LinearLayoutManager(this)
        recViewAgenda.adapter = customAdapter

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.recViewAgenda)
        ) { v, insets ->
            val innerPadding = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
                        or WindowInsetsCompat.Type.displayCutout()
            )
            v.setPadding(
                innerPadding.left,
                innerPadding.top,
                innerPadding.right,
                innerPadding.bottom)
            insets
        }

        val fab: View = findViewById(R.id.createContactAFB)
        fab.setOnClickListener { view ->
            mostrarDialogoCrearContacto(dataset, customAdapter)
        }

    }

    private fun mostrarDialogoCrearContacto(dataset: ArrayList<Contact>, customAdapter: ContactAdapter){
        val dialogView = layoutInflater.inflate(R.layout.create_contact_dialog, null)

        val etName = dialogView.findViewById<EditText>(R.id.inputName)
        val etTelNumber = dialogView.findViewById<EditText>(R.id.inputTelNumber)
        val etEmail = dialogView.findViewById<EditText>(R.id.inputEmail)

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("Guardar") { dialog, _ ->

                val name = etName?.text?.toString() ?: ""
                val telNumber = etTelNumber?.text?.toString() ?: ""
                val email = etEmail?.text?.toString() ?: ""

                dataset.add(Contact(name, telNumber, email))
                customAdapter.notifyItemInserted(dataset.size - 1)
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }
}