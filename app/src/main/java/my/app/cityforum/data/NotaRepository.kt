package my.app.cityforum.data

import androidx.lifecycle.LiveData
import my.app.cityforum.data.NotaDao
import my.app.cityforum.data.Nota

class NotaRepository(private val notaDao: NotaDao) {

    val allNotes: LiveData<List<Nota>> = notaDao.getAlphabetizedNotes()

    suspend fun insert(nota: Nota) {
        notaDao.insert(nota)
    }

    fun deleteById(id: Int) {
        notaDao.deleteById(id)
    }
    suspend fun updateById(title: String, content: String, id: Int){
        notaDao.updateById(title, content, id)
    }
}