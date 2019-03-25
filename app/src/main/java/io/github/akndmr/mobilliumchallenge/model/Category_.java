package io.github.akndmr.mobilliumchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by Akın DEMİR on 11.03.2019.
 */
public class Category_ {
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
    private Object parentCategory;
    @SerializedName("logo")
    @Expose
    private Logo logo;
    @SerializedName("cover")
    @Expose
    private Cover cover;
    @SerializedName("children")
    @Expose
    private List<Child> children = null;

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

    public Object getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Object parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("parentId", parentId).append("order", order).append("parentCategory", parentCategory).append("logo", logo).append("cover", cover).append("children", children).toString();
    }

}
