package com.lab.news.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lab.news.R
import com.lab.news.model.Article
import com.lab.news.util.imageUtils
import com.lab.news.util.messageUtils
import kotlinx.android.synthetic.main.inflate_news.view.*

class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.text_titulo
    private val descriptor = itemView.text_descricao
    private val fonte = itemView.text_fonte
    private val data = itemView.text_data
    private val image = itemView.imagem_noticia

    fun build(article: Article) {
        imageUtils(article.urlToImage, image)
        title.text = article.title
        descriptor.text = article.descriptor

        itemView.setOnClickListener {
            messageUtils(itemView, R.string.future_message)
        }
    }
}