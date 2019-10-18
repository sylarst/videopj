package com.example.hg.uselibrary.view;

import java.util.ArrayList;
import java.util.List;

public class SourceEditeControler {

    private int mLength=0;

    private SourceEditeControler(){
        list=new ArrayList<>();
    }

    public static SourceEditeControler getInstance(){
        return controler;
    }

    private static SourceEditeControler controler=new SourceEditeControler();

    private List<SourceEditText> list;

    public void initView(SourceEditText editText){
        list.add(editText);
        editText.setIndex(mLength);
        mLength++;
    }

    public void changeNext(SourceEditText editText){
        int index=editText.getIndex();
        for(int i=0;i<list.size();i++){
            SourceEditText editText1=list.get(i);
            if(editText1.getIndex()==index){
                int tar=i+1;
                if(tar<list.size()){
                    SourceEditText target=list.get(tar);
                    target.setFocusable(true);
                }
                break;
            }
        }
    }
}
