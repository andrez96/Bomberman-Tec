/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanLogic;

import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author User
 */
public class GameGraphic extends javax.swing.JFrame implements KeyListener{
    
    GlobalSingleton globals = GlobalSingleton.getGlobalSingleton();
    
    
    
    private GameLogic game;
    private int HeroY = 1;
    private int HeroX = 1;
    int bomX;
    int bomY;
    
   
    
    JLabel [][] matrix;

    public GameGraphic(GameLogic game) {
        this.game = game;
        matrix = new JLabel[game.getSize()][game.getSize()];
 
        addKeyListener(this);
       
       int sizeImage = game.getSizeImage();
        for (int x = 0; x < game.getSize(); x++) {
            for (int y = 0; y < game.getSize(); y++) {
                JLabel posMatrix = new JLabel();

                posMatrix.setBounds(x*sizeImage, y*sizeImage, sizeImage, sizeImage);
                posMatrix.setIcon(setIcon(sizeImage,"/bombermanImages/BarrierBlock.png"));
                matrix[y][x] = posMatrix;
                this.add(posMatrix);
                
                if(((x%2)!=0) ||((y%2)!=0)){

                    matrix[y][x].setIcon(setIcon(sizeImage,"/bombermanImages/EmptySpace.png"));
                }
                

            }
        }
        if(game.getLogicMatriz()[1][1].getCode()==1){
            matrix[1][1].setIcon(setIcon(sizeImage,"/bombermanImages/Hero.png"));
        }
        
        for(int x = 0; x < matrix.length;x++){
            matrix [0][x].setIcon(setIcon(sizeImage,"/bombermanImages/BarrierBlock.png"));
            matrix [matrix.length-1][x].setIcon(setIcon(sizeImage,"/bombermanImages/BarrierBlock.png"));
            matrix [x][0].setIcon(setIcon(sizeImage,"/bombermanImages/BarrierBlock.png"));
            matrix [x][matrix.length-1].setIcon(setIcon(sizeImage,"/bombermanImages/BarrierBlock.png"));
        }
        
        for (int x = 0; x < game.getSize(); x++) {
            for (int y = 0; y < game.getSize(); y++) {
                if((game.getLogicMatriz()[y][x].getCode()==5)||(game.getLogicMatriz()[y][x].getCode()==10)||(game.getLogicMatriz()[y][x].getCode()==11)){
                    matrix[y][x].setIcon(setIcon(sizeImage,"/bombermanImages/DestructibleBlock.png"));
                }
            }
        }
        
        for (int x = 0; x < game.getSize(); x++) {
            for (int y = 0; y < game.getSize(); y++) {
                if(game.getLogicMatriz()[y][x].getCode()==2){
                    matrix[y][x].setIcon(setIcon(sizeImage,"/bombermanImages/Balloons.png"));
                }
            }
        }
        
        for (int x = 0; x < game.getSize(); x++) {
            for (int y = 0; y < game.getSize(); y++) {
                if(game.getLogicMatriz()[y][x].getCode()==3){
                    matrix[y][x].setIcon(setIcon(sizeImage,"/bombermanImages/Barrels.png"));
                }
            }
        }
        
        
        

        JLabel label = new JLabel();
        this.add(label);
        
        monstersThread h = new monstersThread();
        h.start();
        
    }
    
    
    
