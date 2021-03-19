package my.app.cityforum

import my.app.cityforum.entities.Note

interface CellClickListener {
    fun onCellClickListener(data: Note)
}