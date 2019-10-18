package com.example.hg.uselibrary.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.age.mac.baselibrary.utils.SoftKeyBoardListener;
import com.example.hg.uselibrary.R;

@SuppressLint("AppCompatCustomView")
public class SourceEditText extends EditText{

    private boolean canEditPda=false;  //是否电表枪输入框
    private boolean isEditEnter=false; //是否输入了回车
    private String mBeforeContent="";

    private boolean inputChange=false;  //这次输入框弹开收起，扫码框有没有改变内容

    SourceEditeTextObserver mObserver;

    private int mIndex;

    public SourceEditText(Context context) {
        super(context);
    }

    public SourceEditText(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context,attrs);
    }

    public SourceEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public int getIndex(){
        return mIndex;
    }

    public void setIndex(int index){
        mIndex=index;
    }

    public void setTextChangeObserVer(SourceEditeTextObserver observer){
        mObserver=observer;
    }

    private void init(Context context,AttributeSet attrs){
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.SourceEditText);
        canEditPda = array.getBoolean(R.styleable.SourceEditText_can_edite_pda,false);
        array.recycle();
        setSingleLine();
        //---电表枪监听
        setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(keyEvent==null){
                    isEditEnter=true;
                }else {
                    isEditEnter = keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER;
                }
                if(canEditPda) {    //电表枪输入框
                    if(isEditEnter) {   //电表枪扫了回车
                        isEditEnter=false;
//                        if(mBeforeContent.equals("")){
//                            //------电表枪扫到了数据，内容改变
//                            if(mObserver!=null) {
//                                mObserver.textChange();
//                            }
//                            inputChange=false;
//                            return true;
//                        }else{
//                            setText(mBeforeContent);
//                        }

                        //------电表枪扫到了数据，内容改变
                            if(mObserver!=null) {
                                mObserver.textChange();
                            }
                            inputChange=false;
                    }
                }else{
                    if(isEditEnter&&keyEvent!=null) {   //电表枪扫了回车
                        isEditEnter=false;
                        setText(mBeforeContent);
                    }
                }
                return false;
            }
        });

        //--------文字改变的监听
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                inputChange=true;
                mBeforeContent=getText().toString().trim();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                editable.replace(0, originText.length(), mBeforeContent);
            }
        });

        SoftKeyBoardListener.setListener((Activity) context, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {

            }
            @Override
            public void keyBoardHide(int height) {
                if(inputChange) {   //输入框收起，输入内容改变
                    if(mObserver!=null) {
                        mObserver.textChange();
                    }
                }
                inputChange=false;
            }
        });
    }

    public interface SourceEditeTextObserver{
        void textChange();
    }
}
