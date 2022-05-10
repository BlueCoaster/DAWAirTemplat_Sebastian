/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sebastian.daw.air.stages;

import sebastian.daw.air.model.sprites.IDrawable;
import sebastian.daw.air.model.sprites.IKeyListener;
import sebastian.daw.air.IWarnClock;



/**
 *
 * @author Pedro
 */
public interface IScene extends IKeyListener, IWarnClock, IDrawable{
    public enum SceneState{
        PRE_STARTED,
        STARTED,
        PAUSED,
        PRE_END,
        END
    }
    public SceneState getState();
    public void start();
    public void stop();
    public void pause();
    public void reset();
}
