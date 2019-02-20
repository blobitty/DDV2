package nyc.bbah.ddv2.model

data class BizAddress(val city: String?,
                      val state: String?,
                      val street: String?,
                      val lat: Long?,
                      val lng: Long?,
                      val printable_address: String?) {
}