import greenfoot.*;  
import java.util.*;

public class ControladorNotas extends Actor 
{
    private List<EntradaNota> mapaNotas;
    private Map<String, Long> ultimoProcessamento = new HashMap<>();
    private long inicio;
    private int indice = 0;
    private int velocidade;
    
    private ZonaAcerto[] zonasP1;
    private ZonaAcerto[] zonasP2;
    private Pontuacao pontP1;
    private Pontuacao pontP2;
    
    private long tempoPausa = 0;
    private long tempoAcumulado = 0;
    private long tempoInicialPausa = 0;
    private long tempoFinalMusica;
    private static final long COOLDOWN_MS = 200;
    private int totalNotasOriginal;
    
    private boolean gameOverIconP1Spawned = false;
    private boolean gameOverIconP2Spawned = false;
    
    public static class EntradaNota
    {
        public int tempo;
        public String tecla;
        public int posX;
        public int jogador;
        
        public EntradaNota(int tempo, String tecla, int jogador)
        {
            this.tempo = tempo;
            this.tecla = tecla;
            this.jogador = jogador;
        }
    }

    public ControladorNotas(List<EntradaNota> mapa, int velocidade, ZonaAcerto[] zonasP1, ZonaAcerto[] zonasP2, Pontuacao p1, Pontuacao p2, long tempoFinalMusica, int totalNotas) 
    {
        this.mapaNotas = new ArrayList<>(mapa);
        this.velocidade = velocidade;
        this.zonasP1 = zonasP1;
        this.zonasP2 = zonasP2;
        this.pontP1 = p1;
        this.pontP2 = p2;
        this.tempoFinalMusica = tempoFinalMusica;
        this.totalNotasOriginal = totalNotas;
    }

    @Override
    protected void addedToWorld(World w)
    {
        setImage(new GreenfootImage(1, 1));
        inicio = System.currentTimeMillis();
        String[] todasTeclas = {"a","w","s","d", "left", "up", "down","right"};
        for (String t : todasTeclas) 
        {
            ultimoProcessamento.put(t, 0L);
        }
        tempoPausa = 0;
        tempoAcumulado = 0;
    }
    
    public void act()
    {
        if (TocadorMusica.estaPausada())
            return;
        
        long tempoDecorrido = System.currentTimeMillis() - inicio;
        
        boolean failP1 = pontP1.getBarraFoco() <= 0;
        boolean failP2 = (pontP2 != null && pontP2.getBarraFoco() <= 0);
        
        if (failP1 && !gameOverIconP1Spawned) { 
            getWorld().addObject(new FiguraGameOver(), 400, 300); 
            gameOverIconP1Spawned = true; 
        }

        if (pontP2 != null && failP2 && !gameOverIconP2Spawned) {
            getWorld().addObject(new FiguraGameOver(), 930, 300);
            gameOverIconP2Spawned = true; 
        }
        
        if (failP1 && (pontP2 == null || failP2)) 
        {
            ((MusicaBase)getWorld()).terminarMusica(pontP1, pontP2, true);
            return;
        }
        
        for (Iterator<EntradaNota> it = mapaNotas.iterator(); it.hasNext(); )
        {
            EntradaNota e = it.next();
    
            boolean jogadorFalhou = (e.jogador == 1 && failP1) || (e.jogador == 2 && pontP2 != null && failP2);
            boolean p1Perdeu = pontP1.isFalhou();
            boolean p2Perdeu = (pontP2 != null && pontP2.isFalhou());
            
            if (p1Perdeu && (pontP2 == null || p2Perdeu)) 
            {
                ((MusicaBase)getWorld()).terminarMusica(pontP1, pontP2, true);
                return;
            }
                                    
            if (tempoDecorrido >= e.tempo)
            {
                if (jogadorFalhou)
                {
                    it.remove(); 
                    continue; 
                }
                
                ZonaAcerto[] zonas = (e.jogador == 1) ? zonasP1 : zonasP2;
                int posX = e.posX;
        
                for (ZonaAcerto z : zonas)
                {
                    if (z.getTecla().equals(e.tecla))
                    {
                        posX = z.getX();
                        break;
                    }
                }
        
                Nota nota = new Nota(e.tecla, velocidade, e.jogador);
                getWorld().addObject(nota, posX, 0);
                it.remove();
            }
            else
            {
                break; 
            }
            
        }
        
        if (mapaNotas.isEmpty())
            {
                if (tempoDecorrido > tempoFinalMusica + 100)
                {
                    ((MusicaBase)getWorld()).terminarMusica(pontP1, pontP2, false);
                    return;
                }
            }
            
        if (!pontP1.isFalhou()) 
        {
            checarTeclasP1();
        }
    
        if (zonasP2 != null && !pontP2.isFalhou()) 
        {
            checarTeclasP2();
        }
    }
        
