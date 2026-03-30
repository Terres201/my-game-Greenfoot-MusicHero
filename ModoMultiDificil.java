import greenfoot.*;
import java.util.*;
/**
 * Representa a implementação concreta de um modo de jogo específico, como o Modo [Single/Multi] [Normal/Dificil].
 * Herda a estrutura básica de ModoBase e implementa o método 'iniciar()' para:
 * 1. Definir o cenário visual específico.
 * 2. Criar e preencher o mapa de notas com a lógica de ritmo e a contagem de notas corretas.
 * 3. Instanciar e adicionar o ControladorNotas ao mundo com as configurações de velocidade.
 */

public class ModoMultiDificil extends ModoBase 
{
    private static final String cenario = "MusicaMulti.png";

    private String converterParaP2(String teclaP1) 
    {
        switch (teclaP1) 
        {
            case "a": return "left";
            case "w": return "up";
            case "s": return "down";
            case "d": return "right";
            default: return teclaP1;
        }
    }
    
    public ModoMultiDificil(MusicaBase mundo, String musicaNome)
    {
        super(mundo, musicaNome);
        prepararZonasParaMultiplayer();
        iniciar();
        mundo.setBackground(cenario);
    }
    
    @Override
    public void iniciar()
    {
        if ("MusicaA".equals(musicaNome))
        {
            List<ControladorNotas.EntradaNota> mapa = new ArrayList<>();
            
            final int Q_NOTE = 380; 
            final int E_NOTE = 190; 
            final int COMPLEXITY_DELAY = 300; 
            final long TEMPO_MAXIMO_A = 95000;
            
            int tempo_atual = 500; 
            int velocidade = 5;
            
            String teclaP1;
            String teclaP2;
            
            int totalNotasMapeadas = mapa.size();
            
    
            for (int i = 0; i < 15; i++) 
            {
                teclaP1 = "a"; 
                teclaP2 = converterParaP2("a");
                
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1)); 
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE * 2; 
            }
            
            for (int m = 0; m < 8; m++) 
            {
                teclaP1 = "a"; teclaP2 = converterParaP2("a");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE;
                
                teclaP1 = "d"; teclaP2 = converterParaP2("d");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE;
                
                teclaP1 = "s"; teclaP2 = converterParaP2("s");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE;
                
                teclaP1 = "d"; teclaP2 = converterParaP2("d");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE;
            }
            
            tempo_atual += 500; 
            int refrao_final = tempo_atual + (10 * 1000);
            
            while (tempo_atual < refrao_final) 
            {
                teclaP1 = "a"; teclaP2 = converterParaP2("a");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += E_NOTE * 2;
                
                teclaP1 = "w"; teclaP2 = converterParaP2("w");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += E_NOTE * 2;
            }
            
            tempo_atual += 500; 
            for (int m = 0; m < 8; m++) 
            {
                teclaP1 = "a"; teclaP2 = converterParaP2("a");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE;

                teclaP1 = "d"; teclaP2 = converterParaP2("d");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE;

                teclaP1 = "s"; teclaP2 = converterParaP2("s");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE;

                teclaP1 = "d"; teclaP2 = converterParaP2("d");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE;
            }

            for (int m = 0; m < 28; m++) 
            {
                teclaP1 = "a"; teclaP2 = converterParaP2("a");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE;

                teclaP1 = "d"; teclaP2 = converterParaP2("d");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE;

                tempo_atual += Q_NOTE * 2; 
            }

