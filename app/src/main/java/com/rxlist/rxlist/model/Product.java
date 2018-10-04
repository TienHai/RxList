package com.rxlist.rxlist.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import static com.rxlist.rxlist.model.ModelUtils.decodeString;

public class Product implements Serializable {
    /*
    {
"id":1527832569,
"clusterId":5516208,
"isDigital":false,
"urlName":"apple-iphone-7",
"productScope":"PUBLIC",
"bestPrice":495.8,
"newBestPrice":539.9,
"usedBestPrice":495.8,
"collectibleBestPrice":0.0,
"advertsCount":87,
"advertsNewCount":32,
"advertsUsedCount":55,
"advertsCollectibleCount":0,
"headline":"Apple iPhone 7 32 Go Noir mat",
"caption":"Apple",
"topic":"Mobile",
"reviewsAverageNote":4.666666666666667,
"nbReviews":87,
"imagesUrls":["https://pmcdn.priceminister.com/photo/1120739112.jpg"],
"pickupAllowed":false,
"isPreOrder":false,
"releaseDate":null,
"attributes":null,
"isMemo":false,
"isMevFormAvailable":false,
"isNotModifiedSinceLastCrawl":false,
"categoryRef":194695,
"prdCategory":"[Tel-PDA, Tel-PDA_Telephones-mobiles]",
"images":
  [
    {"imagesUrls":{"entry":[{"size":"LARGE","url":"https://pmcdn.priceminister.com/photo/1120739112_L.jpg"},{"size":"ORIGINAL","url":"https://pmcdn.priceminister.com/photo/1120739112.jpg"},{"size":"MEDIUM","url":"https://pmcdn.priceminister.com/photo/1120739112_M.jpg"},{"size":"SMALL","url":"https://pmcdn.priceminister.com/photo/1120739112_S.jpg"}]},"id":1120739112},
    {"imagesUrls":{"entry":[{"size":"LARGE","url":"https://pmcdn.priceminister.com/photo/1120739113_L.jpg"},{"size":"ORIGINAL","url":"https://pmcdn.priceminister.com/photo/1120739113.jpg"},{"size":"MEDIUM","url":"https://pmcdn.priceminister.com/photo/1120739113_M.jpg"},{"size":"SMALL","url":"https://pmcdn.priceminister.com/photo/1120739113_S.jpg"}]},"id":1120739113},
    {"imagesUrls":{"entry":[{"size":"LARGE","url":"https://pmcdn.priceminister.com/photo/1120739114_L.jpg"},{"size":"ORIGINAL","url":"https://pmcdn.priceminister.com/photo/1120739114.jpg"},{"size":"MEDIUM","url":"https://pmcdn.priceminister.com/photo/1120739114_M.jpg"},{"size":"SMALL","url":"https://pmcdn.priceminister.com/photo/1120739114_S.jpg"}]},"id":1120739114},
    {"imagesUrls":{"entry":[{"size":"LARGE","url":"https://pmcdn.priceminister.com/photo/1107577441_L.jpg"},{"size":"ORIGINAL","url":"https://pmcdn.priceminister.com/photo/1107577441.jpg"},{"size":"MEDIUM","url":"https://pmcdn.priceminister.com/photo/1107577441_M.jpg"},{"size":"SMALL","url":"https://pmcdn.priceminister.com/photo/1107577441_S.jpg"}]},"id":1107577441}
  ],
"rspMinimumAmount":2480,
"rspCampaignDiscount":24.8
}
     */

    @SerializedName("id")
    private String _id;
    @SerializedName("clusterId")
    private String _clusterId;
    @SerializedName("newBestPrice")
    private Float _newBestPrice;
    @SerializedName("headline")
    private String _headline;
    @SerializedName("caption")
    private String _caption;
    @SerializedName("topic")
    private String _topic;
    @SerializedName("reviewsAverageNote")
    private Float _reviewsAverageNote;
    @SerializedName("imagesUrls")
    private ArrayList<String> _imagesUrls;
    @SerializedName("nbReviews")
    private int _nbReviews;

    public Product(
            String id, String clusterId, Float newBestPrice, String headline, String caption, String topic,
            Float reviewsAverageNote, ArrayList<String> imagesUrls, int nbReviews) {
        _id = id;
        _clusterId = clusterId;
        _newBestPrice = newBestPrice;
        _headline = headline;
        _caption = caption;
        _topic = topic;
        _reviewsAverageNote = reviewsAverageNote;
        _imagesUrls = imagesUrls;
        _nbReviews = nbReviews;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getClusterId() {
        return _clusterId;
    }

    public void setClusterId(String cludterId) {
        _clusterId = cludterId;
    }

    public Float getNewBestPrice() {
        return _newBestPrice;
    }

    public void setNewBestPrice(Float newBestPrice) {
        _newBestPrice = newBestPrice;
    }

    public String getHeadline() {
        return decodeString(_headline);
    }

    public void setHeadline(String title) {
        _headline = title;
    }

    public String getCaption() {
        return decodeString(_caption);
    }

    public void setCaption(String caption) {
        _caption = caption;
    }

    public String getTopic() {
        return decodeString(_topic);
    }

    public void setTopic(String topic) {
        _topic = topic;
    }

    public Float getReviewsAverageNote() {
        return _reviewsAverageNote;
    }

    public void setReviewsAverageNote(Float reviewsAverageNote) {
        _reviewsAverageNote = reviewsAverageNote;
    }

    public ArrayList<String> getImageUrls() {
        return _imagesUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        _imagesUrls = imageUrls;
    }

    public int getNbReviews() {
        return _nbReviews;
    }

    public void setNbReviews(int nbReviews) {
        _nbReviews = nbReviews;
    }
}