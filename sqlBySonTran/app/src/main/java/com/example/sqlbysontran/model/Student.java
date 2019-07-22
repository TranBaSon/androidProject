package com.example.sqlbysontran.model;

public class Student {
    private int mId;
    private String mName;
    private String mPhone;
    private String mAddress;
    private String mEmail;

    public Student() {
    }

    public Student(String mName, String mPhone, String mAddress, String mEmail) {
        this.mName = mName;
        this.mPhone = mPhone;
        this.mAddress = mAddress;
        this.mEmail = mEmail;
    }

    public Student(int mId, String mName, String mPhone, String mAddress, String mEmail) {
        this.mId = mId;
        this.mName = mName;
        this.mPhone = mPhone;
        this.mAddress = mAddress;
        this.mEmail = mEmail;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
