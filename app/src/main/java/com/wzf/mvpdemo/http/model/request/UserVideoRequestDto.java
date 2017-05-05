package com.wzf.mvpdemo.http.model.request;

/**
 * @author: wangzhenfei
 * Description:
 * Date: 2017-04-27 17:31-31
 * Huan Yu Copyright (c) 2016 All Rights Reserved.
 */

public class UserVideoRequestDto extends BaseParamDto {
    private long id;
    private String videoPath;
    private String videoDiscription;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoDiscription() {
        return videoDiscription;
    }

    public void setVideoDiscription(String videoDiscription) {
        this.videoDiscription = videoDiscription;
    }

    @Override
    public String toString() {
        return "UploadVideoRequestDto{" +
                "videoPath='" + videoPath + '\'' +
                ", videoDiscription='" + videoDiscription + '\'' +
                '}';
    }
}