            int TEMPO_FINAL = 90000;
            int DELAY_ACORDE = 200;
            
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_FINAL, "a", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_FINAL, "d", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_FINAL, "left", 2));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_FINAL, "right", 2));
            
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_FINAL + DELAY_ACORDE, "w", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_FINAL + DELAY_ACORDE, "s", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_FINAL + DELAY_ACORDE, "up", 2));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_FINAL + DELAY_ACORDE, "down", 2));
            
            
            controlador = new ControladorNotas(mapa, velocidade, zonasP1, zonasP2, pontuacaoP1, pontuacaoP2,TEMPO_MAXIMO_A, totalNotasMapeadas);
            mundo.addObject(controlador, 0, 0);
            
            TocadorMusica.tocarMusica("MusicaA.mp3");
        }
        
        else if ("MusicaB".equals(musicaNome))
        {
            List<ControladorNotas.EntradaNota> mapa = new ArrayList<>();
            
            final int Q_NOTE = 468; 
            final int E_NOTE = 234; 
            final int COMPLEXITY_DELAY = 100; 
            
            int tempo_atual = 500; 
            int velocidade = 5; 
            final long TEMPO_MAXIMO_B = 92000;

            final int TEMPO_MAXIMO = 85000; 
            
            String teclaP1;
            String teclaP2;
            
            int totalNotasMapeadas = mapa.size();
            
            while (tempo_atual < 10000) 
            {
                teclaP1 = "a"; teclaP2 = converterParaP2("a");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE;

                teclaP1 = "s"; teclaP2 = converterParaP2("s");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE;
            }

            while (tempo_atual < 34000) 
            {
                teclaP1 = "a"; teclaP2 = converterParaP2("a");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE; 

                teclaP1 = "s"; teclaP2 = converterParaP2("s");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                tempo_atual += Q_NOTE; 
            }

            while (tempo_atual < 68000) 
            {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual + 50, "s", 1));

                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, converterParaP2("a"), 2));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual + 50, converterParaP2("s"), 2)); 
    
                tempo_atual += Q_NOTE;

                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "w", 1));

                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, converterParaP2("w"), 2));
    
                tempo_atual += Q_NOTE;
            }

            while (tempo_atual < TEMPO_MAXIMO - Q_NOTE * 2) 
            {
                teclaP1 = "a"; teclaP2 = converterParaP2("a");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, teclaP2, 2));
                
                teclaP1 = "s"; teclaP2 = converterParaP2("s");
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual + Q_NOTE, teclaP1, 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual + Q_NOTE, teclaP2, 2));
                
                tempo_atual += Q_NOTE * 2;
            }

            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "a", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "w", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "s", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "d", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "left", 2));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "up", 2));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "down", 2));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "right", 2));

            controlador = new ControladorNotas(mapa, velocidade, zonasP1, zonasP2, pontuacaoP1, pontuacaoP2, TEMPO_MAXIMO_B, totalNotasMapeadas);
            mundo.addObject(controlador, 0, 0);
            
            TocadorMusica.tocarMusica("MusicaB.mp3");
        }
        
        else if ("MusicaC".equals(musicaNome))
        {
            List<ControladorNotas.EntradaNota> mapa = new ArrayList<>();

            final int Q_NOTE = 435; 
            final int E_NOTE = 217; 
            final int VELOCIDADE = 8; 
            final int TEMPO_MAXIMO = 65000; 
            final int TEMPO_MAXIMO_C = 70000; 
            
            int tempo_atual = 1000; 

            while (tempo_atual < 12000) 
            {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, converterParaP2("a"), 2));
                tempo_atual += Q_NOTE;

                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "d", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, converterParaP2("d"), 2));
                tempo_atual += Q_NOTE;
            }

            while (tempo_atual < 25000) 
            {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, converterParaP2("a"), 2));

                mapa.add(new ControladorNotas.EntradaNota(tempo_atual + E_NOTE, "w", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual + E_NOTE, converterParaP2("w"), 2));
                
                tempo_atual += Q_NOTE * 2; 
            }

            while (tempo_atual < 38000) 
            {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, converterParaP2("a"), 2));
                tempo_atual += E_NOTE;
                
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "s", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, converterParaP2("s"), 2));
                tempo_atual += E_NOTE;

                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "d", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, converterParaP2("d"), 2));
                tempo_atual += E_NOTE;

                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "w", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, converterParaP2("w"), 2));
                tempo_atual += E_NOTE;
            }

            while (tempo_atual < 50000) 
            {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, converterParaP2("a"), 2));
                
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual + E_NOTE, "w", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual + E_NOTE, converterParaP2("w"), 2));
                
                tempo_atual += Q_NOTE * 2; 
            }

            while (tempo_atual < TEMPO_MAXIMO - Q_NOTE) 
            {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, converterParaP2("a"), 2));
                tempo_atual += E_NOTE;

                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "s", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, converterParaP2("s"), 2));
                tempo_atual += E_NOTE;

                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "d", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, converterParaP2("d"), 2));
                tempo_atual += E_NOTE;

                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "w", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, converterParaP2("w"), 2));
                tempo_atual += E_NOTE;
            }

            int totalNotasMapeadas = mapa.size() + 8; 
            
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "a", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, converterParaP2("a"), 2));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "w", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, converterParaP2("w"), 2));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "s", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, converterParaP2("s"), 2));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "d", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, converterParaP2("d"), 2));
        
            controlador = new ControladorNotas(mapa, VELOCIDADE, zonasP1, zonasP2, pontuacaoP1, pontuacaoP2, TEMPO_MAXIMO_C, totalNotasMapeadas); 
            mundo.addObject(controlador, 0, 0);
            
            TocadorMusica.tocarMusica("MusicaC.mp3"); 
        }
    }
}
