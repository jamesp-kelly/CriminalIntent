package com.bignerdranch.android.criminalintent;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by james on 04/01/2016.
 */
public class ImageViewFragment extends DialogFragment {

    private static final String ARG_IMG_FILE = "img_file";

    private ImageView mImageView;

    public static ImageViewFragment newInstance(File imageFile) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_IMG_FILE, imageFile);

        ImageViewFragment fragment = new ImageViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_image, null);

        final File imageFile = (File) getArguments().getSerializable(ARG_IMG_FILE);

        mImageView = (ImageView) v.findViewById(R.id.suspect_image_view);

        if (imageFile == null || !imageFile.exists()) {
            mImageView.setImageDrawable(null);
        } else {

            //todo: fix this so image loads properly

            ViewTreeObserver treeObserver = mImageView.getViewTreeObserver();
            treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int height = mImageView.getMeasuredHeight();
                    int width = mImageView.getMeasuredWidth();

                    Bitmap bitmap = PictureUtils.getScaledBitmap(imageFile.getPath(), width, height);
                    mImageView.setImageBitmap(bitmap);
                }
            });
        }

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.image_view_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
