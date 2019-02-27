package fr.m2ihm.a1819.shi_fu_me.utils;

/**
 * Classe utilisé pour rendre un objet de type {@link DrawableResource}
 * @param <T> Type de {@link DrawableResource} utilisé pour être rendue
 */
public interface Drawer<T extends DrawableResource> {
    /**
     * Rend la ressource
     * @param resource Ressource à rendre
     */
    public void drawResource(T resource);
}
