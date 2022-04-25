package com.yeferic.nubankshorter.domain.repository;

import com.yeferic.nubankshorter.data.repository.LinksShorternerRepositoryImp;
import com.yeferic.nubankshorter.data.source.remote.LinksShortenerRemoteService;
import com.yeferic.nubankshorter.domain.model.LinkShorter;
import com.yeferic.nubankshorter.domain.model.RequestBodyLink;
import com.yeferic.nubankshorter.util.Constanst;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.functions.Predicate;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LinksShortenerRepositoryTest extends TestCase {

    private LinksShortenerRemoteService fakeService;

    @Before
    public void setUp(){
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(Constanst.URL_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        fakeService = retrofit.create(LinksShortenerRemoteService.class);
    }

    @Test
    public void testShortLink() {
        //Arrange
        String textInput = "www.google.com.co";
        LinksShorternerRepositoryImp linksShortenerRepositoryImp = new LinksShorternerRepositoryImp(fakeService);

        //Act

        //Assert
        linksShortenerRepositoryImp.shortLink(new RequestBodyLink(textInput)).test().assertValue(new Predicate<LinkShorter>() {
            @Override
            public boolean test(LinkShorter linkShorter) throws Exception {
                assertEquals(linkShorter.getLinks().getSelf(), textInput);
                return true;
            }
        });
    }
}