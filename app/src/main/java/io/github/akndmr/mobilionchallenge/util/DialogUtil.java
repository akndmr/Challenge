package io.github.akndmr.mobilionchallenge.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import io.github.akndmr.mobilionchallenge.R;

/**
 * Created by Akın DEMİR on 19.03.2019.
 */
public class DialogUtil {

    public static void showDialogWith(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View customView = inflater.inflate(R.layout.about_me, null);
        builder.setView(customView);

        ImageView photo             = customView.findViewById(R.id.iv_about_me_photo);
        ImageView iconWebsite       = customView.findViewById(R.id.iv_about_me_website);
        ImageView iconLinkedIn      = customView.findViewById(R.id.iv_about_me_linkedin);
        ImageView iconGithub        = customView.findViewById(R.id.iv_about_me_github);
        ImageView iconGooglePlay    = customView.findViewById(R.id.iv_about_me_google_play);


        final AlertDialog b = builder.create();


        iconWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = context.getResources().getString(R.string.developer_website_url);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(browserIntent);
            }
        });
        iconLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = context.getResources().getString(R.string.developer_linkedin_url);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(browserIntent);
            }
        });
        iconGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = context.getResources().getString(R.string.developer_github_url);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(browserIntent);
            }
        });
        iconGooglePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = context.getResources().getString(R.string.developer_google_play_url);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(browserIntent);
            }
        });

        b.show();
    }

}