    public ImageIcon setIcon(int scale,String direction){
    URL url = getClass().getResource(direction);
    ImageIcon icon = new ImageIcon(url);
    ImageIcon tempIcon = new ImageIcon(icon.getImage().getScaledInstance(scale, -1, Image.SCALE_DEFAULT));
    return tempIcon;
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent key) {
        System.out.println(key.getKeyCode());
        
        //keyLeft
        if(key.getKeyCode()==37){
            
            if(globals.getLogic().getLogicMatriz()[HeroY][HeroX-1].getCode()==6){
                if(globals.getLogic().getLogicMatriz()[HeroY][HeroX].getCode()==7){
                    matrix[HeroY][HeroX].setIcon(setIcon(game.getSizeImage(),"/bombermanImages/Bomb.png"));
                }
                else{
                    matrix[HeroY][HeroX].setIcon(setIcon(game.getSizeImage(),"/bombermanImages/EmptySpace.png"));
                }
                
                matrix[HeroY][HeroX-1].setIcon(setIcon(game.getSizeImage(),"/bombermanImages/Hero.png"));
                HeroX--;
                System.out.println(HeroX +" "+ HeroY);
                game.getHero().moveLeft(HeroX,HeroY);
                globals.getLogic().viewMatriz();
                }

            }
            
            
        
        //keyRight
        if(key.getKeyCode()==39){
              
            if(globals.getLogic().getLogicMatriz()[HeroY][HeroX+1].getCode()==6){
                if(globals.getLogic().getLogicMatriz()[HeroY][HeroX].getCode()==7){
                    matrix[HeroY][HeroX].setIcon(setIcon(game.getSizeImage(),"/bombermanImages/Bomb.png"));
                }
                else{
                    matrix[HeroY][HeroX].setIcon(setIcon(game.getSizeImage(),"/bombermanImages/EmptySpace.png"));
                }
                
                matrix[HeroY][HeroX+1].setIcon(setIcon(game.getSizeImage(),"/bombermanImages/Hero.png"));
  
            HeroX++;
            
            game.getHero().moveRight(HeroX,HeroY);
            globals.getLogic().viewMatriz();
            
        }}
        //KeyUp
        if(key.getKeyCode()==38){
 
            if(globals.getLogic().getLogicMatriz()[HeroY-1][HeroX].getCode()==6){
                matrix[HeroY-1][HeroX].setIcon(setIcon(game.getSizeImage(),"/bombermanImages/Hero.png"));
                if(globals.getLogic().getLogicMatriz()[HeroY][HeroX].getCode()==7){
                    matrix[HeroY][HeroX].setIcon(setIcon(game.getSizeImage(),"/bombermanImages/Bomb.png"));
                }
                else{
                    matrix[HeroY][HeroX].setIcon(setIcon(game.getSizeImage(),"/bombermanImages/EmptySpace.png"));
                }
                
                HeroY--; 
                
                game.getHero().moveUp(HeroX,HeroY);
                globals.getLogic().viewMatriz();
               }
               
            }
            
            
        
        //keydown
        if(key.getKeyCode()==40){
            
            if(globals.getLogic().getLogicMatriz()[HeroY+1][HeroX].getCode()==6){
                       
                matrix[HeroY+1][HeroX].setIcon(setIcon(game.getSizeImage(),"/bombermanImages/Hero.png"));
                if(globals.getLogic().getLogicMatriz()[HeroY][HeroX].getCode()==7){
                   matrix[HeroY][HeroX].setIcon(setIcon(game.getSizeImage(),"/bombermanImages/Bomb.png")); 
                }
                else{
                    matrix[HeroY][HeroX].setIcon(setIcon(game.getSizeImage(),"/bombermanImages/EmptySpace.png"));
                }
                
                HeroY++; 
                
                game.getHero().moveDown(HeroX,HeroY);
                
                globals.getLogic().viewMatriz();
                   }
            
        }
        
        if(key.getKeyCode()==32){
            Bomb bomb = new Bomb();
            bomX = HeroX;
            bomY = HeroY;
            
            System.out.println(bomX +" "+ bomY);
            matrix[bomY][bomX].setIcon(setIcon(game.getSizeImage(),"/bombermanImages/HeroBomb.png"));
            globals.getLogic().getLogicMatriz()[bomY][bomX] = bomb;
            globals.getLogic().viewMatriz();
            bomb.explodeBomb();
            
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
     public JLabel[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(JLabel[][] matrix) {
        this.matrix = matrix;
    }
    
    public int getBomX() {
        return bomX;
    }

    public void setBomX(int bomX) {
        this.bomX = bomX;
    }

    public int getBomY() {
        return bomY;
    }

    public void setBomY(int bomY) {
        this.bomY = bomY;
    }
    
    
    
    public int getHeroY() {
        return HeroY;
    }

    public void setHeroY(int HeroY) {
        this.HeroY = HeroY;
    }

    public int getHeroX() {
        return HeroX;
    }

    public void setHeroX(int HeroX) {
        this.HeroX = HeroX;
    }
    
    
    
    
        
}
