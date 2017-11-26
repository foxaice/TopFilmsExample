package me.foxaice.topfilmsexample.main_screen.view.adapter;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.foxaice.topfilmsexample.R;
import me.foxaice.topfilmsexample.main_screen.model.Film;
import me.foxaice.topfilmsexample.main_screen.view.MainScreenActivity;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {
    private final List<Film> mFilms;
    private final LayoutInflater mLayoutInflater;
    private final int mRowLayoutId;
    private final MainScreenActivity mActivity;

    public FilmAdapter(MainScreenActivity activity, List<Film> films, int rowLayoutId) {
        mFilms = films;
        mLayoutInflater = activity.getLayoutInflater();
        mRowLayoutId = rowLayoutId;
        mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(mRowLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Film film = mFilms.get(position);
        final ImageView filmCover = holder.filmCover;
        holder.rating.setText(String.valueOf(film.getRating()));
        holder.localName.setText(film.getLocalName());
        holder.originalName.setText(film.getOriginalName());
        holder.filmCover.setAlpha(.35f);
        holder.filmCover.post(new Runnable() {
            @Override
            public void run() {
                Picasso.with(filmCover.getContext())
                        .load((String) film.getImageUrl())
                        .placeholder(R.drawable.place_holder)
                        .resize(filmCover.getWidth(), filmCover.getHeight())
                        .centerCrop()
                        .into(filmCover);
            }
        });
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Bundle bundle =
                            null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        bundle = ActivityOptions.makeClipRevealAnimation(filmCover,
                                (int) event.getX(), (int) event.getY(),
                                0, 0).toBundle();
                    }
                    mActivity.startAboutFilmActivity(film, bundle);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFilms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView originalName;
        private final TextView localName;
        private final TextView rating;
        private final ImageView filmCover;

        public ViewHolder(View itemView) {
            super(itemView);
            originalName = itemView.findViewById(R.id.recycler_original_name);
            localName = itemView.findViewById(R.id.recycler_local_name);
            rating = itemView.findViewById(R.id.recycler_rating);
            filmCover = itemView.findViewById(R.id.recycler_film_cover);
        }
    }
}
