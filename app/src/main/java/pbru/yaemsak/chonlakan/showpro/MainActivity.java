package pbru.yaemsak.chonlakan.showpro;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.http.HttpResponseCache;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private ManagTABLE objManagTABLE;
    private EditText userEditText, passwordEditText;
    private  String userString, passwordString;
    private TextView mTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bidwidget();
        setWidgetEventListener();
        //connected database
        objManagTABLE = new ManagTABLE(this);

        //Test add value
        //testAddValue();

        //delete all SQLite
        deleteAllSQLite();

        //Synchronize Json to SQLise
        synJSONtoSQLite();

    }//main method

    public void clickLoin(View view) {


        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        if (userString.equals("") || passwordString.equals("")) {
            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(MainActivity.this,
                    R.drawable.q2,"มีช่องว่างครับ","กรุณากรอกให้ครบทุกช่องครับ");
        } else {
            // NO Space
            checkUser();

        }

    }//click login

    public void clickGuest(View view) {
        startActivity(new Intent(MainActivity.this, MenuActivity.class));
    }

    private void checkUser() {
        try {

            String[] myResultString = objManagTABLE.searchUser(userString);
            Log.d("ShowPro", "Welcome ==>" + myResultString[3]);

            //check password
            if (passwordString.equals(myResultString[2])) {
                //password True
                Intent objIntent = new Intent(MainActivity.this, ServiceActivity.class);
                objIntent.putExtra("Result", myResultString);
                startActivity(objIntent);


            } else {
                //password False
                MyAlertDialog objMyAlertDialog = new MyAlertDialog();
                objMyAlertDialog.myDialog(MainActivity.this,
                       R.drawable.q2,"Password False","กรุณาลองใหม่อีกครั้งครับ");
            }


        } catch (Exception e) {
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(MainActivity.this,
                    R.drawable.ch2,"ไม่พบ User", "ไม่มี " + userString + " ในฐานข้อมูล");
        }


    }// Check User


    private void bidwidget() {
        mTextView = (TextView) findViewById(R.id.register_link);
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText)  findViewById(R.id.editText2);

    }

    private void synJSONtoSQLite() {

        StrictMode.ThreadPolicy myThreadPolicy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();                  //ปลดล็อค permission ให้เข้าถึงได้ทุก โปรโตคอล
        StrictMode.setThreadPolicy(myThreadPolicy);

        int intTimes = 1;
        while (intTimes <= 3) {//รอบที่วน

            //มี3ขั้นตอน
            //1.Create InputStream
            InputStream objInputStream = null;
            String strURLuser = "http://swiftcodingthai.com/mac/php_get_data_max.php";
            String strURLpromote = "http://swiftcodingthai.com/mac/php_get_promot_max.php";
            String strURLreward = "http://swiftcodingthai.com/mac/php_get_reward_max.php";
       //     String strURLplace = "http://swiftcodingthai.com/mac/php_get_place.php";
            HttpPost objHttpPost = null;
            String tag = "ShowPro";

            try {

                HttpClient objHttpClient = new DefaultHttpClient();
                switch (intTimes) {
                    case 1:
                        objHttpPost = new HttpPost(strURLuser);
                        break;
                    case 2:
                        objHttpPost = new HttpPost(strURLpromote);
                        break;
                    case 3:
                        objHttpPost = new HttpPost(strURLreward);
                        break;
                }//Switch

                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();

            } catch (Exception e) {
                Log.d(tag, "InputStream ==>" + e.toString());
            }

            //2.create JSON Sting
            String strJSON = null;

            try {
                BufferedReader objBufferedReader = new BufferedReader(
                        new InputStreamReader(objInputStream, "UTF-8")
                );
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;

                while ((strLine = objBufferedReader.readLine()) != null) {
                    objStringBuilder.append(strLine);
                }   //while
                objInputStream.close();
                strJSON = objStringBuilder.toString();

            } catch (Exception e) {
                Log.d(tag, "JSON ==> " + e.toString());
            }

            //3.Update SQLite
            try {

                JSONArray objJsonArray = new JSONArray(strJSON);
                for (int i = 0; i < objJsonArray.length(); i++) {
                    JSONObject jsonObject = objJsonArray.getJSONObject(i);
                switch (intTimes) {

                    case 1:
                        // For userTABLE
                            String strUser = jsonObject.getString(ManagTABLE.COLUMN_User);
                            String strPassword = jsonObject.getString(ManagTABLE.COLUMN_Password);
                            String strName = jsonObject.getString(ManagTABLE.COLUMN_Name);
                            String strSurname = jsonObject.getString(ManagTABLE.COLUMN_Surname);
                            String strAddress = jsonObject.getString(ManagTABLE.COLUMN_Address);
                            String strEmail = jsonObject.getString(ManagTABLE.COLUMN_Email);
                            String strPoint = jsonObject.getString(ManagTABLE.COLUMN_Point);
                            objManagTABLE.addNewValueToUser(strUser, strPassword, strName,
                                    strSurname, strAddress, strEmail,strPoint);
                        break;

                    case 2:
                        //For promotionTABLE
                            String strPromotion = jsonObject.getString(ManagTABLE.COLUMN_NamePromotion);
                            String strCondition = jsonObject.getString(ManagTABLE.COLUMN_Condition);
                            String strPictPromotion = jsonObject.getString(ManagTABLE.COLUMN_PictPromotion);
                            String strTimeStart = jsonObject.getString(ManagTABLE.COLUMN_TimeStart);
                            String strTimeEnd = jsonObject.getString(ManagTABLE.COLUMN_TimeEnd);
                            String strPlace = jsonObject.getString(ManagTABLE.COLUMN_Place);
                            String strLat = jsonObject.getString(ManagTABLE.COLUMN_Lat);
                            String strLng = jsonObject.getString(ManagTABLE.COLUMN_Lng);
                        objManagTABLE.addNewValueToPromotion(strPromotion,strCondition,strPictPromotion
                                ,strTimeStart,strTimeEnd,strPlace,strLat,strLng);
                        break;

                    case 3:

                        //for rewardTable
                        String strRewardName = jsonObject.getString(ManagTABLE.COLUMN_Reward_Name);
                        String strUserPoint = jsonObject.getString(ManagTABLE.COLUMN_Use_Point);
                        String strPict_Reward = jsonObject.getString(ManagTABLE.COLUMN_Pict_Reward);
                        objManagTABLE.addReward(strRewardName,strUserPoint,strPict_Reward);
                        break;

                    /*case 4:
                        //For promotionTABLE
                        String strPlace = jsonObject.getString(ManagTABLE.COLUMN_Place);
                        String strLat = jsonObject.getString(ManagTABLE.COLUMN_Lat);
                        String strLng = jsonObject.getString(ManagTABLE.COLUMN_Lng);
                        objManagTABLE.addNewValueToPlace(strPromotion,strCondition,strPictPromotion
                                ,strTimeStart,strTimeEnd,strPlace,strLat,strLng);
                        break;*/




                }//switch
                }//for
            } catch (Exception e) {
                Log.d(tag, "upData ==>" + e.toString());
            }


            intTimes += 1;
        }// while


    }

    private void setWidgetEventListener() {
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)); // หน้า Register
            }
        });
    }


    private void deleteAllSQLite() {// ลบข้อมูลออก แต่ตารางยังคงอยู่
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        objSqLiteDatabase.delete(ManagTABLE.TABLE_USER,null, null);
        objSqLiteDatabase.delete(ManagTABLE.TABLE_promotion, null, null);
        objSqLiteDatabase.delete(ManagTABLE.TABLE_REWARD, null, null);
    //    objSqLiteDatabase.delete(ManagTABLE.TABLE_PLACE, null, null);

    }

    private void testAddValue() {
        objManagTABLE.addNewValueToUser("testUser", "testPassword","testName",
                "testSurname","testAddress","testEmail","testPoint");

        objManagTABLE.addNewValueToPromotion("Promotion","Condition","PictPromotion","Start","End",
                "Place","Lat","Lng");
    }


}// Main class
