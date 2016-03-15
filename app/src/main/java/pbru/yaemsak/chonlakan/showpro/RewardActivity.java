package pbru.yaemsak.chonlakan.showpro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class RewardActivity  extends AppCompatActivity {

    //Explicit
    private ListView rewardListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        //bindwidget
        bindWidget();

        //Show view
        showView();

        //Create list view
        createListView();


    }//Main method

    public void clickMenu(View view) {
        startActivity(new Intent(RewardActivity.this, MenuActivity.class));
    }

    private void createListView () {
        //connect database
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ManagTABLE.TABLE_REWARD, null);
        cursor.moveToFirst();
        String[] nameRewardStrings = new String[cursor.getCount()];
        String[] pointRewardStrings = new String[cursor.getCount()];
        String[] iconStings = new String[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {

            nameRewardStrings[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_Reward_Name));
            pointRewardStrings[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_Use_Point));

            iconStings[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_Pict_Reward));

            cursor.moveToNext();
        } //for
        cursor.close();

        RewardMenuAdapter rewardMenuAdapter = new RewardMenuAdapter(RewardActivity.this, iconStings,
                nameRewardStrings, pointRewardStrings);
        rewardListView.setAdapter(rewardMenuAdapter);

    }//create list view

    private void showView() {
        String[] resultSting = getIntent().getStringArrayExtra("Result");
    }

    private void bindWidget() {
        rewardListView = (ListView) findViewById(R.id.listView4);
    }

}//main Class

