package sebastian.daw.air.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import sebastian.daw.air.IWarnClock;
import sebastian.daw.air.model.Size;
import sebastian.daw.air.model.Coordenada;
import sebastian.daw.air.model.Rectangle;
import sebastian.daw.air.model.sprites.IKeyListener;
import sebastian.daw.air.model.sprites.IMove;
import sebastian.daw.air.model.sprites.SpriteMove;

/**
 *
 * @author Sebastian
 */
public class Fighter extends SpriteMove implements IKeyListener, IWarnClock {

    private boolean[] keys_presed;
    private Image img;
    //path para la imagen
    static String pathurl = "avion.png";
    //para la animación
    private int original_height;
    protected List<Bullets> balas;

    /**
     *
     * @param inc incremento del movimiento
     * @param s tamaño del avión
     * @param p coordenadas iniciales
     * @param board rectangulo con las dimensiones del juego para no salirse
     */
    public Fighter(int inc, Size s, Coordenada p, Rectangle board) {
        super(inc, s, p, true, true, board);
        this.keys_presed = new boolean[5];
        this.img = new Image(getClass().getResourceAsStream("/" + Fighter.pathurl));

//cambia al mover arriba y abajo
        this.original_height = s.getHeight();
        this.balas = new ArrayList<>();

    }

    /**
     * acciones al pulsar las teclas
     *
     * @param code
     */
    @Override
    public void onKeyPressed(KeyCode code) {

        if (code == KeyCode.RIGHT) {
            this.keys_presed[0] = true;
        }
        if (code == KeyCode.LEFT) {
            this.keys_presed[1] = true;
        }
        if (code == KeyCode.UP) {
            this.keys_presed[2] = true;
            this.getSize().setHeight(40);
        }
        if (code == KeyCode.DOWN) {
            this.keys_presed[3] = true;
            this.getSize().setHeight(40);
        }

    }

    /**
     * acciones al soltar el teclado
     *
     * @param code
     */
    @Override
    public void onKeyReleased(KeyCode code) {

        if (code == KeyCode.SPACE) {

            //crear una bala y añadirla
            Bullets e;
            e= new Bullets( this.inc, new Coordenada(this.getPosicion().getX()+this.getSize().getWidth(),this.getPosicion().getY()),this.board);
            balas.add(e);
            e.setDireccion(Direction.RIGHT);
        }
        if (code == KeyCode.RIGHT) {
            this.keys_presed[0] = false;
        }
        if (code == KeyCode.LEFT) {
            this.keys_presed[1] = false;
        }
        if (code == KeyCode.UP) {
            this.keys_presed[2] = false;
            this.getSize().setHeight(original_height);
        }
        if (code == KeyCode.DOWN) {
            this.keys_presed[3] = false;
            this.getSize().setHeight(original_height);
        }
    }

    /**
     * dibujar, es algo más complejo al moverse las alas
     *
     * @param gc
     */
    @Override
    public void draw(GraphicsContext gc) {
        if (keys_presed[2]) {
            gc.drawImage(img, 163, 7, this.getSize().getWidth() / 2, this.getSize().getHeight() / 2,
                    this.getPosicion().getX(), this.getPosicion().getY(),
                    this.getSize().getWidth(), this.getSize().getHeight());
        } else {
            if (keys_presed[3]) {
                gc.drawImage(img, 54, 7, this.getSize().getWidth() / 2, this.getSize().getHeight() / 2,
                        this.getPosicion().getX(), this.getPosicion().getY(),
                        this.getSize().getWidth(), this.getSize().getHeight());
            } else {
                gc.drawImage(img, 105, 8, this.getSize().getWidth() / 2, this.getSize().getHeight() / 2,
                        this.getPosicion().getX(), this.getPosicion().getY(),
                        this.getSize().getWidth(), this.getSize().getHeight());
            }
        }
        this.balas.forEach(b -> b.draw(gc));
    }

    //movimiento del avión
    private void move() {

        if (this.keys_presed[0]) {
            this.move(IMove.Direction.RIGHT);
        }
        if (this.keys_presed[1]) {
            this.move(IMove.Direction.LEFT);
        }
        if (this.keys_presed[2]) {
            this.move(IMove.Direction.UP);
        }
        if (this.keys_presed[3]) {
            this.move(IMove.Direction.DOWN);
        }
    }

    /**
     * cada vez que se recibe un tictac se mueve, faltan las balas del fighter
     */
    @Override
    public void TicTac() {
        this.move();
        
        //mover las balas 
        this.balas.forEach(ba -> ba.TicTac());
        this.balas.removeIf(a->{
                return !a.isLive();
        
        });
        
    }

}
