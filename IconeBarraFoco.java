import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ator visual que exibe o estado atual da barra de foco (energia) de um jogador.
 * A imagem se atualiza em incrementos de 10 pontos (ex: 100%, 90%, 80%),
 * representando a energia restante.
 */

public class IconeBarraFoco extends Actor
{
    private String tagJogador;
    private int valorAtual = -1;
    
    public IconeBarraFoco(String tag)
    {
        this.tagJogador = tag;
        atualizarImagem(100);
    }
    
    public void atualizarImagem(int novoValor)
    {
        novoValor = Math.min(100, Math.max(0, novoValor));
        
        int faixa = (novoValor / 10) * 10;
        if (novoValor == 100) 
        {
            faixa = 100;
        }
        
        if (faixa == valorAtual) 
        {
            return;
        }
        
        String nomeArquivo = "BarraFoco" + faixa + ".png";
        
        setImage(nomeArquivo);
        this.valorAtual = faixa;
    }
}
