package tech.leonam.estockando.view.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import tech.leonam.estockando.R
import tech.leonam.estockando.controller.Produtos
import tech.leonam.estockando.controller.util.UtilImage

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
            with(holder.binding) {
                nomeProduto.text = verificaNull(produto.nomeDoProduto!!)
                quantidadeProdutos.text = verificaNull(produto.qntDoProduto!!)
                descricaoProduto.text = verificaNull(produto.descricaoDoProduto!!)
                quandoFoiCadastrado.text = verificaNull(produto.dataCadastro!!)
                //precoProduto.text = verificaNull(produto.preco!!)
                //imagemCabulosa.setImageBitmap(UtilImage.deBase64ParaBitmap(produto.imagemDoProduto!!))
            }


        } catch (ex: IndexOutOfBoundsException) {

            with(holder.binding){
                imagemCabulosa.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.boxerro
                    )
                )
                imagemCabulosa.scaleType = ImageView.ScaleType.CENTER_INSIDE
                nomeProduto.text = context.getString(R.string.n_o_h_produtos)
                descricaoProduto.text =
                    context.getString(R.string.cadastre_novos_produtos_para_eles_aparecerem_por_aqui)
                precoProduto.text = context.getString(R.string.r_00_00)
                quandoFoiCadastrado.text = context.getString(R.string.nada_por_aqui)
                quantidadeProdutos.text = context.getString(R.string._0)
            }
        }
    }

    private fun verificaNull(texto: String): String {
        return if (texto.isNullOrBlank()) context.getString(R.string.n_o_possui) else return texto
    }
}