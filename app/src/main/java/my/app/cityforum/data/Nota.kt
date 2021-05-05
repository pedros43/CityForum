package my.app.cityforum.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nota_table")
data class Nota (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
)