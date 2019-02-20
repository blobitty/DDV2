package nyc.bbah.ddv2.model

data class Menus(val popularItems: PopularItems,
                 val is_catering: Boolean?,
                 val subtitle: String?,
                 val id: Int?,
                 val name: String) {
}

data class PopularItems(val price: Int,
                        val description: String?,
                        val img_url: String?,
                        val id: Int?,
                        val name: String) {

}
