package io.github.akndmr.mobilliumchallenge.adapter;

import android.content.Context;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.akndmr.mobilliumchallenge.R;
import io.github.akndmr.mobilliumchallenge.model.Product;
import io.github.akndmr.mobilliumchallenge.util.AnimationUtil;
import io.github.akndmr.mobilliumchallenge.util.ConstantsUtil;

/**
 * Created by Akın DEMİR on 15.03.2019.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{

    public static final String TAG = ProductAdapter.class.getSimpleName();

    private Context mContext;
    private List<Product> mProductList;
    private String mOrientation;
    private int previousPosition = 0;

    public ProductAdapter(Context context, List<Product> productList, String orientation) {
        mContext        = context;
        mProductList    = productList;
        mOrientation    = orientation;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view;

        if(mOrientation.equals(ConstantsUtil.ORIENTATION_H)){
            view = layoutInflater.inflate(R.layout.single_product_item_h, viewGroup, false);
        }
        else{
            view = layoutInflater.inflate(R.layout.single_product_item_v, viewGroup, false);
        }

        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductHolder productHolder, int i) {

        // Animate recyclerview items
        if(i > previousPosition){
            AnimationUtil.animate(productHolder);
        }else{
            AnimationUtil.animate(productHolder);
        }
        previousPosition = i;

        // Get product at index i
        final Product product = mProductList.get(i);

        productHolder.productTitle.setText(product.getTitle());
        productHolder.productStore.setText(product.getShop().getName());

        String priceStr = String.valueOf(product.getPrice()) + "TL";
        productHolder.productPrice.setText(priceStr);

        Integer oldPrice = product.getOldPrice();

        // Check if there is old price. If so, strike thru
        if(oldPrice != null){
            String oldPriceStr = String.valueOf(product.getOldPrice()) + "TL";
            TextView textView = productHolder.productPriceOld;
            textView.setText(oldPriceStr);
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }

        Picasso.get()
                .load(product.getImages().get(0).getThumbnail().getUrl())
                .placeholder(R.drawable.placeholder_item)
                .error(R.drawable.placeholder_item)
                .into(productHolder.productImage);

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder{

        ImageView productImage;
        TextView productTitle, productStore, productPrice, productPriceOld;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            productImage    = itemView.findViewById(R.id.iv_product_image);
            productTitle    = itemView.findViewById(R.id.tv_product_title);
            productStore    = itemView.findViewById(R.id.tv_product_vitrin);
            productPrice    = itemView.findViewById(R.id.tv_product_price);
            productPriceOld = itemView.findViewById(R.id.tv_product_price_old);
        }
    }
}
