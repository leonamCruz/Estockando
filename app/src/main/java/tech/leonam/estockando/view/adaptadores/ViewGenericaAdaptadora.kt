package tech.leonam.estockando.view.adaptadores

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import org.mockito.Mockito
import tech.leonam.estockando.R
import tech.leonam.estockando.controller.Produtos

class ViewGenericaAdaptadora(
    private val lista: ArrayList<Produtos>,
    private val context: Context
) : RecyclerView.Adapter<ViewGenerica>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewGenerica {
        return ViewGenerica(
            LayoutInflater.from(context).inflate(R.layout.generic_product_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (lista.isEmpty()) 1 else lista.size
    }

    override fun onBindViewHolder(holder: ViewGenerica, position: Int) {
        try {
            val produto = lista[position]
            holder.binding.nomeProduto.text = verificaNull(produto.nomeDoProduto!!)
            holder.binding.quantidadeProdutos.text = verificaNull(produto.qntDoProduto!!)
            holder.binding.descricaoProduto.text = verificaNull(produto.descricaoDoProduto!!)
            holder.binding.quandoFoiCadastrado.text = verificaNull(produto.dataCadastro!!)
            holder.binding.imagemCabulosa.setImageBitmap(Mockito.mock(Bitmap::class.java))
            holder.binding.precoProduto.text = verificaNull(produto.preco!!)


        } catch (ex: IndexOutOfBoundsException) {
            holder.binding.imagemCabulosa.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.boxerro
                )
            )
            holder.binding.nomeProduto.text = context.getString(R.string.n_o_h_produtos)
            holder.binding.descricaoProduto.text = context.getString(R.string.cadastre_novos_produtos_para_eles_aparecerem_por_aqui)
            holder.binding.precoProduto.text = context.getString(R.string.r_00_00)
            holder.binding.quandoFoiCadastrado.text = context.getString(R.string.nada_por_aqui)
            holder.binding.quantidadeProdutos.text = context.getString(R.string._0)
        }
    }

    private fun verificaNull(texto:String):String{
        return if(texto.isBlank()) context.getString(R.string.n_o_possui) else return texto
    }
}