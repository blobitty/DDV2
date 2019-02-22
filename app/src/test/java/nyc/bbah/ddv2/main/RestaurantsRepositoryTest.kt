package nyc.bbah.ddv2.main

import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import nyc.bbah.ddv2.model.Restaurant
import nyc.bbah.ddv2.network.RestaurantsService
import org.junit.Test

class RestaurantsRepositoryTest {

    val restaurantsService = mock<RestaurantsService>()
    val restaurantsRepository = RestaurantsRepository(restaurantsService)

    @Test
    fun shouldreturnSingle() {
        val mockSingle = mock<Single<List<Restaurant>>>()
        val lat = 37.422740
        val lng = -122.139956
        val offset = 0
        val limit = 20

        val returnSingle = restaurantsRepository.restaurantListCall(lat, lng, offset, limit)
        //TODO: Fix
        //assert(returnSingle == mockSingle)
    }
}