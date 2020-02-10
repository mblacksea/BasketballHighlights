package com.oxygenmobile.basketballhighlights.retrofit;

import com.oxygenmobile.basketballhighlights.model.PlayListAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface APIInterface {
    /**
     * inquireNbaHighlightsPlayList & inquireTop10PlayList similarly methods.
     * Maybe this API's can be changed in the future.
     *
     * @param url
     * @return
     */

    @GET
    Call<PlayListAPI> inquireNbaAllHighlightsPlayList(@Url String url);

    @GET
    Call<PlayListAPI> inquireNbaHighlightsPlayListById(@Url String url, @Query("playlistId") String playlistId);

    @GET
    Call<PlayListAPI> inquireTop10PlayList(@Url String url);
}
