package pbru.yaemsak.chonlakan.showpro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class PromotionActivity extends AppCompatActivity {

    //Explicit
    private ListView promotionListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        //Bind Widget
        promotionListView = (ListView)findViewById(R.id.listView2);

        //CreateListView
        CreateListView();

    }//Main method

    private void CreateListView() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ManagTABLE.TABLE_promotion +
                " ORDER BY _id DESC", null);//เรียงลำดับจากข้อมูลมากไปน้อย , ASC น้อยไปมาก
        cursor.moveToFirst();

        String[] namePromotion = new String[cursor.getCount()];
        String[] picPromotion = new String[cursor.getCount()];
        String[] startPromotion = new String[cursor.getCount()];
        String[] endPromotion = new String[cursor.getCount()];
        final String[] idStrings = new String[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {

            namePromotion[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_NamePromotion));
            picPromotion[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_PictPromotion));
            startPromotion[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_TimeStart));
            endPromotion[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_TimeEnd));
            idStrings[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_id));

            cursor.moveToNext();//สั่งให้ cursor เลื่อนตำแหน่ง
        }//for
        cursor.close();

        //create list view
        PromoteAdapter promoteAdapter = new PromoteAdapter(PromotionActivity.this,
                picPromotion, namePromotion, startPromotion, endPromotion);
        promotionListView.setAdapter(promoteAdapter);

        promotionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(PromotionActivity.this, DetailPromotionActivity.class);
                intent.putExtra("ID", idStrings[i]);
                startActivity(intent);

            }
        });

    }//create list view

    public void clickBackMenu(View view) {
        startActivity(new Intent(PromotionActivity.this, MenuActivity.class));
    }
}// Main Class
