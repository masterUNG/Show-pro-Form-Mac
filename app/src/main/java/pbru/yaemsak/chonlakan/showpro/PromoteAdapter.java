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
 * Created by chonlakan on 23/2/2559.
 */
public class PromoteAdapter extends BaseAdapter{

    //Explicit

    private Context context;
    private String[] iconStrings, titleStrings, startStrings, endStrings;

    public PromoteAdapter(Context context, String[] iconStrings,
                          String[] titleStrings,
                          String[] startStrings,
                          String[] endStrings) {
        this.context = context;
        this.iconStrings = iconStrings;
        this.titleStrings = titleStrings;
        this.startStrings = startStrings;
        this.endStrings = endStrings;
    }

    @Override
    public int getCount() {
        return iconStrings.length;//
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
        View view1 = layoutInflater.inflate(R.layout.promotion_listview, viewGroup, false);

        ImageView iconImageView = (ImageView) view1.findViewById(R.id.imageView7);
        Picasso.with(context).load(iconStrings[i]).resize(160,120).into(iconImageView);
        TextView titleTextView = (TextView) view1.findViewById(R.id.textView19);
        titleTextView.setText(titleStrings[i]);

        TextView startTextView = (TextView) view1.findViewById(R.id.textView21);
        startTextView.setText(startStrings[i]);

        TextView endTextView = (TextView) view1.findViewById(R.id.textView23);
        endTextView.setText(endStrings[i]);

        return view1;
    }
}//main class
