package me.foxaice.topfilmsexample.main_screen.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.foxaice.topfilmsexample.R;
import me.foxaice.topfilmsexample.about_film_screen.view.AboutFilmActivity;
import me.foxaice.topfilmsexample.main_screen.model.Film;
import me.foxaice.topfilmsexample.main_screen.model.FilmRepo;
import me.foxaice.topfilmsexample.main_screen.view.adapter.FilmAdapter;
import me.foxaice.topfilmsexample.main_screen.view.adapter.RecycleSection;

public class MainScreenActivity extends Activity {
    private static String KEY_FILMS = "KEY_FILMS";
    private List<Film> mFilms;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        loadFilms(savedInstanceState);

        RecyclerView recyclerView = findViewById(R.id.activity_main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new FilmAdapter(this, mFilms, R.layout.recycler_row));
        recyclerView.addItemDecoration(new RecycleSection(
                getResources().getDimensionPixelSize(R.dimen.list_header_height),
                MainScreenActivity.getSectionCallback(mFilms)
        ));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mFilms != null) {
            outState.putParcelableArrayList(KEY_FILMS, (ArrayList<? extends Parcelable>) mFilms);
        }
    }

    public void startAboutFilmActivity(Film film, Bundle bundle) {
        AboutFilmActivity.startActivity(this, film, bundle);
    }

    private void loadFilms(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mFilms = savedInstanceState.getParcelableArrayList(KEY_FILMS);
        }
        if (mFilms == null) {
            mFilms = FilmRepo.loadFilm(this);
        }
    }

    private static RecycleSection.SectionCallback getSectionCallback(final List<Film> films) {
        return new RecycleSection.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0 ||
                        films.get(position).getIssueYear() != films.get(position - 1).getIssueYear();
            }

            @Override
            public short getSectionHeader(int position) {
                return films.get(position).getIssueYear();
            }
        };
    }
}
