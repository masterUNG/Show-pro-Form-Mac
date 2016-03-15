package pbru.yaemsak.chonlakan.showpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void clickBackMenu(View view) {
        startActivity(new Intent(AboutActivity.this, MenuActivity.class));
    }

}// Main Class