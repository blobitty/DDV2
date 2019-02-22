package nyc.bbah.ddv2.viewholder

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.discoverlist_itemview.view.*
import nyc.bbah.ddv2.RestaurantsNavigator
import nyc.bbah.ddv2.model.Restaurant
import java.lang.StringBuilder

class RestaurantListViewHolder(itemView: View?, private val navigator: RestaurantsNavigator): ViewHolder(itemView!!) {


    fun onBind(restaurant: Restaurant){
        val maxLength = 20
        val shortDesc = StringBuilder()
        //val shortName = StringBuilder()


        //shorten cuisine string logic
        if(restaurant.description!!.length > maxLength){
            shortDesc.append(restaurant.description.substring(0, maxLength))
            shortDesc.append("...")
            itemView.cuisine_tv?.text = shortDesc.toString()
        } else{
            itemView.cuisine_tv?.text = restaurant.description
        }

        //shorten restaurant name logic
        if (restaurant.name!!.contains("(")){
            val index = restaurant.name.indexOf('(')
            val shortName: String? = restaurant.name.substring(0, index)
            itemView.restaurantname?.text = shortName
        } else{
            itemView.restaurantname?.text = restaurant.name
        }
        //check if status has time
        if(restaurant.status!!.contains("Pre")){
            itemView.asaptime_tv?.text = ""
        } else{
            itemView.asaptime_tv?.text = restaurant.status
        }

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