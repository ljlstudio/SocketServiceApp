package com.rayvison.socketserviceapp;

import android.os.Parcel;
import android.os.Parcelable;



public class ComplexData implements Parcelable {

    private float[][]handValue;
    public ComplexData(){}
    protected ComplexData(Parcel in) {
        handValue = new float[in.readInt()][in.readInt()];
        for (int i = 0; i < handValue.length; i++) {
            in.readFloatArray(handValue[i]);
        }
    }


    public void setData(float[][] leftHandValue) {
        this.handValue = leftHandValue;
    }

    public float[][] getData() {
        return handValue;
    }

    public static final Creator<ComplexData> CREATOR = new Creator<ComplexData>() {
        @Override
        public ComplexData createFromParcel(Parcel in) {
            return new ComplexData(in);
        }

        @Override
        public ComplexData[] newArray(int size) {
            return new ComplexData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(handValue.length);
        dest.writeInt(handValue[0].length);
        for (int i = 0; i < handValue.length; i++) {
            dest.writeFloatArray(handValue[i]);
        }


    }
}
