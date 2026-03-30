import greenfoot.*;
import java.awt.Font;

public class ComboVisual extends Actor
{
    private int comboAtual = 0;
    
    public ComboVisual() 
    {
        setImage(new GreenfootImage(1, 1));
        setCombo(0);
    }
    
    public void setCombo(int novoCombo) 
    {
        if (novoCombo == comboAtual) 
            return;
        
        this.comboAtual = novoCombo;
        atualizarVisual();
    }
    
    private void atualizarVisual() 
    {
        String textoCombo = comboAtual + "X";
        int tamanhoFonte = 48;

        GreenfootImage img = new GreenfootImage(150, 162); 

        img.setColor(Color.CYAN); 
        img.setFont(new greenfoot.Font("Arial", true, false, tamanhoFonte));

        img.drawString(textoCombo, 0, tamanhoFonte);
        setImage(img);
    
    }
}
