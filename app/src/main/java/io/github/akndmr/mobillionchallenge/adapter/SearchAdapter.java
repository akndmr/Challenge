package io.github.akndmr.mobillionchallenge.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.akndmr.mobillionchallenge.R;
import io.github.akndmr.mobillionchallenge.model.Product;

/**
 * Created by Akın DEMİR on 19.03.2019.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {

    private List<Product> mProductList;
    private List<Product> mProductListFull;
    Context mContext;

    public SearchAdapter(Context context, List<Product> productListFiltered) {
        mContext = context;
        mProductList = productListFiltered;
        mProductListFull = new ArrayList<>(productListFiltered);
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.single_product_item_h, parent, false);

        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        final Product product = mProductList.get(position);

        holder.productTitle.setText(product.getTitle());
        holder.productStore.setText(product.getShop().getName());

        String priceStr = String.valueOf(product.getPrice()) + "TL";
        holder.productPrice.setText(priceStr);

        Integer oldPrice = product.getOldPrice();

        // Check if there is old price. If so, strike thru
        if(oldPrice != null){
            String oldPriceStr = String.valueOf(product.getOldPrice()) + "TL";
            TextView textView = holder.productPriceOld;
            textView.setText(oldPriceStr);
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }

        Picasso.get()
                .load(product.getImages().get(0).getThumbnail().getUrl())
                .placeholder(R.drawable.placeholder_item)
                .error(R.drawable.placeholder_item)
                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Product> productListFiltered = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                // Search box is empty - return full list
                productListFiltered.addAll(mProductListFull);
            }
            else{
                // User entered a query
                String filterQuery = constraint.toString().toLowerCase().trim();

                for(Product product : mProductListFull){
                    if(product.getTitle().toLowerCase().contains(filterQuery)){
                        productListFiltered.add(product);
                    }
                    else if(product.getDefinition().toLowerCase().contains(filterQuery)){
                        productListFiltered.add(product);
                    }
                }
            }

            // FilterResult to hold filtered results(list)
            FilterResults filterResults = new FilterResults();
            filterResults.values = productListFiltered;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            // Clear product list to add only filtered items
            mProductList.clear();

            // Add filtered results into our list
            mProductList.addAll((List<Product>)results.values);

            // Notify adapter
            notifyDataSetChanged();

            if(mProductList.size() == 0){
                Toast.makeText(mContext, mContext.getString(R.string.no_products_matched), Toast.LENGTH_SHORT).show();
            }
        }
    };

    class SearchViewHolder extends RecyclerView.ViewHolder{

        ImageView productImage;
        TextView productTitle, productStore, productPrice, productPriceOld;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage    = itemView.findViewById(R.id.iv_product_image);
            productTitle    = itemView.findViewById(R.id.tv_product_title);
            productStore    = itemView.findViewById(R.id.tv_product_vitrin);
            productPrice    = itemView.findViewById(R.id.tv_product_price);
            productPriceOld = itemView.findViewById(R.id.tv_product_price_old);
        }
    }
}
