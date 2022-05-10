package sebastian.daw.air;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sebastian.daw.air.model.Coordenada;
import sebastian.daw.air.model.Size;


/**
 *
 * @author Sebastian
 */
public class Background implements IWarnClock {

    // Declaramos las variables
    private String path; // Path
    private int speed; // Velocidad
    private Image background; // Imagen de fondo
    private Size size; // Tamaño
    private Coordenada c; // Coorenada Nº1
    private Coordenada c2; // Coordenada Nº2
    private Font font; // Tipografía //, Font.ITALIC, 50);
    private GraphicsContext bg; // Gráfico para el fondo
    
    /**
     * Constructor por defecto
     */
    public Background() {
        super();
    }
    
    /**
     * Constructor sobrecargado
     * 
     * @param path_img Pasamos la imágen
     * @param s Pasamos el tamaño
     * @param speed Pasamos la velocidad
     * @param start_position Pasamos la posición
     */
    public Background(String path_img, Size s, int speed, Coordenada start_position) {
        this.size = s;
        this.speed = speed;
        this.background = new Image(getClass().getResourceAsStream(path_img));//"/level1/background.png"));
        font = new Font("8BIT WONDER Nominal", 30);//, Font.ITALIC, 50);
        this.c = start_position;//new Coordenada(0,0);
        this.c2 = new Coordenada((int) this.background.getWidth(), 0);//this.speed, 0);
    }

    /**
     * Función pra cargar el fondo
     * 
     * @param g Gráficos
     */
    public void paint(GraphicsContext g) {
        // Ponemos las dos imágenes
        g.drawImage(this.background, 0, 0, this.size.getWidth() / 2, this.background.getHeight(),// this.c.getY(),
                this.c.getX(), 0, this.background.getWidth(), this.size.getHeight());
        g.drawImage(this.background, 0, 0, this.size.getWidth() / 2, this.background.getHeight(),// this.c.getY(),
                this.c2.getX(), 0, this.background.getWidth(), this.size.getHeight());

        g.setFill(Color.BLUE);
        g.setStroke(Color.BROWN);
        g.setFont(font);
        //g.strokeText("SCORE "+this.c2.getX(), this.size.getWidth()/2-100, 40);
    }

    /**
     * Función para mover el fondo cargado
     * 
     */
    private void move() {
        this.c.setX(this.c.getX() - this.speed);
        this.c2.setX(this.c2.getX() - this.speed);
        
        if (this.c2.getX() <= -this.background.getWidth()) {
            this.c2.setX((int) this.background.getWidth());
        }
        if (this.c.getX() <= -this.background.getWidth()) {
            this.c.setX((int) this.background.getWidth());
        }
     }

    /**
     * Método TicTac
     */
    @Override
    public void TicTac() {
        this.move();
    }

    /**
     * Get del fondo
     * 
     * @return the bg Devolvemos el fondo
     */
    public GraphicsContext getBg() {
        return bg;
    }

    /**
     * Set del fondo
     * 
     * @param bg the bg to set
     */
    public void setBg(GraphicsContext bg) {
        this.bg = bg;
    }
}
