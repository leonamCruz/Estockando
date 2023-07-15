package tech.leonam.estockando.view.adaptadores

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ViewGenericaAdaptadora(val lista: List<Any>) : RecyclerView.Adapter<ViewGenerica>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewGenerica {
    }

    override fun getItemCount(): Int {
        return if (lista.isEmpty()) {
            1
        } else lista.size
    }

    override fun onBindViewHolder(holder: ViewGenerica, position: Int) {
    }
}