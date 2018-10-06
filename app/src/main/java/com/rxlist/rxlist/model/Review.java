package com.rxlist.rxlist.model;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("note")
    private int _note;
    @SerializedName("title")
    private String _title;
    @SerializedName("author")
    private Author _author;
    @SerializedName("date")
    private long _date;
    @SerializedName("description")
    private String _description;

    public Review(int note, String title, Author author, long date, String description) {
        _note = note;
        _title = title;
        _author = author;
        _date = date;
        _description = description;
    }

    public int getNote() {
        return _note;
    }

    public void setNote(int value) {
        _note = value;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String value) {
        _title = value;
    }

    public Author getAuthor() {
        return  _author;
    }

    public void setAuthor(Author value) {
        _author = value;
    }

    public long getDate() {
        return _date;
    }

    public void setDate(long value) {
        _date = value;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String value) {
        _description = value;
    }

    private class Author {
        @SerializedName("login")
        private String _login;
        @SerializedName("firstName")
        private String _firstName;

        public String getName() {
            return _firstName;
        }
    }
}
