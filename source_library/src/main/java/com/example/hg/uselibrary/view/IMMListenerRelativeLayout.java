package com.example.hg.uselibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class IMMListenerRelativeLayout extends RelativeLayout {
    private InputWindowListener listener;

    public IMMListenerRelativeLayout(Context context) {
        super(context);
    }

    public IMMListenerRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IMMListenerRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (oldh > h) {
//            L.d("input window show");
            listener.show();
        } else{
//            L.d("input window hidden");
            listener.hidden();
        }
    }

    public void setListener(InputWindowListener listener) {
        this.listener = listener;
    }

}