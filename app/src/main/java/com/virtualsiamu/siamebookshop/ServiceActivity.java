package com.virtualsiamu.siamebookshop;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class ServiceActivity extends AppCompatActivity {
    //explicit
    private TextView textView;
    private ListView listView;
    private  String nameString, surnameString, urlJSON;
    //private static final String urlJSON = "http://swiftcodingthai.com/9july/get_product_master.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //setup constant
        MyConstant myConstant = new MyConstant();
        urlJSON = myConstant.getUrlJSONproduct();

        // initial widget
        textView = (TextView) findViewById(R.id.textView7);
        listView = (ListView) findViewById(R.id.listView);

        //show view
        nameString = getIntent().getStringExtra("Name");
        surnameString = getIntent().getStringExtra("Surname");
        textView.setText("Welcome" + nameString + " " + surnameString);

        // syn and create listview
        SynProduct synProduct = new SynProduct(this, urlJSON, listView);
        synProduct.execute();

    } //Main Method


    private class SynProduct extends AsyncTask<Void, Void, String> {
        //explict
        private Context context;
        private String myURL;
        private ListView myListView;

        public SynProduct(Context context,
                          String myURL,
                          ListView myListView) {
            this.context = context;
            this.myURL = myURL;
            this.myListView = myListView;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(myURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                Log.d("ShopV2", "e doInBack ==>");
                return null;
            }
        }//doinback

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("ShopV2", "JSON ==> " + s);



        }//onpost สร้างลิสทั้งหมด ลิสวิว




    } //Synproduct class




} //Main class
