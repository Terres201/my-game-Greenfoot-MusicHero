import greenfoot.*;
import java.util.List;
import java.util.Arrays;  

/**
 * Representa o menu de seleção de músicas do jogo.
 * Permite ao jogador escolher a música, modo de jogo (Single/Multiplayer) e dificuldade (Normal/Difícil).
 * Também gerencia a pré-visualização (preview) da música selecionada em loop.
 */

public class MenuMusicas extends World
{
    private static final int Largura_menu = 600;
    private static final int Altura_menu = 600;
    private static final String Cenario = "MenuMusicas.png";
    
    private List<BotaoMusica> botoes;
    private int indiceSelecionado = 0;
    private int tempoTecla = 0;
    private boolean voltando = false;
    
    private ModoJogo modoAtual = ModoJogo.SINGLEPLAYER;
    private Dificuldade dificuldadeAtual = Dificuldade.NORMAL;
    
    private IconeModo iconeModo;
    private IconeDificuldade iconeDificuldade;
    private BotaoVoltarMenu botaoVoltar;
    
    private FiguraEsc figuraEsc;
    private FiguraDificuldade figuraDificuldade;
    private FiguraModo figuraModo;
    
    private GreenfootSound trechoMusicaAtual = null; 
    private String nomeMusicaPreview = null;   
    private boolean musicaInicialTocada = false;

    
    public MenuMusicas()
    {    
        super(Largura_menu, Altura_menu, 1);
        setBackground(Cenario);
        prepararMenu();
    }
    
    public void prepararMenu()
    {    
        botoes = Arrays.asList(
            new BotaoMusica("MusicaA", "BotaoMusicaA.png", "BotaoMusicaASele.png", 1),
            new BotaoMusica("MusicaB", "BotaoMusicaB.png", "BotaoMusicaBSele.png", 2),
            new BotaoMusica("MusicaC", "BotaoMusicaC.png", "BotaoMusicaCSele.png", 3)
        );
        
        int centrox = 451;
        int yinicial = 100;
        int distancia = 100;
        
        for (int i = 0; i < botoes.size(); i++)
        {
            addObject(botoes.get(i), centrox, yinicial + i * distancia);
        }
        
        figuraEsc = new FiguraEsc();
        addObject(figuraEsc, 145, 45);
        
        figuraDificuldade = new FiguraDificuldade();
        addObject(figuraDificuldade, 133, 260);
        
        figuraModo = new FiguraModo();
        addObject(figuraModo, 135, 581);
        
        botaoVoltar = new BotaoVoltarMenu();
        addObject(botaoVoltar, 130, 100);
        
        iconeModo = new IconeModo(modoAtual);
        addObject(iconeModo, 105, 450);
        
        iconeDificuldade = new IconeDificuldade(dificuldadeAtual);
        addObject(iconeDificuldade, 135, 310);
        
        atualizarVisual();
    }
    
    public void act()
    {
        if (!musicaInicialTocada)
        {
            tocarTrechoMusicaAtual();
            musicaInicialTocada = true;
        }
        
        if (tempoTecla > 0) 
        {
            tempoTecla--;
        }
        verificarInputTeclado();
    }
    
    public void tocarTrechoMusicaAtual()
    {
        String nomeBaseMusica = botoes.get(indiceSelecionado).getNomeMusica();
        String nomeArquivo = "Trecho" + nomeBaseMusica + ".mp3";
        
        TocadorMusica.tocarMusica(nomeArquivo);
        nomeMusicaPreview = nomeArquivo;
    }
    
    public void pararTrechoMusicaAtual()
    {
        TocadorMusica.pararMusica(); 
        nomeMusicaPreview = null;
    }
    
    private void verificarInputTeclado()
    {
        int total = botoes.size();
        int anterior = indiceSelecionado;
        
        String key = Greenfoot.getKey();
        if (key == null) return;
        
        if (Greenfoot.isKeyDown("down") && tempoTecla == 0)
        {
            TocadorMusica.pararEfeito();
            indiceSelecionado = (indiceSelecionado + 1) % total;
            tempoTecla = 20;
            TocadorMusica.tocarEfeito("SomNav.mp3");
        }
        
        if (Greenfoot.isKeyDown("up") && tempoTecla == 0)
        {
            TocadorMusica.pararEfeito();
            indiceSelecionado = (indiceSelecionado - 1 + botoes.size()) % total;
            tempoTecla = 20;
            TocadorMusica.tocarEfeito("SomNav.mp3");
        }
        
        if (key.equals("tab"))
        {
            TocadorMusica.pararEfeito();
            TocadorMusica.tocarEfeito("SomNav.mp3");
            Greenfoot.delay(20);
            modoAtual = (modoAtual == ModoJogo.SINGLEPLAYER) ? ModoJogo.MULTIPLAYER : ModoJogo.SINGLEPLAYER;
            iconeModo.atualizarVisual(modoAtual);
            tempoTecla = 20;
        }
        
        if (key.equals("space"))
        {
            TocadorMusica.pararEfeito();
            TocadorMusica.tocarEfeito("SomNav.mp3");
            Greenfoot.delay(10);
            dificuldadeAtual = (dificuldadeAtual == Dificuldade.NORMAL) ? Dificuldade.DIFICIL : Dificuldade.NORMAL;
            iconeDificuldade.atualizarVisual(dificuldadeAtual);
            tempoTecla = 20;
        }
        
        if (key.equals("escape"))
        {
            TocadorMusica.pararEfeito();
            TocadorMusica.tocarEfeito("SomBack.mp3");
            acionarVoltar();
            return;
        }
        if (key.equals("enter"))
        {
            pararTrechoMusicaAtual();
            TocadorMusica.pararEfeito();
            BotaoMusica musica = botoes.get(indiceSelecionado);
            TocadorMusica.tocarEfeito("SomEnter.mp3");
            Greenfoot.delay(20);
            musica.acaoSelecionada(modoAtual, dificuldadeAtual);
        }
        
        if (anterior != indiceSelecionado)
        {
            Greenfoot.delay(5);
            atualizarVisual();
            tocarTrechoMusicaAtual();
        }
    }
    
    private void atualizarVisual()
    {
        for (int i = 0; i < botoes.size(); i++)
        {
            boolean estaSelecionado = (i == indiceSelecionado);
            botoes.get(i).setSelecionado(estaSelecionado);
            tocarTrechoMusicaAtual();
        }
    }
    
    private void acionarVoltar()
    {
        voltando = true;
        
        pararTrechoMusicaAtual();
        botaoVoltar.setSelecionado(true);
        
        Greenfoot.delay(30);
        botaoVoltar.setSelecionado(false);
        
        Greenfoot.setWorld(new Loading(new Menu()));
    }
}

