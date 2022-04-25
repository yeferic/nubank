package com.yeferic.nubankshorter.data.repository;

import com.yeferic.nubankshorter.data.source.remote.LinksShortenerRemoteService;
import com.yeferic.nubankshorter.domain.model.LinkShorter;
import com.yeferic.nubankshorter.domain.model.RequestBodyLink;
import com.yeferic.nubankshorter.domain.repository.LinksShortenerRepository;

import io.reactivex.Observable;
import io.reactivex.Single;

public class LinksShorternerRepositoryImp implements LinksShortenerRepository {
    private LinksShortenerRemoteService service;

    public LinksShorternerRepositoryImp(LinksShortenerRemoteService service) {
        this.service = service;
    }

    @Override
    public Observable<LinkShorter> shortLink(RequestBodyLink _link) {
        return service.shortLink(_link);
    }
}
