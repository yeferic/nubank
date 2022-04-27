package com.yeferic.nubankshorter.presentation.linkshortener.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;

import com.yeferic.nubankshorter.R;
import com.yeferic.nubankshorter.databinding.ActivityLinkShortenerBinding;
import com.yeferic.nubankshorter.domain.model.LinkShorter;
import com.yeferic.nubankshorter.presentation.linkshortener.adapters.LinkItemListAdapter;
import com.yeferic.nubankshorter.presentation.linkshortener.viewmodels.LinkShortenerViewModel;

import java.util.ArrayList;
import java.util.List;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LinkShortenerView extends AppCompatActivity {

    ActivityLinkShortenerBinding binding;
    private Parcelable recyclerViewState;

    private LinkShortenerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_shortener);
        getSupportActionBar().hide();
        viewModel = new ViewModelProvider(this).get(LinkShortenerViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_link_shortener);
        binding.setLifecycleOwner(this);
        initViewModelHandlers();
        initWidgetsHandlers();
    }

    private void initViewModelHandlers() {
        final Observer<String> urlToShortenObserver = url -> {
            binding.txtURLShorten.setText(url);
        };
        viewModel.getUrlToShorten().observe(this, urlToShortenObserver);

        final Observer<List<LinkShorter>> listLinksShortened = list -> {
            LinkItemListAdapter adpItems = new LinkItemListAdapter(list);
            binding.lsLinksShorted.setAdapter(adpItems);
            binding.lsLinksShorted.getLayoutManager().onRestoreInstanceState(recyclerViewState);
            viewModel.setUrlToShorten("");
        };

        viewModel.getShortenedUrls().observe(this,listLinksShortened);

        final Observer<Boolean> processingObserver = value -> {
            binding.pbSearch.setVisibility(value ? View.VISIBLE : View.INVISIBLE);
            binding.btnShorten.setClickable(!value);
            binding.txtURLShorten.setEnabled(!value);
        };
        viewModel.getProcessing().observeForever(processingObserver);
    }

    private void initWidgetsHandlers() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.lsLinksShorted.setLayoutManager(layoutManager);
        binding.lsLinksShorted.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.lsLinksShorted.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                recyclerViewState = binding.lsLinksShorted.getLayoutManager().onSaveInstanceState();
            }
        });

        binding.btnShorten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.txtURLShorten.getText().toString().isEmpty()){
                    viewModel.setUrlToShorten(binding.txtURLShorten.getText().toString());
                    viewModel.shortUrl();
                }
            }
        });
    }
}