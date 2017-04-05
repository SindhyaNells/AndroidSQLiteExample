package com.example.sqlite.search.activity;

import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sqlite.search.R;
import com.example.sqlite.search.adapter.ProductsArrayAdapter;
import com.example.sqlite.search.data.Products;
import com.example.sqlite.search.util.DbConstants;
import com.example.sqlite.search.util.SQLDataController;

import java.util.ArrayList;
import java.util.List;

public class SearchItemActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editSearch;
    Button btnSearch;
    List<Products> productsList;
    ListView productsListView;
    private ProductsArrayAdapter productsArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

        editSearch=(EditText)findViewById(R.id.edit_search);
        btnSearch=(Button)findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);
        productsListView=(ListView)findViewById(R.id.products_list);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setTitle(getResources().getString(R.string.search_item_activity));
        }

        productsList=new ArrayList<>();
    }


    @Override
    public void onClick(View view) {
        int id=view.getId();

        if(id==R.id.btn_search){
            String search_val=editSearch.getText().toString();
            SQLDataController sqlDataController=new SQLDataController(getBaseContext());
            sqlDataController.open();
            Cursor cursor = sqlDataController.getData(search_val);

            if(cursor.moveToNext()){
                do {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.COLUMN_NAME));
                    String desc = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.COLUMN_DESC));
                    String price = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.COLUMN_PRICE));
                    String review = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.COLUMN_REVIEW));

                    Products products = new Products();
                    products.setProduct_name(name);
                    products.setProduct_desc(desc);
                    products.setProduct_price(price);
                    products.setProduct_review(review);

                    productsList.add(products);
                }while (cursor.moveToNext());
            }

            productsArrayAdapter=new ProductsArrayAdapter(SearchItemActivity.this,R.layout.activity_list_item,productsList);
            productsListView.setAdapter(productsArrayAdapter);

            sqlDataController.close();
        }
    }
}
