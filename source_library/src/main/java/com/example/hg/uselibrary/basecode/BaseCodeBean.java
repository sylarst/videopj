package com.example.hg.uselibrary.basecode;

import java.io.Serializable;

public class BaseCodeBean implements Serializable {
    private String codeTypeId;
    private int codeId;
    private String codeInfoValue;
    private String codeTypeValue;
    private String codeInfoId;

    public String getCodeTypeId() {
        return codeTypeId;
    }

    public void setCodeTypeId(String codeTypeId) {
        this.codeTypeId = codeTypeId;
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public String getCodeInfoValue() {
        return codeInfoValue;
    }

    public void setCodeInfoValue(String codeInfoValue) {
        this.codeInfoValue = codeInfoValue;
    }

    public String getCodeTypeValue() {
        return codeTypeValue;
    }

    public void setCodeTypeValue(String codeTypeValue) {
        this.codeTypeValue = codeTypeValue;
    }

    public String getCodeInfoId() {
        return codeInfoId;
    }

    public void setCodeInfoId(String codeInfoId) {
        this.codeInfoId = codeInfoId;
    }
}
