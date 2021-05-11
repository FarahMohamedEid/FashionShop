package farah.e_shop.Models;

import java.io.Serializable;

public class Categories implements Serializable {
    private int BackGround;
    private String CategoryName;

    public Categories() {
    }

    public Categories(int backGround, String categoryName) {
        BackGround = backGround;
        CategoryName = categoryName;
    }

    public int getBackGround() {
        return BackGround;
    }

    public void setBackGround(int backGround) {
        BackGround = backGround;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
