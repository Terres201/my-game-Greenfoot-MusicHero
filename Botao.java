import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
* Classe abstrata que representa os botões do jogo, que herda características de Actor
*/

public abstract class Botao extends Actor
{
    private GreenfootImage imagemNormal;
    private GreenfootImage imagemSelecionada;
    
    public Botao(String nomeImagemNormal, String nomeImagemSelecionada)
    {
        this.imagemNormal = new GreenfootImage(nomeImagemNormal);
        this.imagemSelecionada = new GreenfootImage(nomeImagemSelecionada);
        
        setImage(this.imagemNormal);
    }
    
    public void setSelecionado(boolean selecionado)
    {
        if(selecionado)
        {
            setImage(this.imagemSelecionada);
        }
        else
        {
            setImage(this.imagemNormal);
        }
    }
    
    public abstract void acaoSelecionada();
    
    public void act() {}
}
