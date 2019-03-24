package io.github.akndmr.mobilionchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Akın DEMİR on 11.03.2019.
 */
public class ParentCategory implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("parent_category")
    @Expose
    private ParentCategory parentCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public ParentCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(ParentCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("parentId", parentId).append("order", order).append("parentCategory", parentCategory).toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.parentId);
        dest.writeValue(this.order);
        dest.writeParcelable(this.parentCategory, flags);
    }

    public ParentCategory() {
    }

    protected ParentCategory(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.parentId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.order = (Integer) in.readValue(Integer.class.getClassLoader());
        this.parentCategory = in.readParcelable(Object.class.getClassLoader());
    }

    public static final Parcelable.Creator<ParentCategory> CREATOR = new Parcelable.Creator<ParentCategory>() {
        @Override
        public ParentCategory createFromParcel(Parcel source) {
            return new ParentCategory(source);
        }

        @Override
        public ParentCategory[] newArray(int size) {
            return new ParentCategory[size];
        }
    };
}
