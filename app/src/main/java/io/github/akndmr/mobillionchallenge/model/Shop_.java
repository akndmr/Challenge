package io.github.akndmr.mobillionchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akın DEMİR on 11.03.2019.
 */
public class Shop_ implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("name_updateable")
    @Expose
    private Boolean nameUpdateable;
    @SerializedName("vacation_mode")
    @Expose
    private Integer vacationMode;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("shop_payment_id")
    @Expose
    private Integer shopPaymentId;
    @SerializedName("popular_products")
    @Expose
    private List<PopularProduct> popularProducts = null;
    @SerializedName("product_count")
    @Expose
    private Integer productCount;
    @SerializedName("shop_rate")
    @Expose
    private Integer shopRate;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("follower_count")
    @Expose
    private Integer followerCount;
    @SerializedName("is_editor_choice")
    @Expose
    private Boolean isEditorChoice;
    @SerializedName("is_following")
    @Expose
    private Boolean isFollowing;
    @SerializedName("cover")
    @Expose
    private Cover cover;
    @SerializedName("share_url")
    @Expose
    private String shareUrl;
    @SerializedName("logo")
    @Expose
    private Logo logo;

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Boolean getNameUpdateable() {
        return nameUpdateable;
    }

    public void setNameUpdateable(Boolean nameUpdateable) {
        this.nameUpdateable = nameUpdateable;
    }

    public Integer getVacationMode() {
        return vacationMode;
    }

    public void setVacationMode(Integer vacationMode) {
        this.vacationMode = vacationMode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getShopPaymentId() {
        return shopPaymentId;
    }

    public void setShopPaymentId(Integer shopPaymentId) {
        this.shopPaymentId = shopPaymentId;
    }

    public List<PopularProduct> getPopularProducts() {
        return popularProducts;
    }

    public void setPopularProducts(List<PopularProduct> popularProducts) {
        this.popularProducts = popularProducts;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getShopRate() {
        return shopRate;
    }

    public void setShopRate(Integer shopRate) {
        this.shopRate = shopRate;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Boolean getIsEditorChoice() {
        return isEditorChoice;
    }

    public void setIsEditorChoice(Boolean isEditorChoice) {
        this.isEditorChoice = isEditorChoice;
    }

    public Boolean getIsFollowing() {
        return isFollowing;
    }

    public void setIsFollowing(Boolean isFollowing) {
        this.isFollowing = isFollowing;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("slug", slug).append("definition", definition).append("nameUpdateable", nameUpdateable).append("vacationMode", vacationMode).append("createdAt", createdAt).append("shopPaymentId", shopPaymentId).append("popularProducts", popularProducts).append("productCount", productCount).append("shopRate", shopRate).append("commentCount", commentCount).append("followerCount", followerCount).append("isEditorChoice", isEditorChoice).append("isFollowing", isFollowing).append("cover", cover).append("shareUrl", shareUrl).append("logo", logo).toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.slug);
        dest.writeString(this.definition);
        dest.writeValue(this.nameUpdateable);
        dest.writeValue(this.vacationMode);
        dest.writeString(this.createdAt);
        dest.writeValue(this.shopPaymentId);
        dest.writeList(this.popularProducts);
        dest.writeValue(this.productCount);
        dest.writeValue(this.shopRate);
        dest.writeValue(this.commentCount);
        dest.writeValue(this.followerCount);
        dest.writeValue(this.isEditorChoice);
        dest.writeValue(this.isFollowing);
        dest.writeParcelable(this.cover, flags);
        dest.writeString(this.shareUrl);
        dest.writeParcelable(this.logo, flags);
    }

    public Shop_() {
    }

    protected Shop_(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.slug = in.readString();
        this.definition = in.readString();
        this.nameUpdateable = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.vacationMode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.createdAt = in.readString();
        this.shopPaymentId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.popularProducts = new ArrayList<PopularProduct>();
        in.readList(this.popularProducts, PopularProduct.class.getClassLoader());
        this.productCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.shopRate = (Integer) in.readValue(Integer.class.getClassLoader());
        this.commentCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.followerCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isEditorChoice = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isFollowing = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.cover = in.readParcelable(Cover.class.getClassLoader());
        this.shareUrl = in.readString();
        this.logo = in.readParcelable(Logo.class.getClassLoader());
    }

    public static final Parcelable.Creator<Shop_> CREATOR = new Parcelable.Creator<Shop_>() {
        @Override
        public Shop_ createFromParcel(Parcel source) {
            return new Shop_(source);
        }

        @Override
        public Shop_[] newArray(int size) {
            return new Shop_[size];
        }
    };
}
