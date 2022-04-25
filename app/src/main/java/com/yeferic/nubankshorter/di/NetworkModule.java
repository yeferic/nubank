package com.yeferic.nubankshorter.di;

import com.yeferic.nubankshorter.data.repository.LinksShorternerRepositoryImp;
import com.yeferic.nubankshorter.data.source.remote.LinksShortenerRemoteService;
import com.yeferic.nubankshorter.util.Constanst;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    Retrofit providesRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(Constanst.URL_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    LinksShortenerRemoteService providesLinksShortenerRemoteService(Retrofit retrofit) {
        return retrofit.create(LinksShortenerRemoteService.class);
    }

    @Singleton
    @Provides
    LinksShorternerRepositoryImp providesLinksShortenerRepositoryImp(LinksShortenerRemoteService service){
        return new LinksShorternerRepositoryImp(service);
    }
}
