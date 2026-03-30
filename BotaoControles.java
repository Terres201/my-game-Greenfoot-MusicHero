import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class BotaoControles extends Botao
{
    private static final int Fps = 60;
    
    public BotaoControles()
    {
        super("BotaoControles.png","BotaoControlesSele.png");
    }
    
    @Override
    public void acaoSelecionada()
    {
        Greenfoot.setWorld(new MenuControles());
    }
}
