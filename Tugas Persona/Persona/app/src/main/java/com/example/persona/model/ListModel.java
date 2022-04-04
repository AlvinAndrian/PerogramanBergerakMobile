package com.example.persona.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ListModel implements Parcelable {
    private String name;
    private String tanggal;
    private String detail;
    private double latitude;
    private double longitude;

    public ListModel(Parcel in) {
        name = in.readString();
        tanggal = in.readString();
        detail = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<ListModel> CREATOR = new Creator<ListModel>() {
        @Override
        public ListModel createFromParcel(Parcel in) {
            return new ListModel(in);
        }

        @Override
        public ListModel[] newArray(int size) {
            return new ListModel[size];
        }
    };

    public ListModel(String s, String s1, String s2) {
        this.name = s;
        this.tanggal = s1;
        this.detail = s2;
    }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(tanggal);
        parcel.writeString(detail);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }
}
