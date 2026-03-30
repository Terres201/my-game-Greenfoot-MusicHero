import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Figura extends Actor
{
    private GreenfootImage nomeImagem;
    
    public Figura(String urlImagem)
    {
        this.nomeImagem = new GreenfootImage(urlImagem);
        setImage(this.nomeImagem);
    }
}
