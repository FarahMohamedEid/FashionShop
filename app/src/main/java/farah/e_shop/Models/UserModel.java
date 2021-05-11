package farah.e_shop.Models;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String name;
    private String email;
    private String uid;
    private String img;

    public UserModel(String name, String email, String uid, String img) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.img = img;
    }

    public UserModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
