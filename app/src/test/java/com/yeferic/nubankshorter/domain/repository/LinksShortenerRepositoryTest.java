package com.yeferic.nubankshorter.domain.repository;

import com.yeferic.nubankshorter.data.repository.LinksShorternerRepositoryImp;
import com.yeferic.nubankshorter.data.source.remote.LinksShortenerRemoteService;
import com.yeferic.nubankshorter.domain.model.LinkShorter;
import com.yeferic.nubankshorter.domain.model.Links;
import com.yeferic.nubankshorter.domain.model.RequestBodyLink;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

public class LinksShortenerRepositoryTest extends TestCase {

    @Mock
    private LinksShortenerRemoteService mockService;
    private LinksShorternerRepositoryImp linksShortenerRepositoryImp;

    @Before
    public void setUp(){
        mockService = Mockito.mock(LinksShortenerRemoteService.class);
        linksShortenerRepositoryImp = new LinksShorternerRepositoryImp(mockService);
    }

    @Test
    public void testShortLink() {
        //Arrange
        String textInput = "www.google.com.co";
        Mockito.when(mockService.shortLink(Mockito.any())).thenReturn(Observable.just(new LinkShorter(
                "",
                new Links(textInput,"")
        )));

        //Act

        //Assert
        linksShortenerRepositoryImp.shortLink(new RequestBodyLink(textInput)).test().assertValue(new Predicate<LinkShorter>() {
            @Override
            public boolean test(LinkShorter linkShorter) throws Exception {
                assertEquals(textInput,linkShorter.getLinks().getSelf());
                return true;
            }
        });
    }
}