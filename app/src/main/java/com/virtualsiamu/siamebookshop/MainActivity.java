package com.virtualsiamu.siamebookshop;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    //explicit
    private EditText usereditText, passwordEditText;
    private String userString, passwordString;
    //gเอาข้อมูลมาแสดง
    private static final String urlJSON = "http://swiftcodingthai.com/9july/get_user_siam.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bindwidget
        usereditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);


    }// main method

    // ทำคลาสซ้อนคลาส connectกับฐานข้อมูล ดึงdataอย่างเดียว บางคอลัมน์ก็ได้ ติดต่อเจสันด้วยวิธีนี้วิธีเดียว
    //สิ่งแรกvoid ก่อนโหลดไม่ต้องทำไร โหลดได้String กดที่เส้นแดงนะ alt enter im...ได้ doinbackground ข้างล่าง
    //
    private class SynUserTABLE extends AsyncTask<Void, Void, String> {

        //explicit  สร้าง construct geter คลาสซ้อนคลาส ทำท่อพ่น url
        private Context context;
        private String myURL, myUserString, myPasswordString, truePassword,
                loginNameString, loginSurnameString;
        private boolean statusABoolean = true;
        //  จินตานาการว่าเอาเลข ห้า ใส่กล่องกระดาษ เอามือจับในกล่องอะไรออกมา นี่คือ โพเสสเกตเตอร์
        // กด comman n


        public SynUserTABLE(Context context, String myURL, String myUserString, String myPasswordString) {
            this.context = context;
            this.myURL = myURL;
            this.myUserString = myUserString;
            this.myPasswordString = myPasswordString;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                // net error exception e ยอ่มรับได้
                OkHttpClient okHttpClient = new OkHttpClient();
                //ต่อไปสร้างโหลดเจสันจากurlไหน
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(myURL).build();

                // ต่อไปคือสิ่งที่มองเห็นจากบอดี้website response
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                //  ต่อเนทไม่ได้ข้างล่างนะให้โวยวาย
                Log.d("ShopV1", "e doInBack ==> " + e.toString());
                return null;
            }



        } // doInBack ทำงานแรกทำการoverride  ข้างล่างนี้ ที่ว่าง กด command n เลือก onPostEx....จะได้ข้างล่าง
        //เรียนซุปเปอร์แมนออกมาแล้วนะ

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("ShopV1", "JSON ==>" + s);// จากนั้นไปdebugดวยการกด command 6  ข้างล่างเลือกDebug
            //ตรงแว่นค้นหา พิมพ์ ShopV1 แล้วไปรันดูกรอกข้อมูลsign in เข้าไป เราจะเห็นlogcatล่างมันมีการทำงาน โดย
            //ดึงข้อมูลจากเว็บมาแสดง ถ้าได้ก็คือผ่าน
            try {

                JSONArray jsonArray = new JSONArray(s);
                for (int i=0;i<jsonArray.length();i +=1) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (myUserString.equals(jsonObject.getString("User"))) {
                        statusABoolean = false;
                        truePassword = jsonObject.getString("Password");
                        loginNameString = jsonObject.getString("Name");
                        loginSurnameString = jsonObject.getString("Surname");



                    }


                } // for

                if (statusABoolean) {
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context, "ไม่มี user นี้",
                            "ไม่มี" + myUserString + "ในฐานข้อมูลของเรา");
                } else if (myPasswordString.equals(truePassword)) {
                    Toast.makeText(context, "Welcome" + loginNameString +" " + loginSurnameString,
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                    intent.putExtra("Name", loginNameString);
                    intent.putExtra("Surname", loginSurnameString);
                    startActivity(intent);
                    finish();


                } else {
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context, "Password False",
                            "Please Try Again Password False");

                }




            } catch (Exception e) {
                Log.d("ShopV1", "e onPost ==> " + e.toString());// ทำการดึงค่าเจสันstrin 1 ตัวให้เป็ฯdata


            }

        } // onpost

    }// SynUser Class


    public void clickSigIn(View view) {

        userString = usereditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //check space
        if (userString.equals("") || passwordString.equals("")) {
            //have space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "Have space", "please fill all ever blank กรุณากรอกให้ครบ");


        } else {
            // no space

            //ปุ่มกดsignกดแล้วไปไหน ข้างล่างนี้คือตัวบ่งชี้ให้ไปเรียกหน้า
            SynUserTABLE synUserTABLE = new SynUserTABLE(this, urlJSON, userString, passwordString);
            synUserTABLE.execute();


        }




    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

} // main class
