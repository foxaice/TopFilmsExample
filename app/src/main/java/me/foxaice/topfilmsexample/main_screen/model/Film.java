package me.foxaice.topfilmsexample.main_screen.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Film implements Comparable<Film>, Parcelable {
    private CharSequence mLocalName;
    private CharSequence mOriginalName;
    private CharSequence mImageUrl;
    private CharSequence mDescription;
    private double mRating;
    private short mIssueYear;

    public Film(CharSequence localName, CharSequence originalName, CharSequence imageUrl, CharSequence description, double rating, short issueYear) {
        mLocalName = localName;
        mOriginalName = originalName;
        mImageUrl = imageUrl;
        mDescription = description;
        mRating = rating;
        mIssueYear = issueYear;
    }

    public CharSequence getLocalName() {
        return mLocalName;
    }

    public CharSequence getOriginalName() {
        return mOriginalName;
    }

    public CharSequence getImageUrl() {
        return mImageUrl;
    }

    public CharSequence getDescription() {
        return mDescription;
    }

    public double getRating() {
        return mRating;
    }

    public short getIssueYear() {
        return mIssueYear;
    }

    @Override
    public int compareTo(@NonNull Film film) {
        if (this.getIssueYear() > film.getIssueYear()) {
            return 1;
        } else if (this.getIssueYear() < film.getIssueYear()) {
            return -1;
        } else {
            if (this.getRating() > film.getRating()) {
                return -1;
            } else if (this.getRating() < film.getRating()) {
                return 1;
            } else {
                return 0;
            }
        }
    }


    protected Film(Parcel in) {
        mLocalName = (CharSequence) in.readValue(CharSequence.class.getClassLoader());
        mOriginalName = (CharSequence) in.readValue(CharSequence.class.getClassLoader());
        mImageUrl = (CharSequence) in.readValue(CharSequence.class.getClassLoader());
        mDescription = (CharSequence) in.readValue(CharSequence.class.getClassLoader());
        mRating = in.readDouble();
        mIssueYear = (short) in.readValue(short.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mLocalName);
        dest.writeValue(mOriginalName);
        dest.writeValue(mImageUrl);
        dest.writeValue(mDescription);
        dest.writeDouble(mRating);
        dest.writeValue(mIssueYear);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}
