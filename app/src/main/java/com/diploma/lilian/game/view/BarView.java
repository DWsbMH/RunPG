package com.diploma.lilian.game.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.diploma.lilian.runpg.R;

/**
 * TODO: document your custom view class.
 */
public class BarView extends View {
    private int mExampleColor = Color.GREEN; // TODO: use a default from R.color...
    private float mExampleDimension = 24; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    Paint remainPointPaint;
    Paint backgroundPaint;
    private int maxPoint = 100;
    private float actualPoint = 100;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    public BarView(Context context) {
        super(context);
        init(null, 0);
    }

    public BarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.BarView, defStyle, 0);

        mExampleColor = a.getColor(
                R.styleable.BarView_exampleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.BarView_exampleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.BarView_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.BarView_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        remainPointPaint = new Paint();
        remainPointPaint.setColor(getExampleColor());

        backgroundPaint = new Paint();
        backgroundPaint.setARGB(255,204,204,204);

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        canvas.drawRect(paddingLeft, paddingTop, contentWidth, contentHeight, backgroundPaint);
        canvas.drawRect(paddingLeft, paddingTop, (actualPoint / maxPoint)*contentWidth, contentHeight, remainPointPaint);


        // Draw the text.
        canvas.drawText((int)actualPoint + " / " + maxPoint,
                (canvas.getWidth()) / 2,
                (int) ((canvas.getHeight() / 2) - ((mTextPaint.descent() + mTextPaint.ascent()) / 2)),
                mTextPaint);

    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public void setActualPoint(float actualPoint) {
        this.actualPoint = actualPoint;
        invalidate();
    }

    public float getActualPoint() {
        return actualPoint;
    }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }

    public void initPoints(int point){
        maxPoint = point;
        actualPoint = point;
    }

}
