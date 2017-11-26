package me.foxaice.topfilmsexample.main_screen.view.adapter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.foxaice.topfilmsexample.R;

public class RecycleSection extends RecyclerView.ItemDecoration {
    private final int mHeaderOffset;
    private final SectionCallback mSectionCallback;

    private TextView mHeaderText;

    public RecycleSection(int headerOffset, @NonNull SectionCallback sectionCallback) {
        mHeaderOffset = headerOffset;
        mSectionCallback = sectionCallback;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (mHeaderText == null) {
            mHeaderText = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_section_header, parent, false);
            fixLayoutSize(mHeaderText, parent);
        }

        short previousHeader = 0;
        for (int i = 0, length = parent.getChildCount(); i < length; i++) {
            View child = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(child);
            short year = mSectionCallback.getSectionHeader(position);
            mHeaderText.setText(String.valueOf(year));
            if (previousHeader != year || mSectionCallback.isSection(position)) {
                drawHeader(c, child, mHeaderText);
                previousHeader = year;
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (mSectionCallback.isSection(position)) {
            outRect.top = mHeaderOffset;
        }
    }

    private void drawHeader(Canvas canvas, View child, View header) {
        canvas.save();
        canvas.translate(0, Math.max(0, child.getTop() - header.getHeight()));
        header.draw(canvas);
        canvas.restore();
    }

    private void fixLayoutSize(View v, ViewGroup parent) {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.UNSPECIFIED);
        int childWidth = ViewGroup.getChildMeasureSpec(widthSpec, parent.getPaddingLeft() + parent.getPaddingRight(), v.getLayoutParams().width);
        int childHeight = ViewGroup.getChildMeasureSpec(heightSpec, parent.getPaddingTop() + parent.getPaddingBottom(), v.getLayoutParams().height);

        v.measure(childWidth, childHeight);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
    }

    public interface SectionCallback {
        boolean isSection(int position);
        short getSectionHeader(int position);
    }
}
