import greenfoot.*; 
import java.util.List; 

/**
 * Classe abstrata base para todos os mundos de música (fases jogáveis).
 * Gerencia funcionalidades comuns como o estado de pausa do jogo,
 * a detecção da tecla 'Escape' para pausar, e o cálculo dinâmico da largura/altura do mundo
 * com base no modo de jogo (Singleplayer ou Multiplayer).
 */

public abstract class MusicaBase extends World
{
    protected ModoJogo modo;
    protected Dificuldade dificuldade;
    protected boolean iniciado = false;
    private boolean pausado = false;
    
    public MusicaBase(ModoJogo modo, Dificuldade dificuldade)
    {
        super(getLarguraPorModo(modo), getAlturaPorModo(modo), 1);
        this.modo = modo;
        this.dificuldade = dificuldade;
        
    }
    
    public void act()
    {
        verificarPause();
    }
    
    private void verificarPause()
    {
        String key = Greenfoot.getKey();
            
        if (key != null && key.equals("escape") && !pausado)
        {
            pausado = true;
            TocadorMusica.pausarMusica();
            
            List<ControladorNotas> controladores = getObjects(ControladorNotas.class);
            if (!controladores.isEmpty()) 
            {
                controladores.get(0).pausar();
            }
            
            Greenfoot.setWorld(new MenuPause(this));
        }
    }
    
    public void retomar()
    {
        pausado = false;
        TocadorMusica.retomarMusica();
        
        List<ControladorNotas> controladores = getObjects(ControladorNotas.class);
    
        if (!controladores.isEmpty()) 
        {
            ControladorNotas controlador = controladores.get(0);
            controlador.retomar();
        }
    }
    
    private static int getLarguraPorModo(ModoJogo modo)
    {
        if (modo == modo.SINGLEPLAYER) 
            return 720;
        else
            return 1440;
    }
    
    private static int getAlturaPorModo(ModoJogo modo)
    {
        if (modo == modo.SINGLEPLAYER) 
            return 720;
        else
            return 720;
    }
    
    protected abstract void prepararModo();
    
    public void terminarMusica(Pontuacao p1, Pontuacao p2, boolean isFail)
    {
        TocadorMusica.pararMusica();
        
        int totalNotas = getTotalNotasMapeadas();
        
        EstatisticasJogo stats = new EstatisticasJogo(modo, totalNotas, p1, p2, isFail);
        
        Greenfoot.setWorld(new TelaGameOver(stats));
    }
    
    public int getTotalNotasMapeadas()
    {
        List<ControladorNotas> controladores = getObjects(ControladorNotas.class);
        if (!controladores.isEmpty())
        {
            return controladores.get(0).getTotalNotas();
        }
        
        return 0;
    }
}
