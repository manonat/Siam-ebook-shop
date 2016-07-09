package com.virtualsiamu.siamebookshop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Admin on 7/9/16.
 */
public class MyAlert {

    public void myDialog(Context context,
                         String strTitle,
                         String strMessage) {
        //stringข้างบนจะกำหนดค่าเพิ่มเติมอีกก็ได้ ship command enterต่อไปก็สร้างการสืบทอด
        // กฎการสืบทอต้องสร้างconstant หรือ  object
        //  class object = new Class ();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //ถ้าไม่กด ok
        builder.setCancelable(false);

        //  เพิ่มสนุกโดเรมนอม
        builder.setIcon(R.drawable.doremon48);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        // เพิ่มปุ่มคลิกตกลงคำว่าok ส่วนตรงnewข้างล่างกด ctrl+space จะแตก.. ออกมานะ อย่าลืมทำทุกครั้งที่เจอ new
        builder.setPositiveButton("OKตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // คลิกให้หายไปเมื่อกดok dialog บางครั้งอาจเป็นอีกคำ
                dialog.dismiss();

            }
        });
        //มาตรงนี้จาก dialog.dismiss
        builder.show();






    }

} // main classปปปป public ใช้นอกได้void ไม่รีเทิรนค่ากลับ ปปปcontext คือการส่งclassกับclass ต่อท่อหากัน
