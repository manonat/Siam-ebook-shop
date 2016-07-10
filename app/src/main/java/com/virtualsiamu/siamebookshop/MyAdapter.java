package com.virtualsiamu.siamebookshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Admin on 7/10/16.
 */
public class MyAdapter extends BaseAdapter{

    //expilicit
    private Context context;
    private String[] bookStrings, priceStrings, iconStrings;


    public MyAdapter(Context context,
                     String[] bookStrings,
                     String[] priceStrings,
                     String[] iconStrings) {
        this.context = context;
        this.bookStrings = bookStrings;
        this.priceStrings = priceStrings;
        this.iconStrings = iconStrings;
    }

    @Override
    public int getCount() {// นับหนังสือกี่เล่มให้มาห้าก็นับห้า
        return bookStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.my_listview, parent, false);

        // bind widget
        TextView bookTextView = (TextView) view.findViewById(R.id.textView8);
        TextView priceTextView = (TextView) view.findViewById(R.id.textView9);
        ImageView iconImageView = (ImageView) view.findViewById(R.id.imageView2);

        bookTextView.setText(bookStrings[position]);
        priceTextView.setText(priceStrings[position]);
        Picasso.with(context).load(iconStrings[position]).resize(150, 180).into(iconImageView);


        return view;
    }


}   //Main Class
