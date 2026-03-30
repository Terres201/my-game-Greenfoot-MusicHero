import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BotaoBack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BotaoVoltarMusicas extends Botao
{
    public BotaoVoltarMusicas()
    {
        super("BotaoVoltar.png","BotaoVoltarSele.png");
    }
    
    @Override
    public void acaoSelecionada()
    {
        Greenfoot.setWorld(new Loading(new MenuMusicas()));
    }
}
