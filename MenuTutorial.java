import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;

/**
 * Representa a tela de tutorial do jogo.
 * Exibe instruções sobre como jogar, a pontuação, e os comandos necessários.
 * Permite ao jogador retornar ao Menu Principal.
 */

public class MenuTutorial extends World
{
    private static final int Largura_menu = 600;
    private static final int Altura_menu = 600;
    private static final String Cenario = "InfoTutorial.png";
    
    private List<Botao> botoes;
    private int indiceSelecionado = 0;
    private int tempoTecla = 10;
    
    private int FPS = 10;
    
    public MenuTutorial()
    {    
        super(Largura_menu, Altura_menu, 1);
        setBackground(Cenario);
        prepararMenu();
    }
    public void prepararMenu()
    {
        botoes = Arrays.asList(
        new BotaoControles(),
        new BotaoVoltarMenu()
        );
        
        int pbotaoy = 551;
        int pbotaoCx = 138;
        int pbotaoVx = 462;
    
        addObject(botoes.get(0), pbotaoCx, pbotaoy);
        addObject(botoes.get(1), pbotaoVx, pbotaoy);
        
        atualizarVisual();
    }
    
    public void act()
    {
        if (tempoTecla > 0) tempoTecla--;
        {
            verificarInputTeclado();
        }
    }
    
    private void verificarInputTeclado()
    {
        int anterior = indiceSelecionado;
        
        if (Greenfoot.isKeyDown("left") && tempoTecla == 0)
        {
            TocadorMusica.pararEfeito();
            indiceSelecionado = (indiceSelecionado + 1) % botoes.size();
            TocadorMusica.tocarEfeito("SomNav.mp3");
            tempoTecla = 20;
        }
        
        if (Greenfoot.isKeyDown("right") && tempoTecla == 0)
        {
            TocadorMusica.pararEfeito();
            indiceSelecionado = (indiceSelecionado - 1 + botoes.size()) % botoes.size();
            TocadorMusica.tocarEfeito("SomNav.mp3");
            tempoTecla = 20;
        }
        
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            if (indiceSelecionado == 1)
            {
                TocadorMusica.pararEfeito();
                TocadorMusica.tocarEfeito("SomBack.mp3");
            }
            else
            {
            TocadorMusica.pararEfeito();
            TocadorMusica.tocarEfeito("SomEnter.mp3");
            }
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
