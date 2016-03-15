package pbru.yaemsak.chonlakan.showpro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {


    //Explicit
    private TextView showNameTextView, showPointTextView;
    private ListView rewardListView;
    private int myscoreAnInt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_service);
        // Bind widget
        bindWidget();

        //Show view
        showView();

        //Create list view
        createListView();


    }//main method

    public void clickMenu(View view) {
        startActivity(new Intent(ServiceActivity.this,MenuActivity.class));
    }

    private void createListView() {
        //connect database
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ManagTABLE.TABLE_REWARD, null);
        cursor.moveToFirst();
        int[] iconSmallInts = new int[cursor.getCount()];
        String[] nameRewardStrings = new String[cursor.getCount()];
        String[] pointRewardStrings = new String[cursor.getCount()];
        String[] iconStings = new String[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {

            nameRewardStrings[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_Reward_Name));
            pointRewardStrings[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_Use_Point));

            iconSmallInts[i] = checkReward(pointRewardStrings[i]);

            iconStings[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_Pict_Reward));

            cursor.moveToNext();
        } //for
        cursor.close();

        RewardAdapter rewardAdapter = new RewardAdapter(ServiceActivity.this, iconStings,
                nameRewardStrings, pointRewardStrings, iconSmallInts);
        rewardListView.setAdapter(rewardAdapter);

    }//create list view

    private int checkReward(String pointRewardString) {// เช็คแต้ม

        int intIconSmall = R.drawable.t1;

        if (myscoreAnInt < Integer.parseInt(pointRewardString)) {
            intIconSmall = R.drawable.f1;
        }

        return intIconSmall;
    }

    private void showView() {

        String[] resultSting = getIntent().getStringArrayExtra("Result");
        showNameTextView.setText("Welcome :" + "  " + resultSting[3] + " " + resultSting[4]);
        showPointTextView.setText(resultSting[7] + " " + "คะแนน");
        myscoreAnInt = Integer.parseInt(resultSting[7]);

    }//show view

    private void bindWidget() {
        showNameTextView = (TextView) findViewById(R.id.textView9);
        showPointTextView = (TextView) findViewById(R.id.textView10);
        rewardListView = (ListView) findViewById(R.id.listView);
    }
}//main class
