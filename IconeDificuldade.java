import greenfoot.*;  

/**
 * Ator visual usado na tela de seleção de músicas para exibir a dificuldade
 * atualmente selecionada (Normal ou Difícil).
 */

public class IconeDificuldade extends Actor
{
    private final GreenfootImage imagemNormal = new GreenfootImage("DificuldadeNormal.png");
    private final GreenfootImage imagemDificil = new GreenfootImage("DificuldadeHard.png");
    
    public IconeDificuldade(Dificuldade difInicial)
    {
        atualizarVisual(difInicial);
    }
    
    public void atualizarVisual(Dificuldade dif)
    {
        if (dif == Dificuldade.NORMAL)
        {
            setImage(imagemNormal);
        }
        else
        {
            setImage(imagemDificil);
        }
    }
}
