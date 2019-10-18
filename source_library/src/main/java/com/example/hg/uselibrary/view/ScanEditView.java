package com.example.hg.uselibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.hg.uselibrary.R;

public class ScanEditView extends RelativeLayout {
    private int mRadius = 10;
    private int mImageWidth = 40;
    private int mImageHeight = 40;
    private int mImageId;
    private int mLineColor;
    private int mEditTextColor;
    private int mEditTextSize = 18;
    private int mLineWidth = 1;
    public ScanEditView(Context context) {
        this(context,null);
    }

    public ScanEditView(Context context,AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScanEditView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ScanEditView);

        mEditTextSize = typedArray.getDimensionPixelSize(R.styleable.ScanEditView_scan_edit_text_size,mEditTextSize);
        mEditTextColor = typedArray.getColor(R.styleable.ScanEditView_scan_edit_text_color,mLineColor);
        mRadius = (int) typedArray.getDimension(R.styleable.ScanEditView_scan_edit_radius,mRadius);
        mImageWidth = (int) typedArray.getDimension(R.styleable.ScanEditView_scan_edit_image_width,mImageWidth);
        mImageHeight = (int) typedArray.getDimension(R.styleable.ScanEditView_scan_edit_image_height,mImageHeight);
        mImageId = typedArray.getResourceId(R.styleable.ScanEditView_scan_edit_image,mImageId);
        mLineWidth = (int) typedArray.getDimension(R.styleable.ScanEditView_scan_edit_line_width,mLineWidth);
        mLineColor = typedArray.getColor(R.styleable.ScanEditView_scan_edit_line_color,mLineColor);

        typedArray.recycle();

        setPadding(1,1,1,1);

        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setLayoutParams(ll);

        linearLayout.setPadding(20,0,20,0);

        EditText editText = new EditText(context);
        if(mEditTextColor == 0){
            mEditTextColor = ContextCompat.getColor(context,R.color.black);
        }
        editText.setTextColor(mEditTextColor);
        editText.setTextSize(mEditTextSize);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams1.weight = 1;
        layoutParams1.rightMargin = 20;
        editText.setLayoutParams(layoutParams1);
        editText.setBackgroundColor(ContextCompat.getColor(context,R.color.white));

        linearLayout.addView(editText);

        //添加图片
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(mImageWidth,mImageHeight));
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if(mImageId > 0) {
            imageView.setImageResource(mImageId);
        }else {

            try {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.scan);
                imageView.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        linearLayout.addView(imageView);

        // 外部矩形弧度
        float[] outerR1 = new float[]{mRadius, mRadius, mRadius, mRadius,
                mRadius, mRadius, mRadius, mRadius};

        RoundRectShape rr1 = new RoundRectShape(outerR1,null,null);

        ShapeDrawable drawable1 = new ShapeDrawable(rr1);
        //指定填充颜色
        drawable1.getPaint().setColor(ContextCompat.getColor(context,R.color.white));

        // 指定填充模式
        drawable1.getPaint().setStyle(Paint.Style.FILL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            linearLayout.setBackground(drawable1);
        }

        addView(linearLayout);

        // 外部矩形弧度
        float[] outerR = new float[]{mRadius, mRadius, mRadius, mRadius,
                mRadius, mRadius, mRadius, mRadius};

        RoundRectShape rr = new RoundRectShape(outerR,null,null);

        ShapeDrawable drawable = new ShapeDrawable(rr);
        //指定填充颜色
        drawable.getPaint().setColor(mLineColor);
        drawable.getPaint().setStrokeWidth(mLineWidth);

        // 指定填充模式
        drawable.getPaint().setStyle(Paint.Style.STROKE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        }
    }
}
