package kz.sd.bobo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kz.sd.bobo.databinding.ItemPizzaBinding
import kz.sd.bobo.model.Pizza

class PizzaAdapter: ListAdapter<Pizza, BasePizzaViewHolder<*>>(PizzasDiffUtil()) {
    var itemClick: ((Pizza) -> Unit)? = null
    class PizzasDiffUtil:DiffUtil.ItemCallback<Pizza>(){
        override fun areContentsTheSame(oldItem: Pizza, newItem: Pizza): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areItemsTheSame(oldItem: Pizza, newItem: Pizza): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasePizzaViewHolder<*> {
        return PizzaViewHolder(
            ItemPizzaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BasePizzaViewHolder<*>, position: Int) {
        holder.bindView(getItem(position))
    }
    inner class PizzaViewHolder(binding:ItemPizzaBinding):
        BasePizzaViewHolder<ItemPizzaBinding>(binding){
        override fun bindView(item: Pizza) {
            with(binding){
                title.text = item.title
                desc.text = item.desc
                price.text = item.price.toString()+" KZT"
                img.setImageResource(item.img)
            }
            itemView.setOnClickListener {
                itemClick?.invoke(item)
            }
        }
        }
}