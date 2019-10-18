package com.example.hg.uselibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hg.uselibrary.R;


/**
 * 左边图标右边文字
 */
public class RectView extends LinearLayout {
    private int mImageViewWidth = 40;
    private int mImageViewHeight = 40;
    private int mTextSize = 20;
    private int mTextColor = 0xffffffff;
    private int mTextViewMarginLeft = 40;
    private String mText;
    private int mLeftImageId;
    private int mBackgroundColor = 0xff3B78F5;
    private Drawable mBackground;
    private int mBackgroundRadius = 10;
    public RectView(Context context) {
        this(context,null);
    }

    public RectView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RectView);
        mTextColor = typedArray.getColor(R.styleable.RectView_rect_view_text_color, mTextColor);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.RectView_rect_view_text_size,mTextSize);
        mImageViewWidth = (int) typedArray.getDimension(R.styleable.RectView_rect_view_left_image_width,mImageViewWidth);
        mImageViewHeight = (int) typedArray.getDimension(R.styleable.RectView_rect_view_left_image_height,mImageViewHeight);
        mTextViewMarginLeft = (int)typedArray.getDimension(R.styleable.RectView_rect_view_interval_width,mTextViewMarginLeft);
        mText = typedArray.getString(R.styleable.RectView_rect_view_text);
        mLeftImageId = typedArray.getResourceId(R.styleable.RectView_rect_view_left_image, 0);

        mBackground = typedArray.getDrawable(R.styleable.RectView_rect_view_background);
        mBackgroundColor = typedArray.getColor(R.styleable.RectView_rect_view_background_color,mBackgroundColor);
        mBackgroundRadius = (int) typedArray.getDimension(R.styleable.RectView_rect_view_background_radius,mBackgroundRadius);

        typedArray.recycle();

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

        initView();

    }

    private void initView(){

        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(mLeftImageId);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(mImageViewWidth,mImageViewHeight));
        addView(imageView);

        TextView textView = new TextView(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(mTextViewMarginLeft, 0, 0, 0);
        textView.setText(mText);
        textView.setTextSize(mTextSize);
        textView.setTextColor(mTextColor);
        textView.setLayoutParams(params);
        addView(textView);

        if(mBackground!=null){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                setBackground(mBackground);
            }

        }else {

            // 外部矩形弧度
            float[] outerR = new float[]{mBackgroundRadius, mBackgroundRadius, mBackgroundRadius,
                    mBackgroundRadius, mBackgroundRadius, mBackgroundRadius, mBackgroundRadius, mBackgroundRadius};

            RoundRectShape rr = new RoundRectShape(outerR, null, null);

            ShapeDrawable drawable = new ShapeDrawable(rr);
            //指定填充颜色
            drawable.getPaint().setColor(mBackgroundColor);
            // 指定填充模式
            drawable.getPaint().setStyle(Paint.Style.FILL);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                setBackground(drawable);
            }
        }
    }
}
