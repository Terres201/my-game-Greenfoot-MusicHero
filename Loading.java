import greenfoot.*;  

/**
 * Representa a tela de carregamento (Loading) do jogo.
 * Esta tela é exibida durante a transição entre mundos para dar tempo de carregar
 * recursos e exibe um efeito visual (ícone de vinil) com som de fundo.
 * Após a duração definida, ela transiciona para a próxima tela.
 */

public class Loading extends World
{   
    private static final int Largura_menu = 600;
    private static final int Altura_menu = 600;
    private static final String Cenario = "Loading.png";
    
    private IconeVinil iconeVinil;
    private int tempo = 0;
    private int duracao = 130;
    private World proximaTela;
    
    private GreenfootSound somVinil = new GreenfootSound("SomVinil.mp3");
    
    public Loading(World tela)
    {    
        super(Largura_menu, Altura_menu, 1); 
        setBackground(Cenario);
        iconeVinil = new IconeVinil();
        addObject(iconeVinil, getWidth()/2, getHeight()/2);
        somVinil.playLoop();
        
        proximaTela = tela;
    }
    
    public void act()
    {
        TocadorMusica.atualizarMusicaMundo(this);
        tempo++;
        if (tempo == duracao)
        {
            somVinil.stop();
            Greenfoot.setWorld(proximaTela);
        }
    }
}
