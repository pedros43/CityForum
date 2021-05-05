package my.app.cityforum

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import my.app.cityforum.data.NotaAdapter
import my.app.cityforum.data.Nota
import my.app.cityforum.data.NotaViewModel

var PARAM_ID = "id"
var PARAM1_TITLE = "title"
var PARAM2_CONTENT = "content"

class MainActivity2 : AppCompatActivity(), addClick {
    private lateinit var notaViewModel: NotaViewModel
    private val newWordActivityRequestCode = 1
    private val UpdateNoteActivityRequestCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



        //recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NotaAdapter(this, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //view model
        notaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)
        notaViewModel.allNotes.observe(this, { notes ->
            notes?.let { adapter.setNotes(it) }
        })

        //Fab
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity2, AddNota::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //apagar
        if (resultCode == Activity.RESULT_OK && data != null && data.action == "REMOVE") {
            var id = Integer.parseInt(data?.getStringExtra(PARAM_ID))
            notaViewModel.deleteById(id)
            Toast.makeText(this, "Nota eliminada.", Toast.LENGTH_SHORT).show()
            return
        }

        //inserir
        if(requestCode ==  newWordActivityRequestCode) {
            if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
                var titulo = data?.getStringExtra(AddNota.EXTRA_REPLY_TITLE).toString()
                var content = data?.getStringExtra(AddNota.EXTRA_REPLY_CONTENT).toString()
                var note = Nota(title = titulo, content = content)
                notaViewModel.insert(note)
                Toast.makeText(this, "Nota guardada.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                        applicationContext,
                        "Campo(s) Vazio(s): não inserido.",
                        Toast.LENGTH_LONG).show()
            }
            //editar
        } else if (requestCode == UpdateNoteActivityRequestCode) {
            if (requestCode == UpdateNoteActivityRequestCode && resultCode == Activity.RESULT_OK) {
                var titulo = data?.getStringExtra(PARAM1_TITLE).toString()
                var content = data?.getStringExtra(PARAM2_CONTENT).toString()
                var id = Integer.parseInt(data?.getStringExtra(PARAM_ID))
                notaViewModel.updateById(titulo, content, id)
                Toast.makeText(applicationContext, "Nota alterada.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Campo(s) Vazio(s): não alterado.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCellClickListener(data: Nota) {
        val id = data.id.toString()
        val titulo = data.title
        val conteudo = data.content
        val intent = Intent(this, EditNota::class.java).apply {
            putExtra(PARAM_ID, id)
            putExtra(PARAM1_TITLE, titulo)
            putExtra(PARAM2_CONTENT, conteudo)
        }
        startActivityForResult(intent, UpdateNoteActivityRequestCode)
    }
}