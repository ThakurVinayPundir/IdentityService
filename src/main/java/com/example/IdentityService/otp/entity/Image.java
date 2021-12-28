package com.example.IdentityService.otp.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @Column(name = "userId")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "pic")
    private byte[] pic;

    public Image() {
    }

    public Image(Long userId, String name, String type, byte[] pic) {
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.pic = pic;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Image{" +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", pic=" + Arrays.toString(pic) +
                '}';
    }

}
