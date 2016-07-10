package com.virtualsiamu.siamebookshop;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    //ex
    private TextView bookTextView, priceTextView;
    private ImageView imageView;
    private String namLoginString, surnamLoginString,
    bookString, priceString, iconString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //bindwidget
        bookTextView = (TextView) findViewById(R.id.textView10);

        priceTextView = (TextView) findViewById(R.id.textView11);
        imageView = (ImageView) findViewById(R.id.imageView3);

        namLoginString = getIntent().getStringExtra("NameLogin");
        surnamLoginString = getIntent().getStringExtra("SurnamLogin");
        bookString = getIntent().getStringExtra("Book");
        priceString = getIntent().getStringExtra("Price");
        iconString = getIntent().getStringExtra("Icon");

        bookTextView.setText(bookString);
        priceTextView.setText(priceString + "THB");
        Picasso.with(this).load(iconString).resize(250, 300).into(imageView);








    }//main method

    public void clickBack(View view) {
        finish();
    }

    public void clickOrdr(View view) {

    }// click order



}//mainclass
