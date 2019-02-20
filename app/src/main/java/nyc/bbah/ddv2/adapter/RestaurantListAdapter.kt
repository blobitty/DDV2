package nyc.bbah.ddv2.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import nyc.bbah.ddv2.model.Restaurant
import nyc.bbah.ddv2.viewholder.RestaurantListViewHolder
import nyc.bbah.ddv2.R.layout

class RestaurantListAdapter(val items: List<Restaurant>) : RecyclerView.Adapter<RestaurantListViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RestaurantListViewHolder {
        return RestaurantListViewHolder(LayoutInflater
            .from(p0.context)
            .inflate(layout.discoverlist_itemview, p0, false))
    }

    override fun onBindViewHolder(p0: RestaurantListViewHolder, p1: Int) {
        val restaurant: Restaurant = items[p1]
        p0.onBind(restaurant)
    }

    override fun getItemCount(): Int {
        return items.size
    }


}