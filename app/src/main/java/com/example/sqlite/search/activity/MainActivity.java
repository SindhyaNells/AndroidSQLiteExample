package com.example.sqlite.search.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sqlite.search.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnNewItem;
    Button btnSearchItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher);
            actionBar.setDisplayUseLogoEnabled(true);
        }

        btnNewItem=(Button)findViewById(R.id.btn_new_item);
        btnNewItem.setOnClickListener(this);
        btnSearchItem=(Button)findViewById(R.id.btn_search_item);
        btnSearchItem.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==R.id.btn_new_item){
            Intent intent=new Intent(MainActivity.this,AddItemActivity.class);
            startActivity(intent);

        }else if(id==R.id.btn_search_item){
            Intent intent=new Intent(MainActivity.this,SearchItemActivity.class);
            startActivity(intent);
        }
    }
}
