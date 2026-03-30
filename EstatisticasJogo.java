import greenfoot.*;

/**
 * Classe de modelo de dados responsável por armazenar e processar as estatísticas finais de uma rodada.
 * Utiliza a pontuação bruta, a contagem de acertos e o total de notas da música 
 * para calcular a classificação final (Rank S, A, B, C, D, ou F) de cada jogador.
 */

public class EstatisticasJogo  
{
    private Pontuacao pontP1;
    private Pontuacao pontP2;
    private int totalNotas;
    private ModoJogo modo;
    private boolean isFail;
    
    private String rankP1;
    private String rankP2;

    public EstatisticasJogo(ModoJogo modo, int totalNotas, Pontuacao p1, Pontuacao p2, boolean isFail)
    {
        this.modo = modo;
        this.totalNotas = totalNotas;
        this.pontP1 = p1;
        this.pontP2 = p2;
        this.isFail = isFail;
        
        if (isFail)
        {
            this.rankP1 = "F";
            this.rankP2 = "F";
        }
        else
        {
            this.rankP1 = calcularRank(p1);
            if (modo == ModoJogo.MULTIPLAYER && p2 != null) {
                this.rankP2 = calcularRank(p2);
            }
        }
    }
    
    public boolean isFail()
    {
        return isFail;
    }
    
    private String calcularRank(Pontuacao p)
    {
        int acertos = p.getAcertosPerfeitos() + p.getAcertosBons() + p.getAcertosQuase();
        double percentualAcertos = (double)acertos / totalNotas;
        
        int pontuacaoMaxima = totalNotas * 150;
        double percentualPontuacao = (double)p.getPontuacaoTotal() / pontuacaoMaxima;
        
        if (percentualPontuacao >= 0.98) 
            return "S";
        if (percentualPontuacao >= 0.90) 
            return "A";
        if (percentualPontuacao >= 0.85) 
            return "B";
        if (percentualPontuacao >= 0.70) 
            return "C";
        if (percentualPontuacao >= 0.50) 
            return "D";
        
        return "F"; 
    }
    
    public Pontuacao getPontP1() 
    { 
        return pontP1; 
    }
    
    public Pontuacao getPontP2() 
    { 
        return pontP2; 
    }
    
    public String getRankP1() 
    { 
        return rankP1; 
    }
    
    public String getRankP2() 
    { 
        return rankP2; 
    }
    
    public ModoJogo getModo() 
    { 
        return modo; 
    }
}
