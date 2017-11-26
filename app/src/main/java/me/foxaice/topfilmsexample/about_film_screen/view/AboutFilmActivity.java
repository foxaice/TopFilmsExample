package me.foxaice.topfilmsexample.about_film_screen.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.foxaice.topfilmsexample.R;

public class AboutFilmActivity extends Activity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_film);
        ImageView filmCover = findViewById(R.id.activity_about_film_cover_image);
        TextView originalName = findViewById(R.id.activity_about_film_original_name_text);
        TextView issueYear = findViewById(R.id.activity_about_film_issue_year_text);
        TextView rating = findViewById(R.id.activity_about_film_rating_text);
        TextView description = findViewById(R.id.activity_about_film_description_text);
        initBackButton();
        Picasso.with(this)
                .load("https://st.kp.yandex.net/images/film_iphone/iphone360_2360.jpg")
                .into(filmCover);
        originalName.setText("Fight Club");
        issueYear.setText("Год: 1994");
        rating.setText("Рейтинг: 9.196");
        description.setText("Терзаемый​ ​ хронической​ ​ бессонницей​ ​ и ​ ​ отчаянно пытающийся​ ​ вырваться​ ​ из​ ​ мучительно​ ​ скучной​ ​ жизни,​ ​ клерк​ ​ встречает некоего​ ​ Тайлера​ ​ Дардена,​ ​ харизматического​ ​ торговца​ ​ мылом​ ​ с извращенной​ ​ философией.​ ​ Тайлер​ ​ уверен,​ ​ что​ ​ самосовершенствование​ ​ — удел​ ​ слабых,​ ​ а ​ ​ саморазрушение​ ​ — ​ ​ единственное,​ ​ ради​ ​ чего​ ​ стоит​ ​ жить. Пройдет​ ​ немного​ ​ времени,​ ​ и ​ ​ вот​ ​ уже​ ​ главные​ ​ герои​ ​ лупят​ ​ друг​ ​ друга почем​ ​ зря​ ​ на​ ​ стоянке​ ​ перед​ ​ баром,​ ​ и ​ ​ очищающий​ ​ мордобой​ ​ доставляет​ ​ им высшее​ ​ блаженство.​ ​ Приобщая​ ​ других​ ​ мужчин​ ​ к ​ ​ простым​ ​ радостям физической​ ​ жестокости,​ ​ они​ ​ основывают​ ​ тайный​ ​ Бойцовский​ ​ Клуб,​ ​ который имеет​ ​ огромный​ ​ успех.​ ​ Но​ ​ в ​ ​ концовке​ ​ фильма​ ​ всех​ ​ ждет​ ​ шокирующее открытие,​ ​ которое​ ​ может​ ​ привести​ ​ к ​ ​ непредсказуемым​ ​ событиям...");
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
