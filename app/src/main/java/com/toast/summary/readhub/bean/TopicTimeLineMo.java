package com.toast.summary.readhub.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TopicTimeLineMo extends BaseMo implements Parcelable{
    public String id;
    public String title;
    public String createdAt;

    public TopicTimeLineMo(){}

    protected TopicTimeLineMo(Parcel in) {
        id = in.readString();
        title = in.readString();
        createdAt = in.readString();
    }

    public static final Creator<TopicTimeLineMo> CREATOR = new Creator<TopicTimeLineMo>() {
        @Override
        public TopicTimeLineMo createFromParcel(Parcel in) {
            return new TopicTimeLineMo(in);
        }

        @Override
        public TopicTimeLineMo[] newArray(int size) {
            return new TopicTimeLineMo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(createdAt);
    }
}
