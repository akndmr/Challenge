package io.github.akndmr.mobilliumchallenge.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Akın DEMİR on 17.03.2019.
 */
public class AnimationUtil {

    public static void animate(RecyclerView.ViewHolder holder){

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(holder.itemView,"translationX",100,0);
        animatorTranslateX.setDuration(500);

        animatorSet.playTogether(animatorTranslateX);

        animatorSet.start();
    }
}
