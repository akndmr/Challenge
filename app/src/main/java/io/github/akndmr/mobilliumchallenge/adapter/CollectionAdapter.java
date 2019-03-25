package io.github.akndmr.mobilliumchallenge.adapter;

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
import io.github.akndmr.mobilliumchallenge.R;
import io.github.akndmr.mobilliumchallenge.model.Collection;
import io.github.akndmr.mobilliumchallenge.util.AnimationUtil;
import io.github.akndmr.mobilliumchallenge.util.ConstantsUtil;

/**
 * Created by Akın DEMİR on 15.03.2019.
 */
public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionHolder>{

    public static final String TAG = CollectionAdapter.class.getSimpleName();

    private Context mContext;
    private List<Collection> mCollectionList;
    private int previousPosition = 0;
    private String mType;

    public CollectionAdapter(Context context, List<Collection> categoryList, String type) {
        mContext        = context;
        mCollectionList = categoryList;
        mType           = type;
    }

    @NonNull
    @Override
    public CollectionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view;

        if(mType.equals(ConstantsUtil.TYPE_COLLECTIONS)){
            view = layoutInflater.inflate(R.layout.single_collection_item, viewGroup, false);
        }
        else {
            view = layoutInflater.inflate(R.layout.single_collection_item_v, viewGroup, false);
        }

        return new CollectionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CollectionHolder collectionHolder, int i) {

        if(i > previousPosition){
            AnimationUtil.animate(collectionHolder);
        }else{
            AnimationUtil.animate(collectionHolder);
        }

        previousPosition = i;

        collectionHolder.collectionName.setText(mCollectionList.get(i).getTitle());
        collectionHolder.collectionDefinition.setText(mCollectionList.get(i).getDefinition());

        Picasso.get()
                .load(mCollectionList.get(i).getCover().getMedium().getUrl())
                .placeholder(R.drawable.placeholder_item)
                .error(R.drawable.placeholder_item)
                .into(collectionHolder.collectionImage);

    }

    @Override
    public int getItemCount() {
        return mCollectionList.size();
    }

    public class CollectionHolder extends RecyclerView.ViewHolder{

        ImageView collectionImage;
        TextView collectionName, collectionDefinition;

        public CollectionHolder(@NonNull View itemView) {
            super(itemView);

            collectionImage         = itemView.findViewById(R.id.iv_collection_image);
            collectionName          = itemView.findViewById(R.id.tv_collection_name);
            collectionDefinition    = itemView.findViewById(R.id.tv_collection_definition);
        }
    }
}
