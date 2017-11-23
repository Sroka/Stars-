package com.maciej.srokowski.stars.features.stars;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.maciej.srokowski.stars.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class StarsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewKittens)
    RecyclerView recyclerViewKittens;

    @Inject
    StarsViewModelFactory viewModelFactory;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.viewError)
    TextView viewError;

    private StarsAdapter starsAdapter;
    private StarsViewModel starsViewModel;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stars);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupRecyclerView();
        starsViewModel = ViewModelProviders.of(this, viewModelFactory).get(StarsViewModel.class);
        observeError();
        observeProgress();
        observeStars();
        starsViewModel.loadStars();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    public void observeError() {
        compositeDisposable.add(starsViewModel.getError()
                .subscribe(showError -> viewError.setVisibility(showError ? VISIBLE : GONE)));
    }

    public void observeProgress() {
        compositeDisposable.add(starsViewModel.getProgress()
                .subscribe(showProgress -> progressBar.setVisibility(showProgress ? VISIBLE : GONE)));
    }

    public void observeStars() {
        compositeDisposable.add(starsViewModel.getStars().subscribe(stars -> {
            //We could do DiffUtil async but it is outside of a scope of this exercise
            starsAdapter.setStars(stars);
        }));
    }

    private void setupRecyclerView() {
        recyclerViewKittens.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewKittens.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        starsAdapter = new StarsAdapter();
        recyclerViewKittens.setAdapter(starsAdapter);
    }

}
