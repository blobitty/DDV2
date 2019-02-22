package nyc.bbah.ddv2.viewholder

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.discoverlist_itemview.view.*
import nyc.bbah.ddv2.RestaurantsNavigator
import nyc.bbah.ddv2.model.Restaurant
import java.lang.StringBuilder

class RestaurantListViewHolder(itemView: View?, private val navigator: RestaurantsNavigator): ViewHolder(itemView!!) {


    fun onBind(restaurant: Restaurant ){

        shortDescription(restaurant.description!!, itemView)
        shortenName(restaurant.name!!, itemView)
        checkStatus(restaurant.status!!, itemView)


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

    //Logic to fix Strings for view
    fun shortenName(str: String, itemView: View?): String?{
        //shorten restaurant name logic
        if (str.contains("(")){
            val index = str.indexOf('(')
            val shortName: String? = str.substring(0, index)
            itemView?.restaurantname?.text = shortName
            return shortName
        } else{
            itemView?.restaurantname?.text = str
            return str
        }
    }

    fun shortDescription(str: String, itemView: View?): String?{
        val maxLength = 20

        //shorten cuisine string logic
        if(str.length > maxLength){
            val shortDesc = StringBuilder()
            shortDesc.append(str.substring(0, maxLength))
            shortDesc.append("...")
            itemView?.cuisine_tv?.text = shortDesc.toString()
            return shortDesc.toString()
        } else{
            itemView?.cuisine_tv?.text = str
            return str
        }
    }

    fun checkStatus(str: String, itemView: View?){
        //check if status has time
        if(str.contains("Pre")){
            itemView?.asaptime_tv?.text = ""
        } else{
            itemView?.asaptime_tv?.text = str
        }
    }
}