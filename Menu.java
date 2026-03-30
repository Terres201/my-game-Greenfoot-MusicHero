import greenfoot.*;
import java.util.List;
import java.util.Arrays;  

/**
 * Representa o menu principal do jogo, onde o jogador pode iniciar o jogo (Start) ou acessar o tutorial.
 * É responsável por gerenciar a seleção de botões através do input do teclado e iniciar a música de fundo do menu.
 */

public class Menu extends World
{
    private static final int Largura_menu = 600;
    private static final int Altura_menu = 600;
    private static final String Cenario = "MenuMusicHero.png";
    
    private List<Botao> botoes;
    private int indiceSelecionado = 0;
    private int tempoTecla = 0;

    private int FPS = 10;
    
    public Menu()
    {    
        super(Largura_menu, Altura_menu, 1);
        setBackground(Cenario);
        prepararMenu();
    }
    
    public void prepararMenu()
    {    
        botoes = Arrays.asList(
            new BotaoStart(),
            new BotaoTutorial()
        );
        
        int pbotaox = 164;
        int pbotaoSy = 361;
        int pbotaoTy = 463;
        
        addObject(botoes.get(0), pbotaox, pbotaoSy);
        addObject(botoes.get(1), pbotaox, pbotaoTy);
        
        atualizarVisual();
    }
    
    public void act()
    {
        TocadorMusica.atualizarMusicaMundo(this);
        if (tempoTecla > 0) 
        {
            tempoTecla--;
        }
        verificarInputTeclado();
    }
    
    private void verificarInputTeclado()
    {
        int anterior = indiceSelecionado;
        
        if (Greenfoot.isKeyDown("down") && tempoTecla == 0)
        {
            TocadorMusica.pararEfeito();
            indiceSelecionado = (indiceSelecionado + 1) % botoes.size();
            tempoTecla = 20;
            TocadorMusica.tocarEfeito("SomNav.mp3");
        }
        
        if (Greenfoot.isKeyDown("up") && tempoTecla == 0)
        {
            TocadorMusica.pararEfeito();
            indiceSelecionado = (indiceSelecionado - 1 + botoes.size()) % botoes.size();
            tempoTecla = 20;
            TocadorMusica.tocarEfeito("SomNav.mp3");
        }
        
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            TocadorMusica.pararEfeito();
            TocadorMusica.tocarEfeito("SomEnter.mp3");
            Greenfoot.delay(FPS);
            botoes.get(indiceSelecionado).acaoSelecionada();
        }
        
        if (anterior != indiceSelecionado)
        {
            Greenfoot.delay(FPS);
            atualizarVisual();
        }
    }
    
    private void atualizarVisual()
    {
        for (int i = 0; i < botoes.size(); i++)
        {
            boolean estaSelecionado = (i == indiceSelecionado);
            botoes.get(i).setSelecionado(estaSelecionado);
        }
    }
}
