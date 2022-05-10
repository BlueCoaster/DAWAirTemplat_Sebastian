package sebastian.daw.air.stages;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sebastian.daw.air.model.Coordenada;
import sebastian.daw.air.model.Level;
import sebastian.daw.air.model.Player;
import sebastian.daw.air.model.Size;

/**
 *
 * @author Pedro
 */
public class GameStage extends AbstractScene {

    private Level actual;
    private Size s;
    private static Font font;//, Font.ITALIC, 50);
    private Player player;

    private static float[][] probabilidades = {
        {0.2f, 1f},
        {0.6f, 1f},
        {0.5f, 1f}
    };

    static {
        font = new Font("8BIT WONDER Nominal", 30);//, Font.ITALIC, 50);
    }

    //0 delante, 1 detras,2 arriba, 3 abajo, 4 disparo
    //private boolean[] keys_presed;
    public GameStage(GraphicsContext context, GraphicsContext bg_context, Size s) {
        super(context, bg_context, s, null);
        this.s = s;
        this.state = SceneState.PRE_STARTED;
        this.actual = new Level("/level1/bg2.png", "/level1/music.mp3", s, 2, new Coordenada(0, 0), bg_context, GameStage.probabilidades[0], 2500);
       this.initFactory();
        this.player = new Player();
    }

    //se inicializan la factoria de enemigos
    private void initFactory() {

    }

    @Override
    public void TicTac() {
        if (this.state == SceneState.STARTED) {
            this.actual.TicTac();
            //para pasar de nivel
            if (this.actual.getEstado() == Level.Estado.END) {

                //no quedan niveles
                this.stop();

            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onKeyPressed(KeyCode code) {
        if (this.state == SceneState.STARTED) {
            this.actual.onKeyPressed(code);
        }
    }

    @Override
    public void onKeyReleased(KeyCode code) {
        if (GameStage.SceneState.PRE_STARTED == this.state) {
            this.state = GameStage.SceneState.STARTED;
        }

        if (this.state == SceneState.STARTED) {
            this.actual.onKeyReleased(code);
        }

    }

    @Override
    public void draw(GraphicsContext gc) {
        this.actual.draw(gc);
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BROWN);
        gc.setFont(GameStage.font);
        gc.strokeText("SCORE " + this.player.getScore(), this.s.getWidth() / 2 - 100, 40);
    }

}
