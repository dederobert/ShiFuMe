package fr.m2ihm.a1819.shi_fu_me.utils.drawer;

/**
 * Classe utilisé pour rendre un objet de type {@link DrawableResource}
 * @param <T> Type de {@link DrawableResource} utilisé pour être rendue
 */
interface Drawer<T extends DrawableResource> {
    /**
     * Rend la ressource
     * @param resource Ressource à rendre
     */
    void drawResource(T resource);
}
