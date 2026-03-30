import greenfoot.*;  

/**
 * Representa o ícone de feedback visual exibido temporariamente durante o jogo
 * para indicar a qualidade de um acerto (Perfeito, Bom, Quase ou Erro).
 * No Game Over, é usado estaticamente para mostrar a contagem final.
 * @author [Seu Nome]
 * @version 1.0
 */

public class IconeAcerto extends Actor
{
   private int tempo = 40; 

    public IconeAcerto(String nomeImagem) 
    {
        GreenfootImage img = new GreenfootImage(nomeImagem);
        setImage(img);
    }

    public void act() 
    {
        tempo--;
        if (tempo <= 0) 
        {
            getWorld().removeObject(this);
        }
    }
}
