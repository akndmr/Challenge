package io.github.akndmr.mobillionchallenge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.akndmr.mobillionchallenge.R;
import io.github.akndmr.mobillionchallenge.model.Category_;
import io.github.akndmr.mobillionchallenge.util.AnimationUtil;

/**
 * Created by Akın DEMİR on 15.03.2019.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder>{

    public static final String TAG = CategoryAdapter.class.getSimpleName();

    private Context mContext;
    private List<Category_> mCategoryList;
    private int previousPosition = 0;

    public CategoryAdapter(Context context, List<Category_> categoryList) {
        mContext        = context;
        mCategoryList   = categoryList;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.single_category_item, viewGroup, false);

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryHolder categoryHolder, int i) {

        if(i > previousPosition){
            AnimationUtil.animate(categoryHolder);
        }else{
            AnimationUtil.animate(categoryHolder);
        }

        previousPosition = i;

        categoryHolder.categoryName.setText(mCategoryList.get(i).getName());

        Picasso.get()
                .load(mCategoryList.get(i).getLogo().getUrl())
                .into(categoryHolder.categoryImage);

    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{

        ImageView categoryImage;
        TextView categoryName;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            categoryImage    = itemView.findViewById(R.id.iv_category_image);
            categoryName    = itemView.findViewById(R.id.tv_category_name);
        }
    }
}