    public void pausar()
    {
        tempoInicialPausa = System.currentTimeMillis();
    }
    
    public void retomar()
    {
        long duracaoPausa = System.currentTimeMillis() - tempoInicialPausa;
        inicio += duracaoPausa;
        tempoInicialPausa = 0;
    }
        
    private void checarTeclasP1()
    {
        String[] teclas = {"a", "w", "s", "d"};
        long tempoAtual = System.currentTimeMillis();
        
        for (String t: teclas)
        {
            if(Greenfoot.isKeyDown(t)) 
            {
                long ultimoTempo = ultimoProcessamento.getOrDefault(t, 0L);
                
                if (tempoAtual - ultimoTempo >= COOLDOWN_MS)
                {
                    processarAcerto(t, 1);
                    ultimoProcessamento.put(t, tempoAtual);
                }
            }
        }
    }
    
    private void checarTeclasP2()
    {
        String[] teclas = {"left", "up", "down", "right"};
        long tempoAtual = System.currentTimeMillis();
        
        for (String t: teclas)
        {
            if(Greenfoot.isKeyDown(t)) 
            {
                long ultimoTempo = ultimoProcessamento.getOrDefault(t, 0L);
                
                if (tempoAtual - ultimoTempo >= COOLDOWN_MS)
                {
                    processarAcerto(t, 2);
                    ultimoProcessamento.put(t, tempoAtual);
                }
            }
        }
    }
    
    private void processarAcerto(String tecla, int jogador)
    {
        List<Nota> notas = getWorld().getObjects(Nota.class);
        Nota alvo = null;
        int menorDist = Integer.MAX_VALUE;
        int zonaY = 540;
        
        ZonaAcerto[] zonas = (jogador == 1) ? zonasP1 : zonasP2;
        if (zonas!= null)
        {
            for (ZonaAcerto z : zonas)
            {
                if(z.getTecla().equals(tecla))
                {
                    zonaY = z.getY();
                    break;
                }
            }
        }
        
        for (Nota n : notas) 
        {
            if (!n.getTecla().equals(tecla)) 
                continue;
            if (n.getJogador() != jogador)
                continue;
            if (n.estaDesativada())
                continue;
                
            int dist = Math.abs(n.getY() - zonaY);
            
            if (dist < menorDist && dist <= 100)
            {
                menorDist = dist;
                alvo = n;
            }
        }
        
        if  (alvo == null)
            return;
        
        if (menorDist <= 10)
        {
            registrarPontuacao(jogador, "IconePerfeito.png", 150);
        }
        
        else if (menorDist <= 25)
        {
            registrarPontuacao(jogador, "IconeBom.png", 100);
        }
        
        else if (menorDist <= 40)
        {
           registrarPontuacao(jogador, "IconeQuase.png", 50);
        }
          
        else
        {
            registrarPontuacao(jogador, "IconeErrou.png", 0);
        }
        
        for (ZonaAcerto z : zonas)
        {
            if (z.getTecla().equals(tecla))
            {
                z.mostrarFeedbackVisual();
                break;
            }
        }
        
        alvo.desativar();
        getWorld().removeObject(alvo);
    }
    
    private void registrarPontuacao(int jogador, String nomeImagem, int valor)
    {
        int xFeedback = (jogador == 1) ? 98 : 1340;
        int yFeedback = (jogador == 1) ? 640 : 630;
            
        if (valor == 150)
        {
            if (jogador == 1) 
                pontP1.registrarAcertoPerfeito(1);
            else
                pontP2.registrarAcertoPerfeito(2);
        }
        
        if (valor == 100)
        {
            if (jogador == 1) 
                pontP1.registrarAcertoBom(1);
            else
                pontP2.registrarAcertoBom(2);
        }
        
        if (valor == 50)
        {
            if (jogador == 1) 
                pontP1.registrarAcertoQuase(1);
            else
                pontP2.registrarAcertoQuase(2);
        }
        
        if (valor == 0)
        {
            if (jogador == 1) 
                pontP1.registrarErro();
            else
                pontP2.registrarErro();
        }
        
        IconeAcerto icone = new IconeAcerto(nomeImagem);
        getWorld().addObject(icone, xFeedback, yFeedback);
    }
    
    public int getTotalNotas()
    {
        return totalNotasOriginal;
    }
}
    

