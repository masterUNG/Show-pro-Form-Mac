package pbru.yaemsak.chonlakan.showpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by chonlakan on 27/2/2559.
 */
public class RewardMenuAdapter extends BaseAdapter {


    //Explicit
    private Context context;
    private String[] nameRewardStrings,
            pointRewardStrings,
            iconStrings;

    public RewardMenuAdapter(Context context,
                             String[] iconStrings,
                             String[] nameRewardStrings,
                             String[] pointRewardStrings) {
        this.context = context;
        this.iconStrings = iconStrings;
        this.nameRewardStrings = nameRewardStrings;
        this.pointRewardStrings = pointRewardStrings;

    }//constructor

    @Override
    public int getCount() {
        return nameRewardStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.menu_reward_listview, viewGroup, false);

        ImageView iconImageView = (ImageView) view1.findViewById(R.id.imv2reward);
        Picasso.with(context).load(iconStrings[i]).resize(150,120).into(iconImageView);

        TextView nameRewardTextView = (TextView) view1.findViewById(R.id.textView32);
        nameRewardTextView.setText(nameRewardStrings[i]);

        TextView pointRewardTextView = (TextView) view1.findViewById(R.id.textView33);
        pointRewardTextView.setText(pointRewardStrings[i]);

        return view1;
    }
}//main class
