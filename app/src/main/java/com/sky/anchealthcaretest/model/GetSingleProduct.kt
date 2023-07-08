package com.sky.anchealthcaretest.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class GetSingleProduct {

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("title")
    @Expose
    private var title: String? = null

    @SerializedName("price")
    @Expose
    private var price: Float? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("category")
    @Expose
    private var category: String? = null

    @SerializedName("image")
    @Expose
    private var image: String? = null

    @SerializedName("rating")
    @Expose
    private var rating: Rating? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getPrice(): Float? {
        return price
    }

    fun setPrice(price: Float?) {
        this.price = price
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getCategory(): String? {
        return category
    }

    fun setCategory(category: String?) {
        this.category = category
    }

    fun getImage(): String? {
        return image
    }

    fun setImage(image: String?) {
        this.image = image
    }

    fun getRating(): Rating? {
        return rating
    }

    fun setRating(rating: Rating?) {
        this.rating = rating
    }

    class Rating {
        @SerializedName("rate")
        @Expose
        var rate: Float? = null

        @SerializedName("count")
        @Expose
        var count: Int? = null
    }


}