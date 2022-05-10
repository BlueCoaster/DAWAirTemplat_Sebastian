/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sebastian.daw.air.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sebastian.daw.air.IWarnClock;
import sebastian.daw.air.model.sprites.IMove;
import sebastian.daw.air.model.sprites.SpriteMove;
/**
 *
 * @author DAM
 */

    

/**
 *
 * @author Natalio
 */
public class Enemy1 extends Enemy {
   private Image img;
  
    //path para la imagen
    public static String pathurl="enemigos/e1.png";
    //para la animación
    private int original_height;

    public Enemy1() {
       
        this.img = new Image(getClass().getResourceAsStream("/"+Enemy1.pathurl));
          
    }
    
   
    
   @Override
     public void initEnemy(Coordenada p, Rectangle board){
    
    super.init(3, this.getSize(), p, true, true, board);
    
    }
    
    
    

    
    //movimiento del Enemigo
    private void move() {
     
        this.move(IMove.Direction.LEFT);
        
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

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, 0, 0, this.getSize().getWidth(), this.getSize().getHeight(),
                    this.getPosicion().getX(), this.getPosicion().getY(),
                    this.getSize().getWidth(), this.getSize().getHeight());
    
           this.balas.forEach(b -> b.draw(gc));
    }

   
  
}

