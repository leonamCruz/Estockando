package tech.leonam.estockando.view.adapt

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import tech.leonam.estockando.databinding.GenericProductLayoutBinding

class GenericRecyclerView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: GenericProductLayoutBinding = GenericProductLayoutBinding.bind(itemView)
}