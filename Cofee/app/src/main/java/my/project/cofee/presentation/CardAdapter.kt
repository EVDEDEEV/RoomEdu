package my.project.cofee.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import my.project.cofee.R
import my.project.cofee.data.models.CardModel
import my.project.cofee.data.models.CoffeeModel
import my.project.cofee.databinding.CardItemBinding
import my.project.cofee.databinding.CoffeeItemBinding

class CardAdapter  ():
    RecyclerView.Adapter<CardAdapter.CardHolder>() {

    private val productsFromCard = ArrayList<CardModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.card_item, parent, false)
        return CardHolder(binding)
    }

    override fun getItemCount(): Int {
        return productsFromCard.size
    }



    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(productsFromCard[position])

    }

    fun setList(cardList: List<CardModel>) {
        productsFromCard.clear()
        productsFromCard.addAll(cardList)

    }



    class CardHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(
            cardModel: CardModel
        ) {

            val getImage = cardModel.image
            Picasso.get().load(getImage).into(binding.imageProductCard)
            binding.nameProductCard.text = cardModel.name
            binding.countProductBasket.text = cardModel.count
            binding.priceProductCard.text = cardModel.price

        }

    }

}