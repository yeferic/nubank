package com.yeferic.nubankshorter.data.source.remote;

import com.yeferic.nubankshorter.domain.model.LinkShorter;
import com.yeferic.nubankshorter.domain.model.RequestBodyLink;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LinksShortenerRemoteService {
    @POST("api/alias")
    Observable<LinkShorter> shortLink(@Body RequestBodyLink request);
}
