package com.rxlist.rxlist.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import static com.rxlist.rxlist.model.ModelUtils.decodeString;

public class ProductItem {

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

    public ProductItem(String id, String clusterId, Float newBestPrice, String headline,
                       String caption, String topic, Float reviewsAverageNote,
                       ArrayList<String> imagesUrls, int nbReviews) {
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

    public void setClusterId(String clusterId) {
        _clusterId = clusterId;
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