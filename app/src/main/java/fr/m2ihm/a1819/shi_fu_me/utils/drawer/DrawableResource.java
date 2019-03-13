package fr.m2ihm.a1819.shi_fu_me.utils.drawer;

/**
 * Représente une resource qui pourra être utilisé par un objet de type {@link Drawer} afin d'être rendue
 * @param <T> Type de ressource stocké
 * @version 1.0.0
 */
public abstract class DrawableResource<T> {
    /**
     * Ressource utilisé pour être rendue
     */
    private T resource;

    /**
     * Créer une ressource
     * @param resource Ressource à utiliser
     */
    DrawableResource(T resource) {
        this.resource = resource;
    }

    /**
     * Obtiens la ressource
     * @return La ressource à rendre
     */
    public T getResource() {
        return resource;
    }

    /**
     * Définie la ressource
     * @param resource La ressource à être rendue
     */
    public void setResource(T resource) {
        this.resource = resource;
    }

}
