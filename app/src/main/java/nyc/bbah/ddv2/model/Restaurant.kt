package nyc.bbah.ddv2.model

//model for consuming DD JSON. Necessary properties used only
data class Restaurant(val delivery_fee: Int?,
                      val id: Int?,
                      val status: String?,
                      val description: String?,
                      val cover_img_url: String?,
                      val name: String?)