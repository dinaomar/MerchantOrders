package com.gmail.dina_elsaftawy.merchantorder.model.data;

public class order {
    private String title;
    private String imageUrl;
    private String description;

    public order(String title, String imageUrl, String description) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }
}
