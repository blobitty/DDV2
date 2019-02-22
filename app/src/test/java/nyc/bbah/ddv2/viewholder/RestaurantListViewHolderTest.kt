package nyc.bbah.ddv2.viewholder

import android.view.View
import com.nhaarman.mockitokotlin2.mock
import nyc.bbah.ddv2.RestaurantsNavigator
import nyc.bbah.ddv2.model.Restaurant
import org.junit.Test
import org.junit.Assert.*

class RestaurantListViewHolderTest {

    val navigator = mock<RestaurantsNavigator>()
    val view = mock<View>()
    val viewHolder: RestaurantListViewHolder = RestaurantListViewHolder(view, navigator)


    @Test
    fun shouldShortenName(){
        val badString = "Popeyes (Brooklyn)"

        assertEquals(viewHolder.shortenName(badString, view), "Popeyes " )

    }

    @Test
    fun shouldShortenDescription(){
        val badString = "stringwaytoolongtofitinsideofaview"

        assertEquals(viewHolder.shortDescription(badString, view), "stringwaytoolongtofi...")
    }
}