package com.example.sqlite.search.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.search.R;
import com.example.sqlite.search.util.DbConstants;
import com.example.sqlite.search.util.SQLDataController;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editItemName;
    EditText editItemDesc;
    EditText editItemPrice;
    EditText editItemReview;

    Button btnAdd, btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setTitle(getResources().getString(R.string.new_item_activity));
        }

        editItemName = (EditText) findViewById(R.id.edit_item_name);
        editItemDesc = (EditText) findViewById(R.id.edit_item_desc);
        editItemPrice = (EditText) findViewById(R.id.edit_item_price);
        editItemReview = (EditText) findViewById(R.id.edit_item_review);

        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_add) {
            String item_name = editItemName.getText().toString();
            String item_desc = editItemDesc.getText().toString();
            String item_price = editItemPrice.getText().toString();
            String item_review = editItemReview.getText().toString();

            SQLDataController sqlDataController=new SQLDataController(getBaseContext());
            sqlDataController.open();

            long ins_value= sqlDataController.insert(item_name,item_desc,item_price,item_review);

            sqlDataController.close();

            if(ins_value!=-1){
                //saved successfully
                Toast.makeText(getBaseContext(),getResources().getString(R.string.add_msg),Toast.LENGTH_LONG);

            }
        }
    }

}
