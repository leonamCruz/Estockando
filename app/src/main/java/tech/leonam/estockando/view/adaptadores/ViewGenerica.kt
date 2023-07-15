package tech.leonam.estockando.view.adaptadores

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import tech.leonam.estockando.databinding.GenericProductLayoutBinding

class ViewGenerica(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding: GenericProductLayoutBinding = GenericProductLayoutBinding.bind(itemView)
}