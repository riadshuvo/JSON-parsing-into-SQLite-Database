
package com.example.shuvo.json_parsing_into_sqlite.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("p_id")
    @Expose
    private Integer pId;
    @SerializedName("post_content")
    @Expose
    private String postContent;
    @SerializedName("post_title")
    @Expose
    private String postTitle;
    @SerializedName("post_link")
    @Expose
    private String postLink;
    @SerializedName("img_src")
    @Expose
    private String imgSrc;

    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostLink() {
        return postLink;
    }

    public void setPostLink(String postLink) {
        this.postLink = postLink;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

}
