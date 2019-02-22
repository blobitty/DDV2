package nyc.bbah.ddv2.adapter

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import nyc.bbah.ddv2.RestaurantsNavigator
import nyc.bbah.ddv2.model.Restaurant
import nyc.bbah.ddv2.viewholder.RestaurantListViewHolder
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class RestaurantListAdapterTest {

    val navigator = mock<RestaurantsNavigator>()
    val items = listOf(Restaurant(
        delivery_fee = 40,
        id = 100,
        status = "32",
        description = "Chicken Restaurant",
        cover_img_url = "http:://image",
        name = "Popeyes"
    ))
    val adapter = RestaurantListAdapter(items, navigator)

    //TODO: Cant test. Picasso attached to Android OS
//    @Test
//    fun `should bind view holder with restaurant`() {
//        val viewHolder = mock<RestaurantListViewHolder>()
//
//        adapter.onBindViewHolder(viewHolder, 0)
//
//        verify(viewHolder).onBind(items.first())
//    }

    @Test
    fun `should have as many items as given`() {
        assert(adapter.itemCount == items.size)
    }
}