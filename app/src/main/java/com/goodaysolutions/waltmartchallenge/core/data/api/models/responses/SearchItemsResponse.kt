package com.goodaysolutions.waltmartchallenge.core.data.api.models.responses

import com.google.gson.annotations.SerializedName

data class SearchItemsResponse(
    @SerializedName("site_id")
    val siteID: SiteID,

    @SerializedName("country_default_time_zone")
    val countryDefaultTimeZone: String,

    val query: String,
    val paging: Paging,
    val results: List<Result>,
    val sort: Sort,

    @SerializedName("available_sorts")
    val availableSorts: List<Sort>,

    val filters: List<Filter>,

    @SerializedName("available_filters")
    val availableFilters: List<AvailableFilter>
)

data class AvailableFilter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<AvailableFilterValue>
)

data class AvailableFilterValue(
    val id: String,
    val name: String,
    val results: Long
)

data class Sort(
    val id: String? = null,
    val name: String
)

data class Filter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<FilterValue>
)

data class FilterValue(
    val id: String,
    val name: String,

    @SerializedName("path_from_root")
    val pathFromRoot: List<Sort>? = null
)

data class Paging(
    val total: Long,

    @SerializedName("primary_results")
    val primaryResults: Long,

    val offset: Long,
    val limit: Long
)

data class Result(
    val id: String,
    val title: String,
    val condition: String,

    @SerializedName("thumbnail_id")
    val thumbnailID: String,

    @SerializedName("catalog_product_id")
    val catalogProductID: String,

    @SerializedName("listing_type_id")
    val listingTypeID: String,

    val permalink: String,

    @SerializedName("buying_mode")
    val buyingMode: String,

    @SerializedName("site_id")
    val siteID: SiteID,

    @SerializedName("category_id")
    val categoryID: String,

    @SerializedName("domain_id")
    val domainID: String,

    @SerializedName("variation_id")
    val variationID: String,

    val thumbnail: String,

    @SerializedName("currency_id")
    val currencyID: String,

    @SerializedName("order_backend")
    val orderBackend: Long,

    val price: Double,

    @SerializedName("original_price")
    val originalPrice: Any? = null,

    @SerializedName("sale_price")
    val salePrice: Any? = null,

    @SerializedName("sold_quantity")
    val soldQuantity: Long,

    @SerializedName("available_quantity")
    val availableQuantity: Long,

    @SerializedName("official_store_id")
    val officialStoreID: Any? = null,

    @SerializedName("use_thumbnail_id")
    val useThumbnailID: Boolean,

    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean,

    val tags: List<ResultTag>,

    @SerializedName("variation_filters")
    val variationFilters: List<VariationFilter>,

    val shipping: Shipping,

    @SerializedName("stop_time")
    val stopTime: String,

    val seller: Seller,

    @SerializedName("seller_address")
    val sellerAddress: SellerAddress,

    val address: Address,
    val attributes: List<Attribute>,

    @SerializedName("variations_data")
    val variationsData: Map<String, VariationsDatum>,

    val installments: Installments,

    @SerializedName("winner_item_id")
    val winnerItemID: Any? = null,

    @SerializedName("catalog_listing")
    val catalogListing: Boolean,

    val discounts: Any? = null,
    val promotions: List<Any?>,

    @SerializedName("inventory_id")
    val inventoryID: Any? = null,

    @SerializedName("differential_pricing")
    val differentialPricing: DifferentialPricing? = null
)

data class DifferentialPricing(
    val id: Long
)

data class Address(
    @SerializedName("state_id")
    val stateID: String,

    @SerializedName("state_name")
    val stateName: String,

    @SerializedName("city_id")
    val cityID: String? = null,

    @SerializedName("city_name")
    val cityName: String
)

data class Attribute(
    val id: String,
    val name: String,

    @SerializedName("value_id")
    val valueID: String? = null,

    @SerializedName("value_name")
    val valueName: String,

    @SerializedName("attribute_group_id")
    val attributeGroupID: String,

    @SerializedName("attribute_group_name")
    val attributeGroupName: String,

    @SerializedName("value_struct")
    val valueStruct: Struct? = null,

    val values: List<AttributeValue>,
    val source: Long,

    @SerializedName("value_type")
    val valueType: String
)

data class Struct(
    val number: Double,
    val unit: String = ""
)

data class AttributeValue(
    val id: String? = null,
    val name: String,
    val struct: Struct? = null,
    val source: Long
)

data class Installments(
    val quantity: Long,
    val amount: Double,
    val rate: Double,

    @SerializedName("currency_id")
    val currencyID: String
)

