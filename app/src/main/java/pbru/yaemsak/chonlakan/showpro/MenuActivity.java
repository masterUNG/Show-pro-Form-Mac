package pbru.yaemsak.chonlakan.showpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private ImageView promotionImageView,
            locationImageView,
            rewardImageView,
            aboutImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //bind widget
        bindWidget();

        //Image Controller
        imageController();

    }// Main method

    private void imageController() {
        promotionImageView.setOnClickListener(this);
        locationImageView.setOnClickListener(this);
        rewardImageView.setOnClickListener(this);
        aboutImageView.setOnClickListener(this);

    }//imageController

    private void bindWidget() {
        promotionImageView = (ImageView)findViewById(R.id.imageView2);
        locationImageView  = (ImageView)findViewById(R.id.imageView4);
        rewardImageView  = (ImageView)findViewById(R.id.imageView5);
        aboutImageView = (ImageView)findViewById(R.id.imageView6);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.imageView2:
                startActivity(new Intent(MenuActivity.this, PromotionActivity.class));
                break;
            case R.id.imageView4:

                break;
            case R.id.imageView5:
                startActivity(new Intent(MenuActivity.this, RewardActivity.class));
                break;
            case R.id.imageView6:
                startActivity(new Intent(MenuActivity.this, AboutActivity.class));
                break;


        }//switch

    }//on click

}// Main class
