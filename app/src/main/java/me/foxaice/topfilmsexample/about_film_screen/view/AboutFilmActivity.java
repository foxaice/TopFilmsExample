package me.foxaice.topfilmsexample.about_film_screen.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.foxaice.topfilmsexample.R;
import me.foxaice.topfilmsexample.main_screen.model.Film;

public class AboutFilmActivity extends Activity {
    private static final String EXTRA_FILM = "EXTRA_FILM";

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static void startActivity(Context context, Film film, Bundle bundle) {
        context.startActivity(new Intent(context, AboutFilmActivity.class)
                .putExtra(EXTRA_FILM, film), bundle);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_film);
        initBackButton();
        loadInfo(getIntent().getExtras());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void loadInfo(Bundle extras) {
        if (extras.containsKey(EXTRA_FILM)) {
            Film film = (Film) extras.get(EXTRA_FILM);
            if (film != null) {
                ImageView filmCover = findViewById(R.id.activity_about_film_cover_image);
                TextView originalName = findViewById(R.id.activity_about_film_original_name_text);
                TextView issueYear = findViewById(R.id.activity_about_film_issue_year_text);
                TextView rating = findViewById(R.id.activity_about_film_rating_text);
                TextView description = findViewById(R.id.activity_about_film_description_text);
                TextView header = findViewById(R.id.activity_about_film_toolbar_header_text);
                Picasso.with(this)
                        .load((String) film.getImageUrl())
                        .placeholder(R.drawable.no_poster)
                        .error(R.drawable.no_poster)
                        .into(filmCover);
                header.setText(film.getLocalName());
                originalName.setText(film.getOriginalName());
                issueYear.setText(getString(R.string.year, film.getIssueYear()));
                rating.setText(getString(R.string.rating, film.getRating()));
                description.setText(film.getDescription());
            }
        }
    }

    private void initBackButton() {
        View backArrow = findViewById(R.id.activity_about_film_toolbar_back_image);
        backArrow.setOnTouchListener(new View.OnTouchListener() {
            private ImageView backgroundImage = (ImageView) findViewById(R.id.activity_about_film_toolbar_back_image_background);

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    backgroundImage.setPressed(false);
                    float x = event.getX();
                    float y = event.getY();
                    if (x > 0 && x < v.getWidth() && y > 0 && y < v.getHeight()) {
                        onBackPressed();
                    }
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    backgroundImage.setPressed(true);
                }
                return true;
            }
        });
    }
}
