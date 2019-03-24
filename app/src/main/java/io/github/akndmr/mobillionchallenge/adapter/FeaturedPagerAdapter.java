package io.github.akndmr.mobillionchallenge.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.github.akndmr.mobillionchallenge.R;
import io.github.akndmr.mobillionchallenge.model.Featured;

/**
 * Created by Akın DEMİR on 13.03.2019.
 */
public class FeaturedPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<Featured> mFeaturedList;
    private LayoutInflater mLayoutInflater;

    public FeaturedPagerAdapter(Context context, ArrayList<Featured> featuredList) {
        mContext = context;
        mFeaturedList = featuredList;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mFeaturedList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View customView = mLayoutInflater.inflate(R.layout.single_featured_item, container, false);

        ImageView featuredImage     = customView.findViewById(R.id.iv_featured_image);
        TextView featuredTitle      = customView.findViewById(R.id.tv_featured_title);
        TextView featuredSubtitle   = customView.findViewById(R.id.tv_featured_subtitle);

        String imageUrl     = mFeaturedList.get(position).getCover().getMedium().getUrl();
        String title        = mFeaturedList.get(position).getTitle();
        String subtitle     = mFeaturedList.get(position).getSubTitle();

        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.placeholder_item)
                .placeholder(R.drawable.placeholder_item)
                .into(featuredImage);

        featuredTitle.setText(title);
        featuredSubtitle.setText(subtitle);

        container.addView(customView);

        return customView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeViewAt(position);
    }
}
