import greenfoot.*;  

/**
 * Ator lógico de dados (não necessariamente visível) responsável por rastrear todas as estatísticas
 * de um único jogador: pontuação total, barra de foco (energia), combo, acertos e erros.
 * Notifica os atores visuais (IconeBarraFoco e ComboVisual) sobre mudanças de estado.
 */

public class Pontuacao extends Actor
{
    private int pontos = 0;    
    private int barraFoco = 100;
    private int combo = 0;
    private String tag;
    
    private int maxCombo = 0;
    private int acertosPerfeitos = 0;
    private int acertosBons = 0;
    private int acertosQuase = 0;
    private int erros = 0;
    
    private IconeBarraFoco barraVisual;
    private ComboVisual comboVisual;
    private boolean falhou = false;
    
    public Pontuacao(String tag, IconeBarraFoco barraVisual, ComboVisual comboVisual)
    {
        this.tag = tag;
        this.barraVisual = barraVisual;
        this.comboVisual = comboVisual;
        atualizarImagem();
    }
    
    public void registrarAcertoPerfeito(int jogador)
    {
        pontos += 150;
        combo ++;
        acertosPerfeitos++;
        maxCombo = Math.max(maxCombo, combo);
        barraFoco = Math.min(100, barraFoco + 5);
        atualizarBarraVisual();
        atualizarComboVisual();
    }
    
    public void registrarAcertoBom(int jogador)
    {
        pontos += 100;
        combo++;
        acertosBons++;
        maxCombo = Math.max(maxCombo, combo);
        barraFoco = Math.min(100, barraFoco +3);
        atualizarBarraVisual();
        atualizarComboVisual();
    }
    
    public void registrarAcertoQuase(int jogador)
    {
        pontos += 50;
        maxCombo = Math.max(maxCombo, combo);
        combo = 0;
        acertosQuase++;
        barraFoco = Math.max(0, barraFoco - 3);
        atualizarBarraVisual();
        atualizarComboVisual();
        
        if (barraFoco <= 0) 
        {
            falhou = true;
        }
    }
    
    public void registrarErro()
    {
        barraFoco = Math.max(0, barraFoco - 8);
        maxCombo = Math.max(maxCombo, combo);
        combo = 0;
        erros++;
        atualizarBarraVisual();
        atualizarComboVisual();
        
        if (barraFoco <= 0) 
        {
            falhou = true;
        }
    }
    
    private void atualizarBarraVisual()
    {
        if (barraVisual != null) 
        {
            barraVisual.atualizarImagem(barraFoco);
        }
    }
    
    private void atualizarComboVisual()
    {
        if (comboVisual != null) 
        {
            comboVisual.setCombo(combo);
        }
    }
    
    public void act()
    {
        atualizarImagem();
    }
    
    private void atualizarImagem()
    {
        setImage(new GreenfootImage(1, 1));
    }
    
    public boolean isFalhou() 
    {
        return falhou;
    }
    
    public int getPontuacaoTotal()
    {
        return pontos;
    }
    
    public int getMaxCombos()
    {
        return maxCombo;
    }
    
    public int getAcertosPerfeitos()
    {
        return acertosPerfeitos;
    }
    
    public int getAcertosBons()
    {
        return acertosBons;
    }
    
    public int getAcertosQuase()
    {
        return acertosQuase;
    }
    
    public int getErros()
    {
        return erros;
    }
    
    public int getBarraFoco()
    {
        return barraFoco;
    }
}
