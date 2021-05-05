package my.app.cityforum.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.app.cityforum.addClick
import my.app.cityforum.R


class NotaAdapter internal constructor(context: Context, private val addClick: addClick ) : RecyclerView.Adapter<NotaAdapter.NoteViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Nota>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview, parent, false)
        return NoteViewHolder(itemView)
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteItemViewTitle: TextView = itemView.findViewById(R.id.textViewTitle);
        val noteItemViewContent: TextView = itemView.findViewById(R.id.textViewContent);

    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = notes[position]

        holder.noteItemViewTitle.text = current.title
        holder.noteItemViewContent.text = current.content

        holder.itemView.setOnClickListener {
            addClick.onCellClickListener(current)
        }

    }
    internal fun setNotes(notas: List<Nota>) {
        this.notes = notas
        notifyDataSetChanged()
    }

    override fun getItemCount() = notes.size
}