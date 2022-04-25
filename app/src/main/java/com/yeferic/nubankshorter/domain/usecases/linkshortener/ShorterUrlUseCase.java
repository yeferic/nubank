package com.yeferic.nubankshorter.domain.usecases.linkshortener;

import com.yeferic.nubankshorter.data.repository.LinksShorternerRepositoryImp;
import com.yeferic.nubankshorter.domain.model.LinkShorter;
import com.yeferic.nubankshorter.domain.model.RequestBodyLink;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ShorterUrlUseCase {
    private LinksShorternerRepositoryImp linksShorternerRepositoryImp;

    @Inject
    public ShorterUrlUseCase(LinksShorternerRepositoryImp linksShorternerRepositoryImp) {
        this.linksShorternerRepositoryImp = linksShorternerRepositoryImp;
    }

    public Observable<LinkShorter> shorterUrl(String url){
       return linksShorternerRepositoryImp.shortLink(new RequestBodyLink(url));
    }

}
