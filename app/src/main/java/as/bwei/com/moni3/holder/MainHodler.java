package as.bwei.com.moni3.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import as.bwei.com.moni3.R;

/**
 * Created by HP on 2018/9/15.
 */

public class MainHodler extends RecyclerView.ViewHolder {

    public ImageView ivIcon;
    public TextView tvTitle;
    public TextView tvDestance;

    public MainHodler(View itemView) {
        super(itemView);

        ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvDestance = (TextView) itemView.findViewById(R.id.tv_distance);
    }
}
