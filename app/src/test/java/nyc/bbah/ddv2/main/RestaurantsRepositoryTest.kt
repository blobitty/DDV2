package nyc.bbah.ddv2.main

import com.nhaarman.mockitokotlin2.mock
import nyc.bbah.ddv2.network.RestaurantsService

class RestaurantsRepositoryTest {

    val restaurantsService = mock<RestaurantsService>()
    val restaurantsRepository = RestaurantsRepository(restaurantsService)

    
}