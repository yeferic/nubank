package com.yeferic.nubankshorter.presentation.linkshortener.viewmodels;

import android.util.Log;

import androidx.annotation.MainThread;
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
import io.reactivex.android.schedulers.AndroidSchedulers;
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
        urlToShorten.setValue("");
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

    @VisibleForTesting
    void addLinkShorterToList(LinkShorter linkShorter){
        this.shortenedUrls.getValue().add(0,linkShorter);
        this.shortenedUrls.setValue(this.shortenedUrls.getValue());
    }

    public void shortUrl() {
        setProcessingValue(true);

        useCase.shorterUrl(this.urlToShorten.getValue())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(error -> {
                    error.printStackTrace();
                    setProcessingValue(false);
                })
                .subscribeOn(Schedulers.io())
                .subscribe(x -> {
                    addLinkShorterToList(x);
                    setProcessingValue(false);
                });
    }
}
