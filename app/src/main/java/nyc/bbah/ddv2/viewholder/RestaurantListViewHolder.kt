package nyc.bbah.ddv2.viewholder

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.discoverlist_itemview.view.*
import nyc.bbah.ddv2.RestaurantsNavigator
import nyc.bbah.ddv2.model.Restaurant

class RestaurantListViewHolder(itemView: View?, private val navigator: RestaurantsNavigator): ViewHolder(itemView!!) {


    fun onBind(restaurant: Restaurant){

        itemView.restaurantname?.text = restaurant.name
        itemView.cuisine_tv?.text = restaurant.description
        itemView.asaptime_tv?.text = restaurant.status

        val imgUrl: String? = restaurant.cover_img_url

        Picasso
            .get()
            .load(imgUrl)
            .into(itemView.restaurantlist_imageView)

        //TODO: Extra Credit
        itemView.setOnClickListener {
            navigator.openDetail(restaurant)
        }
    }


}