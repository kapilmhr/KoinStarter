package app.frantic.koinstarter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.frantic.koinstarter.R
import app.frantic.koinstarter.model.Flower
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_flower_item.view.*

class FlowerAdapter(var flowerList:List<Flower>) : RecyclerView.Adapter<FlowerAdapter.MyHolder>() {
    var context: Context?= null
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolder {
        context = parent.context
        var view: View = LayoutInflater.from(context).inflate(R.layout.layout_flower_item, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return flowerList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val flower = flowerList.get(position)
        holder.name.text = flower.name
        holder.price.text = "Price: $${flower.price}"
        holder.category.text = "Category: ${flower.category}"
        Picasso.get().load("http://services.hanselandpetal.com/photos/${flower.photo}").into(holder.image)
    }

    class MyHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.name
        val price = itemView.price
        val category = itemView.category
        val image = itemView.imageView

    }
}