package com.yeferic.nubankshorter.domain.repository;

import com.yeferic.nubankshorter.domain.model.LinkShorter;
import com.yeferic.nubankshorter.domain.model.RequestBodyLink;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface LinksShortenerRepository {
    Observable<LinkShorter> shortLink(RequestBodyLink _link);
}
