package io.github.akndmr.mobilionchallenge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.akndmr.mobilionchallenge.R;
import io.github.akndmr.mobilionchallenge.model.Shop_;
import io.github.akndmr.mobilionchallenge.util.AnimationUtil;

/**
 * Created by Akın DEMİR on 15.03.2019.
 */
public class EditorShopVAdapter extends RecyclerView.Adapter<EditorShopVAdapter.EditorShopVHolder>{

    public static final String TAG = EditorShopVAdapter.class.getSimpleName();

    private Context mContext;
    private List<Shop_> mShopList;
    private int previousPosition = 0;

    public EditorShopVAdapter(Context context, List<Shop_> categoryList) {
        mContext    = context;
        mShopList   = categoryList;
    }

    @NonNull
    @Override
    public EditorShopVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.single_editor_shop_item_v, viewGroup, false);

        return new EditorShopVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EditorShopVHolder editorShopHolder, final int i) {

        if(i > previousPosition){
            AnimationUtil.animate(editorShopHolder);
        }else{
            AnimationUtil.animate(editorShopHolder);
        }

        previousPosition = i;

        editorShopHolder.itemName.setText(mShopList.get(i).getName());
        editorShopHolder.itemDefinition.setText(mShopList.get(i).getDefinition());
        editorShopHolder.productCount.setText(String.valueOf(mShopList.get(i).getProductCount()));

        Picasso.get()
                .load(mShopList.get(i).getCover().getMedium().getUrl())
                .placeholder(R.drawable.placeholder_item)
                .error(R.drawable.placeholder_item)
                .into(editorShopHolder.itemImage);
        Picasso.get()
                .load(mShopList.get(i).getLogo().getThumbnail().getUrl())
                .placeholder(R.drawable.placeholder_store)
                .error(R.drawable.placeholder_store)
                .into(editorShopHolder.shopLogo);

        editorShopHolder.followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "You're now following " + mShopList.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mShopList.size();
    }

    public class EditorShopVHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        CircleImageView shopLogo;
        TextView itemName, itemDefinition, productCount;
        Button followButton;

        public EditorShopVHolder(@NonNull View itemView) {
            super(itemView);

            itemImage       = itemView.findViewById(R.id.iv_editor_shop_all_item);
            shopLogo        = itemView.findViewById(R.id.iv_editor_shop_all_shop_logo);
            itemName        = itemView.findViewById(R.id.tv_editor_shop_all_item_name);
            itemDefinition  = itemView.findViewById(R.id.tv_editor_shop_all_item_definition);
            productCount    = itemView.findViewById(R.id.tv_editor_shop_all_item_count);
            followButton    = itemView.findViewById(R.id.btn_editor_shop_all_follow);
        }
    }

}
