package my.app.cityforum.db

import androidx.lifecycle.LiveData
import my.app.cityforum.dao.NoteDao
import my.app.cityforum.entities.Note

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAlphabetizedNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    fun getNoteById(id: Int): LiveData<List<Note>> {
        return noteDao.getNoteById(id)
    }
}