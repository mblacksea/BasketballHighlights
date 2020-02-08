package com.oxygenmobile.basketballhighlights.retrofit;

import com.oxygenmobile.basketballhighlights.model.PlayListAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIInterface {

    @GET
    Call<PlayListAPI> inquireNbaHighlightsPlatList(@Url String url);
}
