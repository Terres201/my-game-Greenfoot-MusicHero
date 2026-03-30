import greenfoot.*;  

/**
 * Representa as notas musicais que caem na tela (os alvos que o jogador deve acertar).
 * É responsável pelo movimento de descida, pela identificação da tecla que a representa,
 * pelo rastreamento do jogador alvo (P1 ou P2) e pela detecção de que a nota foi perdida (Miss).
 */

public class Nota extends Actor 
{
    private String tecla;
    private int velocidade; 
    private int jogador;
    private boolean ativa = true;
    private int limiteZonaAcertoY = 670;
    private GreenfootImage base;

    public Nota(String tecla, int velocidade, int jogador) 
    {
        this.tecla = tecla;
        this.velocidade = velocidade;
        this.jogador = jogador;
        
        atualizarImagem(); 
    }

    private void atualizarImagem()
    {
        String nomeImagem = "Nota" + tecla + ".png";
        
        GreenfootImage imagem = null;
        imagem = new GreenfootImage(nomeImagem);
        
        base = imagem;
        setImage(base);
    }
    
    public void act() 
    {
        if (!ativa) 
            return;
            
        setLocation(getX(), getY() + velocidade);
        
        if (getY() > limiteZonaAcertoY && ativa)
        {
            java.util.List<Pontuacao> ps = getWorld().getObjects(Pontuacao.class);
             if (!ps.isEmpty())
             {
                Pontuacao pontuacao = null;
                
                if (jogador == 1 && ps.size() >= 1)
                    pontuacao = ps.get(0);
                else if (jogador == 2 && ps.size() >= 2)
                    pontuacao = ps.get(1);
                    
                if (pontuacao != null && pontuacao.getBarraFoco() > 0)
                {
                    pontuacao.registrarErro();
                }
             }
            
             desativar();
             getWorld().removeObject(this);
             return;
        }
    }
    
    public boolean estaDesativada()
    {
        return !ativa;
    }
    
    public String getTecla()
    {
        return tecla;
    }
    
    public int getJogador()
    {
        return jogador;
    }
    
    public void desativar() 
    {
        ativa = false;
    }
}
