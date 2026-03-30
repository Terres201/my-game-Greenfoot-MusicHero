
/**
 * Classe abstrata base para os modos de jogo (Modo).
 * Contém a infraestrutura básica necessária para qualquer fase do jogo,
 * incluindo referências ao mundo da música, às zonas de acerto dos jogadores (P1 e P2),
 * o controlador de notas e os objetos de pontuação.
 * Gerencia a preparação de zonas de acerto e placares de pontuação.
 */

public abstract class ModoBase  
{
    protected MusicaBase mundo;
    protected ZonaAcerto[] zonasP1 = new ZonaAcerto [4];
    protected ZonaAcerto[] zonasP2 = null;
    protected ControladorNotas controlador;
    protected Pontuacao pontuacaoP1;
    protected Pontuacao pontuacaoP2;
    protected String musicaNome;
    
    public ModoBase (MusicaBase mundo, String musicaNome)
    {
        this.mundo = mundo;
        this.musicaNome = musicaNome;
        
        prepararZonasBase();
        prepararPontuacaoBase();
    }
    
    public void prepararZonasBase()
    {
        int[] posX = {250, 380, 510, 640};
        String[] teclas = {"a", "w", "s", "d"};
        for (int i = 0; i < 4; i++)
        {
            zonasP1[i] = new ZonaAcerto(teclas[i], 1);
            mundo.addObject(zonasP1[i], posX[i], 620);
        }
    }
    
    protected void prepararPontuacaoBase()
    {
        IconeBarraFoco barraP1 = new IconeBarraFoco("P1");
        mundo.addObject(barraP1, 66, 387);
        
        ComboVisual comboP1 = new ComboVisual();
        mundo.addObject(comboP1, 110, 220);
        
        pontuacaoP1 = new Pontuacao("P1", barraP1, comboP1);
        mundo.addObject(pontuacaoP1,1,1);
    }
    
    protected void prepararZonasParaMultiplayer()
    {
        zonasP2 = new ZonaAcerto[4];
        int[] posX2 = {800, 930, 1060, 1190};
        String[] teclasP2 = {"left", "up", "down", "right"};
        for (int i = 0; i < 4; i++)
        {
            zonasP2[i] = new ZonaAcerto(teclasP2[i], 2);
            mundo.addObject(zonasP2[i], posX2[i], 620);
        }
        IconeBarraFoco barraP2 = new IconeBarraFoco("P2");
        mundo.addObject(barraP2, 1374, 387);
        
        ComboVisual comboP2 = new ComboVisual();
        mundo.addObject(comboP2, 1416, 215); 
        
        pontuacaoP2 = new Pontuacao("P2", barraP2, comboP2);
        mundo.addObject(pontuacaoP2,1,1);
    }
    
    public abstract void iniciar();
    
}
