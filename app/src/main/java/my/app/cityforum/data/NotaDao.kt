package my.app.cityforum.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import my.app.cityforum.data.Nota


@Dao
interface NotaDao {

    @Query("SELECT * from nota_table ORDER BY title ASC")
    fun getAlphabetizedNotes(): LiveData<List<Nota>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nota: Nota)

    @Query("DELETE FROM nota_table")
    suspend fun deleteAll()

    @Query("DELETE FROM nota_table WHERE id == :id")
    fun deleteById(id: Int)

    @Query("UPDATE nota_table SET title=:title, content=:content WHERE id == :id")
    suspend fun updateById(title: String, content: String, id: Int)
}