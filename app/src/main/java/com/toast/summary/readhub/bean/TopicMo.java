package com.toast.summary.readhub.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TopicMo extends BaseMo implements Parcelable {

    public long id;
    public String title;
    public String summary;
    public String summaryAuto;
    public String url;
    public String mobileUrl;
    public String siteName;
    public String language;
    public String authorName;
    public String publishDate;

    public TopicMo(){}

    protected TopicMo(Parcel in) {
        id = in.readLong();
        title = in.readString();
        summary = in.readString();
        summaryAuto = in.readString();
        url = in.readString();
        mobileUrl = in.readString();
        siteName = in.readString();
        language = in.readString();
        authorName = in.readString();
        publishDate = in.readString();
    }

    public static final Creator<TopicMo> CREATOR = new Creator<TopicMo>() {
        @Override
        public TopicMo createFromParcel(Parcel in) {
            return new TopicMo(in);
        }

        @Override
        public TopicMo[] newArray(int size) {
            return new TopicMo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(summary);
        dest.writeString(summaryAuto);
        dest.writeString(url);
        dest.writeString(mobileUrl);
        dest.writeString(siteName);
        dest.writeString(language);
        dest.writeString(authorName);
        dest.writeString(publishDate);
    }
}
