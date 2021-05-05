package my.app.cityforum.data

import android.app.Application
import androidx.lifecycle.*
import my.app.cityforum.data.NotaDB
import my.app.cityforum.data.NotaRepository
import my.app.cityforum.data.Nota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotaRepository

    val allNotes: LiveData<List<Nota>>
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    init {
        val notesDao = NotaDB.getDatabase(application, viewModelScope).noteDao()
        repository = NotaRepository(notesDao)
        allNotes = repository.allNotes
    }

    fun insert(nota: Nota) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(nota)
    }

    fun deleteById(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteById(id)
    }

    fun updateById(title: String, content: String, id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateById(title, content, id)
    }

}