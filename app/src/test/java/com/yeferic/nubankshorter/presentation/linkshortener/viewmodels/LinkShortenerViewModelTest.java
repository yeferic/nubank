package com.yeferic.nubankshorter.presentation.linkshortener.viewmodels;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.SavedStateHandle;

import com.yeferic.nubankshorter.data.repository.LinksShorternerRepositoryImp;
import com.yeferic.nubankshorter.data.source.remote.LinksShortenerRemoteService;
import com.yeferic.nubankshorter.domain.model.LinkShorter;
import com.yeferic.nubankshorter.domain.model.Links;
import com.yeferic.nubankshorter.domain.model.RequestBodyLink;
import com.yeferic.nubankshorter.domain.usecases.linkshortener.ShorterUrlUseCase;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

@RunWith(MockitoJUnitRunner.class)
public class LinkShortenerViewModelTest extends TestCase {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private ShorterUrlUseCase useCase;

    private LinkShortenerViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        useCase = Mockito.mock(ShorterUrlUseCase.class);
        viewModel = new LinkShortenerViewModel(useCase);
    }

    @Test
    public void testGetUrlToShorten_null() {
        //Arrange

        //Act

        //Assert
        assertNull(viewModel.getUrlToShorten().getValue());
    }

    @Test
    public void testGetUrlToShorten_NotNull() {
        //Arrange

        //Act
        viewModel.setUrlToShorten("");

        //Assert
        assertNotNull(viewModel.getUrlToShorten().getValue());
    }

    @Test
    public void testSetUrlToShorten() {
        //Arrange
        String fake_url = "fake_url";

        //Act
        viewModel.setUrlToShorten(fake_url);

        //Assert
        assertEquals(fake_url,viewModel.getUrlToShorten().getValue());
    }

    @Test
    public void testGetProcessing_NotNull() {
        //Arrange

        //Act

        //Assert
        assertNotNull(viewModel.getProcessing().getValue());
    }

    @Test
    public void testGgetShortenedUrls_Empty() {
        //Arrange

        //Act

        //Assert
        assertEquals(0,viewModel.getShortenedUrls().getValue().size());
    }

    @Test
    public void testSetProcessingValue() {
        //Arrange
        Boolean processing = true;

        //Act
        viewModel.setProcessingValue(processing);

        //Assert
        assertEquals(processing, viewModel.getProcessing().getValue());
    }

    @Test
    public void testShortUrl_changeProcessingValue() {
        //Arrange
        String url = "www.google.com";
        Boolean processing = false;

        Mockito.when(useCase.shorterUrl(Mockito.any())).thenReturn(Observable.just(new LinkShorter(
                "",
                new Links()
        )));

        //Act
        viewModel.setProcessingValue(processing);
        viewModel.setUrlToShorten(url);
        viewModel.shortUrl();

        //Assert
        assertTrue(viewModel.getProcessing().getValue());
    }

    @Test
    public void testShortUrl() {
        //Arrange
        String url = "www.google.com";

        Mockito.when(useCase.shorterUrl(Mockito.any())).thenReturn(Observable.just(new LinkShorter(
                "",
                new Links()
        )));

        //Act
        viewModel.setUrlToShorten(url);
        viewModel.shortUrl();

        //Assert
        Mockito.verify(useCase).shorterUrl(Mockito.any());
    }

    @Test
    public void testAddLinkShorterToList() {
        //Arrange
        LinkShorter linkShorter = Mockito.mock(LinkShorter.class);

        //Act
        viewModel.addLinkShorterToList(linkShorter);

        //Assert
        viewModel.getShortenedUrls().observeForever(new androidx.lifecycle.Observer<List<LinkShorter>>() {
            @Override
            public void onChanged(List<LinkShorter> linkShorters) {
                assertEquals(1, linkShorters.size());
            }
        });
    }
}