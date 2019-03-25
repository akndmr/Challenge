package io.github.akndmr.mobilliumchallenge.model;

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
public class Product implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("old_price")
    @Expose
    private Integer oldPrice;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("stock")
    @Expose
    private Integer stock;
    @SerializedName("max_installment")
    @Expose
    private Integer maxInstallment;
    @SerializedName("commission_rate")
    @Expose
    private Integer commissionRate;
    @SerializedName("cargo_time")
    @Expose
    private Integer cargoTime;
    @SerializedName("is_cargo_free")
    @Expose
    private Boolean isCargoFree;
    @SerializedName("is_new")
    @Expose
    private Boolean isNew;
    @SerializedName("reject_reason")
    @Expose
    private String rejectReason;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("difference")
    @Expose
    private String difference;
    @SerializedName("is_editor_choice")
    @Expose
    private Boolean isEditorChoice;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("is_owner")
    @Expose
    private Boolean isOwner;
    @SerializedName("is_approved")
    @Expose
    private Boolean isApproved;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;
    @SerializedName("share_url")
    @Expose
    private String shareUrl;
    @SerializedName("is_liked")
    @Expose
    private Boolean isLiked;
    @SerializedName("like_count")
    @Expose
    private Integer likeCount;
    @SerializedName("shop")
    @Expose
    private Shop shop;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Integer oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getMaxInstallment() {
        return maxInstallment;
    }

    public void setMaxInstallment(Integer maxInstallment) {
        this.maxInstallment = maxInstallment;
    }

    public Integer getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(Integer commissionRate) {
        this.commissionRate = commissionRate;
    }

    public Integer getCargoTime() {
        return cargoTime;
    }

    public void setCargoTime(Integer cargoTime) {
        this.cargoTime = cargoTime;
    }

    public Boolean getIsCargoFree() {
        return isCargoFree;
    }

    public void setIsCargoFree(Boolean isCargoFree) {
        this.isCargoFree = isCargoFree;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public Boolean getIsEditorChoice() {
        return isEditorChoice;
    }

    public void setIsEditorChoice(Boolean isEditorChoice) {
        this.isEditorChoice = isEditorChoice;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Boolean getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(Boolean isOwner) {
        this.isOwner = isOwner;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("code", code).append("title", title).append("slug", slug).append("definition", definition).append("oldPrice", oldPrice).append("price", price).append("stock", stock).append("maxInstallment", maxInstallment).append("commissionRate", commissionRate).append("cargoTime", cargoTime).append("isCargoFree", isCargoFree).append("isNew", isNew).append("rejectReason", rejectReason).append("categoryId", categoryId).append("difference", difference).append("isEditorChoice", isEditorChoice).append("commentCount", commentCount).append("isOwner", isOwner).append("isApproved", isApproved).append("isActive", isActive).append("shareUrl", shareUrl).append("isLiked", isLiked).append("likeCount", likeCount).append("shop", shop).append("category", category).append("images", images).toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.code);
        dest.writeString(this.title);
        dest.writeString(this.slug);
        dest.writeString(this.definition);
        dest.writeValue(this.oldPrice);
        dest.writeValue(this.price);
        dest.writeValue(this.stock);
        dest.writeValue(this.maxInstallment);
        dest.writeValue(this.commissionRate);
        dest.writeValue(this.cargoTime);
        dest.writeValue(this.isCargoFree);
        dest.writeValue(this.isNew);
        dest.writeString(this.rejectReason);
        dest.writeValue(this.categoryId);
        dest.writeString(this.difference);
        dest.writeValue(this.isEditorChoice);
        dest.writeValue(this.commentCount);
        dest.writeValue(this.isOwner);
        dest.writeValue(this.isApproved);
        dest.writeValue(this.isActive);
        dest.writeString(this.shareUrl);
        dest.writeValue(this.isLiked);
        dest.writeValue(this.likeCount);
        dest.writeParcelable(this.shop, flags);
        dest.writeParcelable(this.category, flags);
        dest.writeList(this.images);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.code = in.readParcelable(Object.class.getClassLoader());
        this.title = in.readString();
        this.slug = in.readString();
        this.definition = in.readString();
        this.oldPrice = (Integer) in.readValue(Integer.class.getClassLoader());
        this.price = (Integer) in.readValue(Integer.class.getClassLoader());
        this.stock = (Integer) in.readValue(Integer.class.getClassLoader());
        this.maxInstallment = (Integer) in.readValue(Integer.class.getClassLoader());
        this.commissionRate = (Integer) in.readValue(Integer.class.getClassLoader());
        this.cargoTime = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isCargoFree = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isNew = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.rejectReason = in.readString();
        this.categoryId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.difference = in.readString();
        this.isEditorChoice = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.commentCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isOwner = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isApproved = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isActive = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.shareUrl = in.readString();
        this.isLiked = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.likeCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.shop = in.readParcelable(Shop.class.getClassLoader());
        this.category = in.readParcelable(Category.class.getClassLoader());
        this.images = new ArrayList<Image>();
        in.readList(this.images, Image.class.getClassLoader());
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
