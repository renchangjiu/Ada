package com.su.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"value"})    // 转json 时忽略value 属性
public class Captcha {

    private String id;              // 唯一id

    private String imageBase64;     // 图片的base64值

    private String value;           // 验证码的字符的真实值


    public Captcha(String id, String imageBase64, String value) {
        this.id = id;
        this.imageBase64 = imageBase64;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Captcha{" +
                "id='" + id + '\'' +
                ", imageBase64='" + imageBase64 + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
