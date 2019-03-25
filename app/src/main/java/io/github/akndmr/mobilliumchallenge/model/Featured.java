package io.github.akndmr.mobilliumchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Akın DEMİR on 11.03.2019.
 */
public class Featured {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("cover")
    @Expose
    private Cover cover;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("sub_title")
    @Expose
    private String subTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("type", type).append("cover", cover).append("title", title).append("subTitle", subTitle).toString();
    }
}
