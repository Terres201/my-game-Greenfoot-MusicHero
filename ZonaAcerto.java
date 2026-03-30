import greenfoot.*;  

/**
 * Representa a área na parte inferior da tela onde o jogador deve pressionar a tecla
 * correspondente no momento em que a Nota passa. Usada pelo ControladorNotas
 * para determinar a precisão do acerto.
 */

public class ZonaAcerto extends Actor 
{
    private String tecla;
    private int jogador;
    
    private GreenfootImage imagemNormal;
    private GreenfootImage imagemAtiva;
    
    private long tempoUltimoFeedback = 0;
    private final int DURACAO_FEEDBACK = 90;
    
    
    public ZonaAcerto(String tecla, int jogador) 
    {
        this.tecla = tecla;
        this.jogador = jogador;
        
        atualizarImagem();
        imagemAtiva = carregarImagemAtiva();
    }
    
    private void atualizarImagem()
    {
        String nomeImagem = "Zona" + tecla + ".png";
        
        imagemNormal = new GreenfootImage(nomeImagem);
    
        setImage(imagemNormal);
    }
    
    public void act() 
    {
        if (System.currentTimeMillis() - tempoUltimoFeedback > DURACAO_FEEDBACK)
        {
            setImage(imagemNormal);
        }
    }
    
    public void mostrarFeedbackVisual()
    {
        setImage(imagemAtiva);
        tempoUltimoFeedback = System.currentTimeMillis();
    }
    
    public String getTecla()
    {
        return tecla;
    }
    
    public int getJogador()
    {
        return jogador;
    }
    
    private GreenfootImage carregarImagemAtiva()
    {
        String nomeImagemAtiva = "Zona" + tecla + "Ativa.png";
        return new GreenfootImage(nomeImagemAtiva);
    }
}
