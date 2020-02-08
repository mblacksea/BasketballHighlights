package com.oxygenmobile.basketballhighlights.model;

public class BasketballHighlightsUrl {

    private String playListApi;
    private String playListVideo;
    private String topPlayList;

    public BasketballHighlightsUrl() {
    }

    public BasketballHighlightsUrl(String playListApi, String playListVideo, String topPlayList) {
        this.playListApi = playListApi;
        this.playListVideo = playListVideo;
        this.topPlayList = topPlayList;
    }

    public String getPlayListApi() {
        return playListApi;
    }

    public void setPlayListApi(String playListApi) {
        this.playListApi = playListApi;
    }

    public String getPlayListVideo() {
        return playListVideo;
    }

    public void setPlayListVideo(String playListVideo) {
        this.playListVideo = playListVideo;
    }

    public String getTopPlayList() {
        return topPlayList;
    }

    public void setTopPlayList(String topPlayList) {
        this.topPlayList = topPlayList;
    }
}
