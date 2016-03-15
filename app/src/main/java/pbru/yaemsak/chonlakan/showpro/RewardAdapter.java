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
 * Created by chonlakan on 18/2/2559.
 */
public class RewardAdapter extends BaseAdapter{

    //Explicit
    private Context context;

    private String[] nameRewardStrings,pointRewardStrings, iconStrings;
    private int[] iconSmallInts;

    public RewardAdapter(Context context,
                         String[] iconStrings,
                         String[] nameRewardStrings,
                         String[] pointRewardStrings,
                         int[] iconSmallInts) {
        this.context = context;
        this.iconStrings = iconStrings;
        this.nameRewardStrings = nameRewardStrings;
        this.pointRewardStrings = pointRewardStrings;
        this.iconSmallInts = iconSmallInts;//ส่ง

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
        View view1 = layoutInflater.inflate(R.layout.reward_listview, viewGroup, false);

        ImageView iconImageView = (ImageView) view1.findViewById(R.id.imvreward);
        Picasso.with(context).load(iconStrings[i]).resize(120,120).into(iconImageView);

        TextView nameRewardTextView = (TextView) view1.findViewById(R.id.textView12);
        nameRewardTextView.setText(nameRewardStrings[i]);

        TextView pointRewardTextView = (TextView) view1.findViewById(R.id.textView13);
        pointRewardTextView.setText(pointRewardStrings[i]);

        ImageView iconSmallImageView = (ImageView)view1.findViewById(R.id.imageView3);
        iconSmallImageView.setImageResource(iconSmallInts[i]);

        return view1;
    }
}//main class
