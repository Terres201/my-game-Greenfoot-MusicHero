import greenfoot.*;  
import java.util.Arrays;

/**
 * Representa a tela de Game Over (Fim de Jogo).
 * É responsável por exibir as estatísticas finais do jogo, incluindo a pontuação total,
 * maior combo, detalhamento de acertos (Perfeito/Bom/Quase/Erro) e a classificação final (Rank).
 * Gerencia a transição de volta ao menu de seleção de músicas.
 */

public class TelaGameOver extends World
{
    private static final int Largura = 600;
    private static final int Altura = 600;
    private static final String Cenario = "TelaGameOver.png"; 
    
    private EstatisticasJogo estatisticas;

    public TelaGameOver(EstatisticasJogo stats)
    {    
        super(stats.getModo() == ModoJogo.SINGLEPLAYER ? Largura : Largura * 2, 
              stats.getModo() == ModoJogo.SINGLEPLAYER ? Altura : Altura, 1);
        
        setBackground(Cenario);
        this.estatisticas = stats;
        
        TocadorMusica.pararMusica();
        prepararTela();
    }
    
    private void prepararTela()
    {
    int xBaseP1 = 300;
    
    int xBaseP2 = 900;
        
        exibirEstatisticas(estatisticas.getPontP1(), estatisticas.getRankP1(), xBaseP1, "P1");
        
        if (estatisticas.getModo() == ModoJogo.MULTIPLAYER) {
            exibirEstatisticas(estatisticas.getPontP2(), estatisticas.getRankP2(), xBaseP2, "P2");
        }
    }
    
    private void exibirEstatisticas(Pontuacao p, String rank, int x, String titulo)
    {
        final int TAMANHO_ICONE = 35;
        final int X_OFFSET_RANK = 192; 
        final int X_OFFSET_PONTUACAO = -133; 
        final int X_OFFSET_CONTAGEM = 450; 
        final int X_OFFSET_ICONE = -150; 
        final int X_OFFSET_TITLE = 85;
        
        addObject(new IconeRank(rank), x + X_OFFSET_RANK, 106);
        
        if (estatisticas.isFail()) 
        {
            addObject(new FiguraGameOver(), x, 281);
            addObject(new Texto("Foco Esgotado!", 30, Color.RED), x + X_OFFSET_TITLE, 381);
            return; 
        }

        addObject(new Texto("Pontuação: " + p.getPontuacaoTotal(), 30, Color.WHITE), x + X_OFFSET_TITLE, 91);
        
        addObject(new Texto("Maior Combo: " + p.getMaxCombos(), 30, Color.WHITE), x + X_OFFSET_TITLE, 131);

        int yDetalhe = 190;
        addObject(new FiguraAcerto("IconePerfeito.png"), x + X_OFFSET_ICONE, yDetalhe);
        addObject(new Texto("x" + p.getAcertosPerfeitos(), TAMANHO_ICONE, Color.WHITE), x + X_OFFSET_CONTAGEM, yDetalhe);

        yDetalhe += 110;
        addObject(new FiguraAcerto("IconeBom.png"), x + X_OFFSET_ICONE, yDetalhe);
        addObject(new Texto("x" + p.getAcertosBons(), TAMANHO_ICONE, Color.WHITE),x + X_OFFSET_CONTAGEM, yDetalhe);

        yDetalhe += 110;
        addObject(new FiguraAcerto("IconeQuase.png"), x + X_OFFSET_ICONE, yDetalhe);
        addObject(new Texto("x" + p.getAcertosQuase(), TAMANHO_ICONE, Color.WHITE), x + X_OFFSET_CONTAGEM, yDetalhe);

        yDetalhe += 110;
        addObject(new FiguraAcerto("IconeErrou.png"), x + X_OFFSET_ICONE, yDetalhe);
        addObject(new Texto("x" + p.getErros(), TAMANHO_ICONE, Color.WHITE), x + X_OFFSET_CONTAGEM, yDetalhe);
        
        addObject(new FiguraEsc(), 466, 528);
    }
    
    public void act()
    {
        String key = Greenfoot.getKey();
        if (key != null && key.equals("escape"))
        {
            acionarVoltar();
        }
    }
    
    private void acionarVoltar()
    {
        TocadorMusica.tocarEfeito("SomBack.mp3"); 
        
        Greenfoot.setWorld(new Loading(new MenuMusicas()));
    }
}
