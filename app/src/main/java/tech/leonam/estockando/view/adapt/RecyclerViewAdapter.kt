package tech.leonam.estockando.view.adapt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import tech.leonam.estockando.R
import tech.leonam.estockando.viewModel.Product
import tech.leonam.estockando.viewModel.util.ImageUtility
import tech.leonam.estockando.viewModel.util.PriceUtility

class RecyclerViewAdapter(
    private val lista: ArrayList<Product>,
    private val context: Context
) : RecyclerView.Adapter<GenericRecyclerView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericRecyclerView {
        return GenericRecyclerView(
                LayoutInflater.from(context).inflate(R.layout.generic_product_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (lista.size == 0) 1 else lista.size
    }

    override fun onBindViewHolder(holder: GenericRecyclerView, position: Int) {
        try {
            val produto = lista[position]
            with(holder.binding) {
                nomeProduto.text = verificaNull(produto.nomeDoProduto)
                quantidadeProdutos.text = String.format("%s: %s", context.getString(R.string.quantidade), verificaNull(produto.qntDoProduto))
                descricaoProduto.text = verificaNull(produto.descricaoDoProduto + "\n Id: ${produto.id}")
                quandoFoiCadastrado.text = String.format("%s %s", context.getString(R.string.cadastrado_em), verificaNull(produto.dataCadastro).replace(" ", "\n"))
                precoProduto.text = verificaNull(PriceUtility.normalizaPreco(produto.preco))
                imagemCabulosa.scaleType = ImageView.ScaleType.CENTER_INSIDE
                try {
                    imagemCabulosa.setImageBitmap(ImageUtility.deBase64ParaBitmap(produto.imagemDoProduto))
                } catch (e: Exception) {
                    imagemCabulosa.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.boxerro))
                }
            }

        } catch (ex: Exception) {

            with(holder.binding) {
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
        return if (texto.isBlank()) context.getString(R.string.n_o_possui) else return texto
    }
}