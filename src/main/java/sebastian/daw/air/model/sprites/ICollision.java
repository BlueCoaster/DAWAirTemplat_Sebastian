package sebastian.daw.air.model.sprites;

/**
 *
 * @author Natalio
 */
public interface ICollision {

    /**
     * Enumerador de estado de colisión
     */
    public enum State {
        COLLISION,
        DEAD,
        FREE
    };

    public int getX();
    public int getY();
    public int getHeight();
    public int getWidht();
    
    /**
     * 
     * @param another
     * @return 
     */
    public default boolean isCollision(ICollision another) {
        boolean colision = false;
        
        // Si el punto x mas a la izquerida esta en el margen o si el punto mas a la derecha esta en el margen:
        if (another.getX() > this.getX() && another.getX() < this.getX() + this.getWidht() || another.getX() + another.getWidht() > this.getX() && another.getX() + another.getWidht() < this.getX() + this.getWidht()) {
            // O si el punto mas a la derecha esta en el margen:
            if (another.getY() > this.getY() && another.getY() < this.getY() + this.getHeight() || another.getY() + another.getHeight()> this.getY() && another.getY() + another.getHeight()< this.getY() + this.getHeight()) {
                colision = true;
                this.setColision();
                another.setColision();
            }
        }
        
        return colision;
    }

    public boolean hascollided();
    public void setColision();
    public void setFree();

}