data class Seller(
    val id: Long,
    val nickname: String,

    @SerializedName("car_dealer")
    val carDealer: Boolean,

    @SerializedName("real_estate_agency")
    val realEstateAgency: Boolean,

    @SerializedName("_")
    val empty: Boolean,

    @SerializedName("registration_date")
    val registrationDate: String,

    val tags: List<String>,

    @SerializedName("car_dealer_logo")
    val carDealerLogo: String,

    val permalink: String,

    @SerializedName("seller_reputation")
    val sellerReputation: SellerReputation,

    val eshop: Eshop? = null
)

data class Eshop(
    @SerializedName("eshop_id")
    val eshopID: Long,

    val seller: Long,

    @SerializedName("nick_name")
    val nickName: String,

    @SerializedName("eshop_status_id")
    val eshopStatusID: Long,

    @SerializedName("site_id")
    val siteID: String,

    @SerializedName("eshop_experience")
    val eshopExperience: Long,

    @SerializedName("eshop_rubro")
    val eshopRubro: Any? = null,

    @SerializedName("eshop_locations")
    val eshopLocations: List<Any?>? = null,

    @SerializedName("eshop_logo_url")
    val eshopLogoURL: String
)

data class SellerReputation(
    @SerializedName("level_id")
    val levelID: String? = null,
    @SerializedName("power_seller_status")
    val powerSellerStatus: Any? = null,
    val transactions: Transactions,
    val metrics: Metrics
)

data class Metrics(
    val sales: Sales,
    val claims: Cancellations,

    @SerializedName("delayed_handling_time")
    val delayedHandlingTime: Cancellations,

    val cancellations: Cancellations
)

data class Cancellations(
    val period: String,
    val rate: Double,
    val value: Long,
    val excluded: Excluded? = null
)

data class Excluded(
    @SerializedName("real_value")
    val realValue: Long,

    @SerializedName("real_rate")
    val realRate: Double
)

data class Sales(
    val period: String,
    val completed: Int
)


data class Transactions(
    val canceled: Long,
    val completed: Long,
    val period: String,
    val ratings: Ratings,
    val total: Long
)

data class Ratings(
    val negative: Double,
    val neutral: Double,
    val positive: Double
)

data class SellerAddress(
    val comment: String,

    @SerializedName("address_line")
    val addressLine: String,

    val id: Any? = null,
    val latitude: Any? = null,
    val longitude: Any? = null,
    val country: Sort,
    val state: Sort,
    val city: Sort
)

data class Shipping(
    @SerializedName("store_pick_up")
    val storePickUp: Boolean,

    @SerializedName("free_shipping")
    val freeShipping: Boolean,

    @SerializedName("logistic_type")
    val logisticType: String,

    val mode: String,
    val tags: List<String>,
    val benefits: Any? = null,
    val promise: Any? = null
)

enum class SiteID(val value: String) {
    Ml_ARG("MLA"), Ml_BRA("MLB"), Ml_MEX("MLM");

    companion object {
        fun fromValue(value: String): SiteID = when (value) {
            "MLA" -> Ml_ARG
            "MLB" -> Ml_BRA
            "MLM" -> Ml_MEX
            else -> Ml_ARG
        }
    }
}

enum class ResultTag(val value: String) {
    CartEligible("cart_eligible"),
    DraggedBidsAndVisits("dragged_bids_and_visits"),
    GoodQualityPicture("good_quality_picture"),
    ImmediatePayment("immediate_payment"),
    KvsPrimary("kvs_primary"),
    PoorQualityPicture("poor_quality_picture"),
    PoorQualityThumbnail("poor_quality_thumbnail"),
    ShippingGuaranteed("shipping_guaranteed"),
    StandardPriceByChannel("standard_price_by_channel");

    companion object {
        public fun fromValue(value: String): ResultTag = when (value) {
            "cart_eligible" -> CartEligible
            "dragged_bids_and_visits" -> DraggedBidsAndVisits
            "good_quality_picture" -> GoodQualityPicture
            "immediate_payment" -> ImmediatePayment
            "kvs_primary" -> KvsPrimary
            "poor_quality_picture" -> PoorQualityPicture
            "poor_quality_thumbnail" -> PoorQualityThumbnail
            "shipping_guaranteed" -> ShippingGuaranteed
            "standard_price_by_channel" -> StandardPriceByChannel
            else -> throw IllegalArgumentException()
        }
    }
}

enum class VariationFilter(val value: String) {
    Color("COLOR");

    companion object {
        public fun fromValue(value: String): VariationFilter = when (value) {
            "COLOR" -> Color
            else -> throw IllegalArgumentException()
        }
    }
}

data class VariationsDatum(
    val thumbnail: String,
    val ratio: String,
    val name: String,

    @SerializedName("pictures_qty")
    val picturesQty: Long
)
