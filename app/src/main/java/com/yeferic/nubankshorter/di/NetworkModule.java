package com.yeferic.nubankshorter.di;

import com.yeferic.nubankshorter.data.repository.LinksShorternerRepositoryImp;
import com.yeferic.nubankshorter.data.source.remote.LinksShortenerRemoteService;
import com.yeferic.nubankshorter.common.Constanst;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@InstallIn(SingletonComponent.class)
@Module
public class NetworkModule {

    @Provides
    @Singleton
    Retrofit providesRetrofitInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl(Constanst.URL_SERVICE)
                .client(client)
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
