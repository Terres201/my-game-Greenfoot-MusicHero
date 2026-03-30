import greenfoot.*;  

public class BotaoMusica extends Actor
{
    private GreenfootImage imagemNormal;
    private GreenfootImage imagemSelecionada;
    private int idMusica;
    private String nomeMusica;
    
    public BotaoMusica(String nomeMusica, String nomeImagemNormal, String nomeImagemSelecionada, int id)
    {
        this.idMusica = id;
        this.imagemNormal = new GreenfootImage(nomeImagemNormal);
        this.imagemSelecionada = new GreenfootImage(nomeImagemSelecionada);
        this.nomeMusica = nomeMusica;
        setImage(this.imagemNormal);
    }
    
    public String getNomeMusica()
    {
        return nomeMusica;
    }
    
    public void setSelecionado(boolean selecionado)
    {
        if (selecionado)
        {
            setImage(this.imagemSelecionada);
        }
        else
        {
            setImage(this.imagemNormal);
        }
    }
    
    public void acaoSelecionada(ModoJogo modo, Dificuldade dificuldade)
    {
        World proxima;
        
        switch (idMusica)
        {
            case 1: proxima = new MusicaA(modo, dificuldade); break;
            case 2: proxima = new MusicaB(modo, dificuldade); break;
            case 3: proxima = new MusicaC(modo, dificuldade); break;
            default: proxima = new MusicaA(modo, dificuldade); break;
        }
        
        Greenfoot.setWorld(new Loading(proxima));
    }
    

}