package com.example.erge.resource;

import com.example.erge.resource.dbflow.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = MyDatabase.class)
public class MobUser extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String productid;

    @Column
    String image;

    @Column
    String name;

    @Column
    String price;

    @Column
    String desciption;

    @Column
    String rating;

    @Column
    String qty;

    @Column
    String stoke;

    public void InsertData( String productid, String image, String name, String price, String desciption, String rating, String qty, String stoke)
    {
        this.productid = productid;
        this.image = image;
        this.name = name;
        this.price = price;
        this.desciption = desciption;
        this.rating = rating;
        this.qty = qty;
        this.stoke = stoke;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStoke() {
        return stoke;
    }

    public void setStoke(String stoke) {
        this.stoke = stoke;
    }


}
