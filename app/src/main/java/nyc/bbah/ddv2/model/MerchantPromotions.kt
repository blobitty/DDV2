package nyc.bbah.ddv2.model

data class MerchantPromotions(val minimum_subtotal_monetary_fields: MinimumSubtotalMonetaryFields?,
                              val deliveryFee: Int?,
                              val delivery_fee_monetary_fields: DeliveryFeeMonetaryFields?,
                              val minimum_subtotal: Int?,
                              val new_store_customers_only: Boolean,
                              val id: Int?){


data class MinimumSubtotalMonetaryFields(val currency: String?,
                                         val display_string: String?,
                                         val unit_amount: Int?,
                                         val decimal_places: Int?) {

}

data class DeliveryFeeMonetaryFields(val currency: String?,
                                     val display_string: String?,
                                     val unit_amount: Int?,
                                     val decimal_places: Int?) {

}

}
