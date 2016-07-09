package com.virtualsiamu.siamebookshop;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {


    //explicit แหล่งประกาศตัวแปร
    private EditText nameEditText, surnameEditText, userEditText, passEditText;
    private  String nameString, surnameString, userString, passwordString;
    private static final String urlPHP = "http://swiftcodingthai.com/9july/add_user_siam.php";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //การผูกตัวแปรกับ widget บน activity

        nameEditText = (EditText) findViewById(R.id.editText);
        surnameEditText = (EditText) findViewById(R.id.editText2);
        userEditText = (EditText) findViewById(R.id.editText3);
        passEditText = (EditText) findViewById(R.id.editText4);



    }// mainmethod

    public void clickSigUpSign(View view) {

        // Get value from edit text
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passEditText.getText().toString().trim();

        // การ เช็ค check space ต้องกรอกครบนะเว้ย ถ้าว่างเปล่าจะทำการโวยวาย ต้องสร้างตัวโวยวายก่อน myAlert
        // ที่เขียนทีเดียวสามารถนำมาใช้ได้ทุกหน้ากรอกผิด


        if (checkSpace()) {
            //true havespace กดaltenter จะเด้งไปข้างล่าง privateboolean
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง","กรุณากรอกทุกช่อง");


        } else {
            //fale no space
// ตั้งชื่อ
            updateNewUserToServer();


        } //มาจากตรงปุ่มโดเรมอน



    }//click Sign

    private void updateNewUserToServer() {

        //สร้างไลบาลรี่ติดตั้งก่อน ไปที่ไฟล์ สตักเจอร์ สร้างเสร็จมันจะrun libraryให้ จากนั้นไปลองrunดูว่ากรอกข้อมูลแล้ว
        // มันกลับไปหน้าแรก แล้วดูที่หน้าphpMyAdmin ก็จะปรากฎข้อมูลที่เรากรอกไปขึ้นมาแล้ว ถ้าไม่มา ให้เช็คไฟล์.php
        // หรือเช็ค .add
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                // มาจากไฟล์ add user php ที่วางในfilezilla
                .add("isAdd", "true")
                .add("Name", nameString)
                .add("Surname", surnameString)
                .add("User", userString)
                .add("Password", passwordString)
                .build();
        // เขวี้ยงขึ้นไป หรือทำการชี้เป้าไป
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        //วิธีการโยน
        Call call = okHttpClient.newCall(request);
        //ctrl ข้างล่างspace ตรง callback กด enter
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                finish();

            }
        });



    } // update

    private boolean checkSpace() {

        boolean status = false;

        //ถ้าไม่กรอกจะเป็นtrue แล้วใช้ไปป์
        status = nameString.equals("") || surnameString.equals("")
                || userString.equals("") || passwordString.equals("");
        //จากนั้นกลับไปที่if (checkSpace)) แล้วกรอกค่า MyAlertต่อไป พิมพ์ มีช่องว่าง บรา บรา บรา



        return status;

    }


}  //main class
