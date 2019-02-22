package nyc.bbah.ddv2.adapter

import com.nhaarman.mockitokotlin2.mock
import nyc.bbah.ddv2.RestaurantsNavigator
import nyc.bbah.ddv2.model.Restaurant
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

    @Test
    fun `should have as many items as given`() {
        assert(adapter.itemCount == items.size)
    }
}