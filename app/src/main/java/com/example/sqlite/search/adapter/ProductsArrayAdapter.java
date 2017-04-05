package com.example.sqlite.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sqlite.search.R;
import com.example.sqlite.search.data.Products;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sindhya on 4/4/17.
 */
public class ProductsArrayAdapter extends ArrayAdapter<Products>{

    private List<Products> productsList=new ArrayList<>();
    private static LayoutInflater inflater=null;

    public ProductsArrayAdapter(Context context, int resource, List<Products> products_list) {
        super(context, resource,products_list);
        this.productsList=new ArrayList<>();
        productsList.addAll(products_list);
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class ViewHolder{
        TextView list_prod_name;
        TextView list_prod_desc;
        TextView list_prod_price;
        TextView list_prod_review;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.activity_list_item,null);
            holder=new ViewHolder();
            holder.list_prod_name=(TextView)convertView.findViewById(R.id.list_item_name);
            holder.list_prod_desc=(TextView)convertView.findViewById(R.id.list_item_desc);
            holder.list_prod_price=(TextView)convertView.findViewById(R.id.list_item_price);
            holder.list_prod_review=(TextView)convertView.findViewById(R.id.list_item_review);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }

        Products prodObj=productsList.get(position);
        holder.list_prod_name.setText(prodObj.product_name);
        holder.list_prod_desc.setText("Description: "+prodObj.product_desc);
        holder.list_prod_price.setText("Price: "+prodObj.product_price);
        holder.list_prod_review.setText("Review: "+prodObj.product_review);

        return convertView;
    }

}
