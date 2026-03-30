import greenfoot.*; 

/**
 * Ator visual usado na tela de seleção de músicas para exibir o modo de jogo
 * atualmente selecionado (Singleplayer ou Multiplayer).
 */

public class IconeModo extends Actor
{
    private final GreenfootImage imagemSingle = new GreenfootImage("ModoJogoS.png");
    private final GreenfootImage imagemMulti = new GreenfootImage("ModoJogoM.png");
    
    public IconeModo(ModoJogo modoInicial)
    {   
        atualizarVisual(modoInicial);
    }
    
    public void atualizarVisual(ModoJogo modo)
    {
        if (modo == ModoJogo.SINGLEPLAYER)
        {
            setImage(imagemSingle);
        }
        else
        {
            setImage(imagemMulti);
        }
    }
}
