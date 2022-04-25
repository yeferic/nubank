package com.yeferic.nubankshorter.presentation.linkshortener.viewmodels;

import android.annotation.SuppressLint;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yeferic.nubankshorter.domain.model.LinkShorter;
import com.yeferic.nubankshorter.domain.usecases.linkshortener.ShorterUrlUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class LinkShortenerViewModel extends ViewModel {
    private ShorterUrlUseCase useCase;
    private MutableLiveData<String> urlToShorten;
    private MutableLiveData<List<LinkShorter>> shortenedUrls;
    private MutableLiveData<Boolean> processing;

    @Inject
    public LinkShortenerViewModel(ShorterUrlUseCase useCase) {
        this.useCase = useCase;
        urlToShorten = new MutableLiveData<>();
        shortenedUrls = new MutableLiveData<>();
        shortenedUrls.setValue(new ArrayList<>());
        processing = new MutableLiveData<>();
        processing.setValue(false);
    }

    public void setUrlToShorten(String url) {
        this.urlToShorten.setValue(url);
    }

    public LiveData<String> getUrlToShorten() {
        return this.urlToShorten;
    }

    public LiveData<List<LinkShorter>> getShortenedUrls() {
        return shortenedUrls;
    }

    public LiveData<Boolean> getProcessing() {
        return processing;
    }

    public void setProcessingValue(Boolean processingValue){
        processing.setValue(processingValue);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public void addLinkShorterToList(LinkShorter linkShorter){
        this.shortenedUrls.getValue().add(linkShorter);
    }

    public void shortUrl(){
        setProcessingValue(true);
        useCase.shorterUrl(this.urlToShorten.getValue())
                .map(e -> {
                    addLinkShorterToList(e);
                    setProcessingValue(false);
                    return e;
                })
                .doOnError(error -> {
                    error.printStackTrace();
                    setProcessingValue(false);
                })
                .subscribeOn(Schedulers.io());
    }
}
