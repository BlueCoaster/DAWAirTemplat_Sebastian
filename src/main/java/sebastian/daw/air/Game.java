package sebastian.daw.air;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import sebastian.daw.air.model.Size;
import sebastian.daw.air.model.sprites.IKeyListener;
import sebastian.daw.air.stages.AbstractScene;
import sebastian.daw.air.stages.GameStage;
import sebastian.daw.air.stages.IScene;


/**
 *
 * @author Sebastian
 */
public class Game implements IWarnClock, IKeyListener {

    
    public static Clock clock = new Clock(40);
    private AbstractScene escena_actual;
    private GraphicsContext ctx;


    public Game(GraphicsContext context, GraphicsContext bg_context, Size s) {
        Game.clock.addIWarClock(this);
        this.escena_actual=new GameStage(context,bg_context,s);
        this.ctx=context;
        
    }
    public void start() {
        this.clock.start();
    }
    public void stop() {
        this.clock.stop();
    }


    @Override
    public synchronized void TicTac() {
        this.escena_actual.TicTac();
        this.escena_actual.draw(ctx);
        if (this.escena_actual.getState()==IScene.SceneState.END) {
            System.out.println("Fin del juego");
        }
    }

    @Override
    public void onKeyPressed(KeyCode code) {
            this.escena_actual.onKeyPressed(code);

    }

    @Override
    public void onKeyReleased(KeyCode code) {
       this.escena_actual.onKeyReleased(code);
     }

}
