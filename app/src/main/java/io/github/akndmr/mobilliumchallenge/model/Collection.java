package io.github.akndmr.mobilliumchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Akın DEMİR on 11.03.2019.
 */
public class Collection implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("share_url")
    @Expose
    private String shareUrl;
    @SerializedName("cover")
    @Expose
    private Cover cover;
    @SerializedName("logo")
    @Expose
    private Logo logo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("title", title).append("definition", definition).append("start", start).append("end", end).append("shareUrl", shareUrl).append("cover", cover).append("logo", logo).toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.definition);
        dest.writeString(this.start);
        dest.writeString(this.end);
        dest.writeString(this.shareUrl);
        dest.writeParcelable(this.cover, flags);
        dest.writeParcelable(this.logo, flags);
    }

    public Collection() {
    }

    protected Collection(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.definition = in.readString();
        this.start = in.readString();
        this.end = in.readString();
        this.shareUrl = in.readString();
        this.cover = in.readParcelable(Cover.class.getClassLoader());
        this.logo = in.readParcelable(Logo.class.getClassLoader());
    }

    public static final Parcelable.Creator<Collection> CREATOR = new Parcelable.Creator<Collection>() {
        @Override
        public Collection createFromParcel(Parcel source) {
            return new Collection(source);
        }

        @Override
        public Collection[] newArray(int size) {
            return new Collection[size];
        }
    };
}
