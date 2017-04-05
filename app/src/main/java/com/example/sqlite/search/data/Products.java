package com.example.sqlite.search.data;

import android.database.Cursor;

import com.example.sqlite.search.util.DbConstants;

/**
 * Created by sindhya on 4/4/17.
 */
public class Products {

    public String product_name;
    public String product_desc;
    public String product_price;
    public String product_review;


    public Products(){

    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public void setProduct_review(String product_review) {
        this.product_review = product_review;
    }
}
