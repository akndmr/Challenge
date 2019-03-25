package io.github.akndmr.mobilliumchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Akın DEMİR on 11.03.2019.
 */
public class Logo implements Parcelable {
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("medium")
    @Expose
    private Medium medium;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("width", width).append("height", height).append("url", url).append("medium", medium).append("thumbnail", thumbnail).toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.width);
        dest.writeValue(this.height);
        dest.writeString(this.url);
        dest.writeParcelable(this.medium, flags);
        dest.writeParcelable(this.thumbnail, flags);
    }

    public Logo() {
    }

    protected Logo(Parcel in) {
        this.width = (Integer) in.readValue(Integer.class.getClassLoader());
        this.height = (Integer) in.readValue(Integer.class.getClassLoader());
        this.url = in.readString();
        this.medium = in.readParcelable(Medium.class.getClassLoader());
        this.thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
    }

    public static final Parcelable.Creator<Logo> CREATOR = new Parcelable.Creator<Logo>() {
        @Override
        public Logo createFromParcel(Parcel source) {
            return new Logo(source);
        }

        @Override
        public Logo[] newArray(int size) {
            return new Logo[size];
        }
    };
}
