package com.tubes.diabetescare;

public class Sport {

    // Member variables representing the title and information about the sport.
    private String title;
    private String subTitle;
    private final int imageResource;
    public String newsURL;

    public Sport(String title, String subTitle, int imageResource, String newsURL) {
        this.title = title;
        this.subTitle = subTitle;
        this.imageResource = imageResource;
        this.newsURL = newsURL;
    }

    String getTitle() {
        return title;
    }

    String getSubTitle() {
        return subTitle;
    }

    public int getImageResource() {
        return imageResource;
    }

    String getNewsURL() {
        return newsURL;
    }
}
