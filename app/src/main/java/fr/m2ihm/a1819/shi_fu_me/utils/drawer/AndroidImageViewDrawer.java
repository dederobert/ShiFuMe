package fr.m2ihm.a1819.shi_fu_me.utils.drawer;

import android.support.v7.widget.AppCompatImageView;

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
