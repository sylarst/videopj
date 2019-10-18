package com.example.hg.uselibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hg.uselibrary.R;


/**
 * 顶部搜索框
 */
public class TopSearchView extends LinearLayout {

    private String mEditText;
    private int mEditTextColor = 0xff000000;
    private int mEditTextSize = 18;
    private String mEditHint;
    private int mEditBackgroundRadius = 50;
    private int mEditBackgroundColor = 0xffDBDADA;
    private int mEditMargin = 15;

    private int mBackgroundRadius = 5;
    private int mBackgroundColor = 0xffffffff;

    private int mLeftImageWidth = 50;
    private int mLeftImageHeight = 50;
    private int mLeftImageId;

    private int mSearchImageWidth = 30;
    private int mSearchImageHeight = 30;
    private int mSearchImageId;

    private Drawable mBackground;
    private Drawable mEditBackground;

    public TopSearchView(Context context) {
        this(context,null);
    }

    public TopSearchView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TopSearchView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopSearchView);

        mSearchImageWidth = (int) typedArray.getDimension(R.styleable.TopSearchView_top_search_view_search_image_width,mSearchImageWidth);
        mSearchImageHeight = (int) typedArray.getDimension(R.styleable.TopSearchView_top_search_view_search_image_height,mSearchImageHeight);
        mSearchImageId = typedArray.getResourceId(R.styleable.TopSearchView_top_search_view_search_image, 0);

        mLeftImageWidth = (int) typedArray.getDimension(R.styleable.TopSearchView_top_search_view_left_image_width,mLeftImageWidth);
        mLeftImageHeight = (int) typedArray.getDimension(R.styleable.TopSearchView_top_search_view_left_image_height,mLeftImageHeight);
        mLeftImageId = typedArray.getResourceId(R.styleable.TopSearchView_top_search_view_left_image, 0);

        mEditHint = typedArray.getString(R.styleable.TopSearchView_top_search_view_edit_text_hint);
        mEditText = typedArray.getString(R.styleable.TopSearchView_top_search_view_edit_text);
        mEditTextColor = typedArray.getColor(R.styleable.TopSearchView_top_search_view_edit_text_color,mEditTextColor);
        mEditTextSize = typedArray.getDimensionPixelSize(R.styleable.TopSearchView_top_search_view_edit_text_size,mEditTextSize);

        mEditBackgroundColor = typedArray.getColor(R.styleable.TopSearchView_top_search_view_edit_background_color,mEditBackgroundColor);
        mEditBackgroundRadius = (int) typedArray.getDimension(R.styleable.TopSearchView_top_search_view_edit_background_radius,mEditBackgroundRadius);

        mEditMargin = (int)typedArray.getDimension(R.styleable.TopSearchView_top_search_view_edit_margin,mEditMargin);
        mBackgroundColor = typedArray.getColor(R.styleable.TopSearchView_top_search_view_background_color,mBackgroundColor);
        mBackgroundRadius = (int) typedArray.getDimension(R.styleable.TopSearchView_top_search_view_background_radius,mBackgroundRadius);

        mBackground = typedArray.getDrawable(R.styleable.TopSearchView_top_search_view_background);
        mEditBackground = typedArray.getDrawable(R.styleable.TopSearchView_top_search_view_edit_background);

        typedArray.recycle();

        setPadding(20,0,10,0);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);



        Log.d("TAG","mLeftImageId="+mLeftImageId);
        //添加 扫码图片
        ImageView scanImage = new ImageView(context);
        if(mLeftImageId > 0) {
            scanImage.setImageResource(mLeftImageId);
        }else {
            try {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.scan);
                scanImage.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        scanImage.setScaleType(ImageView.ScaleType.FIT_XY);
        scanImage.setLayoutParams(new ViewGroup.LayoutParams(mLeftImageWidth, mLeftImageHeight));
        addView(scanImage);

        LinearLayout editLL = new LinearLayout(context);
        editLL.setOrientation(LinearLayout.HORIZONTAL);
        editLL.setGravity(Gravity.CENTER);

        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        params.setMargins(mEditMargin,mEditMargin,mEditMargin,mEditMargin);
        editLL.setLayoutParams(params);

        //添加搜索图片
        ImageView searchImage = new ImageView(context);
        if(mSearchImageId > 0) {
            searchImage.setImageResource(mSearchImageId);
        }else {
            try {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.search);
                searchImage.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        searchImage.setScaleType(ImageView.ScaleType.FIT_XY);
        LayoutParams layoutParams = new LayoutParams(mSearchImageWidth, mSearchImageHeight);
        layoutParams.setMargins(20,0,0,0);
        searchImage.setLayoutParams(layoutParams);

        editLL.addView(searchImage);

        EditText editText = new EditText(context);
        editText.setTextColor(mEditTextColor);
        editText.setTextSize(mEditTextSize);
        if(TextUtils.isEmpty(mEditHint)){
            mEditHint = getResources().getString(R.string.normal_edit);
        }
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(mEditHint);
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15,true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        editText.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失

        LayoutParams editTextParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        editTextParams.setMargins(mEditMargin,0,30,0);
        editText.setPadding(0,2,0,0);
        editText.setLayoutParams(editTextParams);
        editText.setBackgroundColor(mEditBackgroundColor);


        editLL.addView(editText);

        if(mEditBackground != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                setBackground(mEditBackground);
            }
        }else{
            // 外部矩形弧度
            float[] outerR = new float[]{mEditBackgroundRadius, mEditBackgroundRadius, mEditBackgroundRadius,
                    mEditBackgroundRadius, mEditBackgroundRadius, mEditBackgroundRadius, mEditBackgroundRadius, mEditBackgroundRadius};

            RoundRectShape rr = new RoundRectShape(outerR, null, null);

            ShapeDrawable drawable = new ShapeDrawable(rr);
            //指定填充颜色
            drawable.getPaint().setColor(mEditBackgroundColor);
            // 指定填充模式
            drawable.getPaint().setStyle(Paint.Style.FILL);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                editLL.setBackground(drawable);
            }
        }

        addView(editLL);

        if(mBackground != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                setBackground(mBackground);
            }
        }else{
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
