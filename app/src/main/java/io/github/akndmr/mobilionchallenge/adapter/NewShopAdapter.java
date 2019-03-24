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
import io.github.akndmr.mobilionchallenge.util.ConstantsUtil;

/**
 * Created by Akın DEMİR on 15.03.2019.
 */
public class NewShopAdapter extends RecyclerView.Adapter<NewShopAdapter.NewShopHolder>{

    public static final String TAG = NewShopAdapter.class.getSimpleName();

    private Context mContext;
    private List<Shop_> mShopList;
    private int previousPosition = 0;
    private String mType;

    public NewShopAdapter(Context context, List<Shop_> newShopList, String type) {
        mContext    = context;
        mShopList   = newShopList;
        mType       = type;
    }

    @NonNull
    @Override
    public NewShopHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.single_editor_shop_item_v, viewGroup, false);

        return new NewShopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewShopHolder newShopHolder, final int i) {

        if(mType.equals(ConstantsUtil.TYPE_NEW_SHOPS_W_BUTTON)){
            newShopHolder.followButton.setVisibility(View.VISIBLE);
            newShopHolder.followButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "You're now following " + mShopList.get(i).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Without button
        else{
            newShopHolder.followButton.setVisibility(View.GONE);
        }

        if(i > previousPosition){
            AnimationUtil.animate(newShopHolder);
        }else{
            AnimationUtil.animate(newShopHolder);
        }

        previousPosition = i;

        newShopHolder.itemName.setText(mShopList.get(i).getName());
        newShopHolder.itemDefinition.setText(mShopList.get(i).getDefinition());
        newShopHolder.productCount.setText(String.valueOf(mShopList.get(i).getProductCount()));

        if(mShopList.get(i).getCover() != null){
            Picasso.get()
                    .load(mShopList.get(i).getCover().getMedium().getUrl())
                    .placeholder(R.drawable.placeholder_item)
                    .error(R.drawable.placeholder_item)
                    .into(newShopHolder.itemImage);
        }
        else {
            Picasso.get()
                    .load(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.placeholder_item)
                    .error(R.drawable.placeholder_item)
                    .into(newShopHolder.itemImage);
        }
        if(mShopList.get(i).getLogo() != null){
            Picasso.get()
                    .load(mShopList.get(i).getLogo().getThumbnail().getUrl())
                    .placeholder(R.drawable.placeholder_store)
                    .error(R.drawable.placeholder_store)
                    .into(newShopHolder.shopLogo);
        }
        else {
            Picasso.get()
                    .load(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.placeholder_store)
                    .error(R.drawable.placeholder_store)
                    .into(newShopHolder.shopLogo);
        }

    }

    @Override
    public int getItemCount() {
        return mShopList.size();
    }

    public class NewShopHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        CircleImageView shopLogo;
        TextView itemName, itemDefinition, productCount;
        Button followButton;

        public NewShopHolder(@NonNull View itemView) {
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
