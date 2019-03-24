package io.github.akndmr.mobillionchallenge.adapter;

import android.content.Context;
import android.net.Uri;
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
import io.github.akndmr.mobillionchallenge.R;
import io.github.akndmr.mobillionchallenge.model.Shop_;
import io.github.akndmr.mobillionchallenge.util.AnimationUtil;

/**
 * Created by Akın DEMİR on 15.03.2019.
 */
public class EditorShopAdapter extends RecyclerView.Adapter<EditorShopAdapter.EditorShopHolder>{

    public static final String TAG = EditorShopAdapter.class.getSimpleName();

    private Context mContext;
    private List<Shop_> mShopList;
    private int previousPosition = 0;

    public EditorShopAdapter(Context context, List<Shop_> categoryList) {
        mContext    = context;
        mShopList   = categoryList;
    }

    @NonNull
    @Override
    public EditorShopHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.single_editor_shop_item, viewGroup, false);

        return new EditorShopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EditorShopHolder editorShopHolder, int i) {

        if(i > previousPosition){
            AnimationUtil.animate(editorShopHolder);
        }else{
            AnimationUtil.animate(editorShopHolder);
        }

        previousPosition = i;

        editorShopHolder.shopName.setText(mShopList.get(i).getName());
        editorShopHolder.shopDefinition.setText(mShopList.get(i).getDefinition());

        Picasso.get()
                .load(mShopList.get(i).getLogo().getThumbnail().getUrl())
                .placeholder(R.drawable.placeholder_item)
                .error(R.drawable.placeholder_item)
                .into(editorShopHolder.shopLogo);
        Picasso.get()
                .load(mShopList.get(i).getPopularProducts().get(0).getImages().get(0).getThumbnail().getUrl())
                .placeholder(R.drawable.placeholder_item_square)
                .error(R.drawable.placeholder_item_square)
                .into(editorShopHolder.popularItem1);
        Picasso.get()
                .load(mShopList.get(i).getPopularProducts().get(1).getImages().get(0).getThumbnail().getUrl())
                .placeholder(R.drawable.placeholder_item_square)
                .error(R.drawable.placeholder_item_square)
                .into(editorShopHolder.popularItem2);
        Picasso.get()
                .load(mShopList.get(i).getPopularProducts().get(2).getImages().get(0).getThumbnail().getUrl())
                .placeholder(R.drawable.placeholder_item_square)
                .error(R.drawable.placeholder_item_square)
                .into(editorShopHolder.popularItem3);

        final String shopLink = Uri.parse(mShopList.get(i).getShareUrl()).toString();

        editorShopHolder.goToShopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, shopLink, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mShopList.size();
    }

    public class EditorShopHolder extends RecyclerView.ViewHolder{

        ImageView shopLogo, popularItem1, popularItem2, popularItem3;
        TextView shopName, shopDefinition;
        Button goToShopButton;

        public EditorShopHolder(@NonNull View itemView) {
            super(itemView);

            shopLogo            = itemView.findViewById(R.id.iv_editor_shop_logo);
            popularItem1        = itemView.findViewById(R.id.iv_editor_shop_image1);
            popularItem2        = itemView.findViewById(R.id.iv_editor_shop_image2);
            popularItem3        = itemView.findViewById(R.id.iv_editor_shop_image3);
            shopName            = itemView.findViewById(R.id.tv_editor_shop_name);
            shopDefinition      = itemView.findViewById(R.id.tv_editor_shop_definition);
            goToShopButton      = itemView.findViewById(R.id.btn_editor_shop_go_to_shop);
        }
    }
}
