package fr.m2ihm.a1819.shi_fu_me.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Classe représentant une {@link AppCompatImageView} qui peut rendre un objet de type {@link AndroidDrawableResource}
 * @version 1.0.0
 */
public final class AndroidImageViewDrawer  implements Drawer<AndroidDrawableResource> {


    private final AppCompatImageView imageView;

    public AndroidImageViewDrawer(AppCompatImageView imageView) {
        this.imageView = imageView;
    }


    @Override
    public void drawResource(AndroidDrawableResource resource) {
        this.imageView.setImageResource(resource.getResource());
    }
}
