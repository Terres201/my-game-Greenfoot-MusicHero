import greenfoot.*;
import java.util.*;

/**
 * Representa a implementação concreta de um modo de jogo específico, como o Modo [Single/Multi] [Normal/Dificil].
 * Herda a estrutura básica de ModoBase e implementa o método 'iniciar()' para:
 * 1. Definir o cenário visual específico.
 * 2. Criar e preencher o mapa de notas com a lógica de ritmo e a contagem de notas corretas.
 * 3. Instanciar e adicionar o ControladorNotas ao mundo com as configurações de velocidade.
 */

public class ModoSingleNormal extends ModoBase 
{
    private static final String cenario = "MusicaSingle.png";
    
    public ModoSingleNormal(MusicaBase mundo, String musicaNome)
    {
        super(mundo, musicaNome);
        iniciar();
        mundo.setBackground(cenario);
    }
    
    @Override
    public void iniciar()
    {
        if ("MusicaA".equals(musicaNome))
        {
            List<ControladorNotas.EntradaNota> mapa = new ArrayList<>();
        
            final long TEMPO_MAXIMO_A = 95000;        
            final int Q_NOTE = 380; 
            final int E_NOTE = 190;
            final int COMPLEXITY_DELAY = 300;
            
            int tempo_atual = 500; 
            int velocidade = 3;
            
            int totalNotasMapeadas = mapa.size();
            
            
            for (int i = 0; i < 15; i++) 
            {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                tempo_atual += Q_NOTE * 2; 
            }
            
            
            for (int m = 0; m < 8; m++) 
            {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                tempo_atual += Q_NOTE;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "d", 1));
                tempo_atual += Q_NOTE;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "s", 1));
                tempo_atual += Q_NOTE;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "d", 1));
                tempo_atual += Q_NOTE;
                
                if (m % 2 == 1) {
                    mapa.add(new ControladorNotas.EntradaNota(tempo_atual - Q_NOTE + E_NOTE, "w", 1));
                }
            }
            
            tempo_atual += 500; 
            int refrao_final = tempo_atual + (10 * 1000);
            while (tempo_atual < refrao_final) 
            {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                tempo_atual += E_NOTE * 2;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "w", 1));
                tempo_atual += E_NOTE * 2;
            }
            
            tempo_atual += 500; 
            for (int m = 0; m < 8; m++) 
            {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                tempo_atual += Q_NOTE;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "d", 1));
                tempo_atual += Q_NOTE;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "s", 1));
                tempo_atual += Q_NOTE;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "d", 1));
                tempo_atual += Q_NOTE;
                
                if (m % 2 == 1) 
                {
                    mapa.add(new ControladorNotas.EntradaNota(tempo_atual - Q_NOTE + E_NOTE, "w", 1));
                }
            }
            
            for (int m = 0; m < 28; m++) 
            {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual + Q_NOTE, "d", 1));
                
                if (m % 3 == 0) 
                { 
                    mapa.add(new ControladorNotas.EntradaNota(tempo_atual + COMPLEXITY_DELAY, "w", 1));
                    mapa.add(new ControladorNotas.EntradaNota(tempo_atual + Q_NOTE + COMPLEXITY_DELAY, "s", 1));
                }
                
                tempo_atual += Q_NOTE * 4;
            
            }
            
            mapa.add(new ControladorNotas.EntradaNota(90000, "a", 1));
            mapa.add(new ControladorNotas.EntradaNota(90000, "d", 1));
            mapa.add(new ControladorNotas.EntradaNota(90000 + COMPLEXITY_DELAY, "w", 1));
            mapa.add(new ControladorNotas.EntradaNota(90000 + COMPLEXITY_DELAY, "s", 1));
        
            controlador = new ControladorNotas(mapa, velocidade, zonasP1, null, pontuacaoP1, null, TEMPO_MAXIMO_A, totalNotasMapeadas);
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
            int velocidade = 3; 
            final long TEMPO_MAXIMO_B = 92000;

            final int TEMPO_MAXIMO = 85000; 
            
            int totalNotasMapeadas = mapa.size();

            while (tempo_atual < 10000) 
            {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                tempo_atual += Q_NOTE;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "s", 1));
                tempo_atual += Q_NOTE;
            }

            while (tempo_atual < 34000) 
            {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                tempo_atual += Q_NOTE; 

                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "s", 1));
                tempo_atual += Q_NOTE; 
            }

            while (tempo_atual < 68000) 
            {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual + 50, "s", 1)); 
                tempo_atual += Q_NOTE;
                
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "w", 1));
                tempo_atual += Q_NOTE;
            }

            while (tempo_atual < TEMPO_MAXIMO - Q_NOTE * 2) 
            { 
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual + Q_NOTE, "s", 1));
                tempo_atual += Q_NOTE * 2; 
            }

            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "a", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "w", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "s", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "d", 1));
            
            controlador = new ControladorNotas(mapa, velocidade, zonasP1, null, pontuacaoP1, null, TEMPO_MAXIMO_B, totalNotasMapeadas); 
            mundo.addObject(controlador, 0, 0);
            
            TocadorMusica.tocarMusica("MusicaB.mp3"); 
        }
        
        else if ("MusicaC".equals(musicaNome))
        {
            List<ControladorNotas.EntradaNota> mapa = new ArrayList<>();
            
            // Constantes Rítmicas para 138 BPM
            final int Q_NOTE = 435; 
            final int E_NOTE = 217; 
            final int VELOCIDADE = 3;
            final int TEMPO_MAXIMO = 65000; 
            final int TEMPO_MAXIMO_C = 70000;
            
            int tempo_atual = 1000;

            while (tempo_atual < 12000) {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                tempo_atual += Q_NOTE;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "d", 1));
                tempo_atual += Q_NOTE;
            }

            while (tempo_atual < 25000) {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual + E_NOTE, "w", 1)); 
                tempo_atual += Q_NOTE * 2; 
            }

            while (tempo_atual < 38000) {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                tempo_atual += E_NOTE;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "s", 1));
                tempo_atual += E_NOTE;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "d", 1));
                tempo_atual += E_NOTE;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "w", 1));
                tempo_atual += E_NOTE;
            }

            while (tempo_atual < 50000) {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual + E_NOTE, "w", 1));
                tempo_atual += Q_NOTE * 2; 
            }

            while (tempo_atual < TEMPO_MAXIMO - Q_NOTE) {
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "a", 1));
                tempo_atual += E_NOTE;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "s", 1));
                tempo_atual += E_NOTE;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "d", 1));
                tempo_atual += E_NOTE;
                mapa.add(new ControladorNotas.EntradaNota(tempo_atual, "w", 1));
                tempo_atual += E_NOTE;
            }

            int totalNotasMapeadas = mapa.size();
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "a", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "w", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "s", 1));
            mapa.add(new ControladorNotas.EntradaNota(TEMPO_MAXIMO, "d", 1));
            
            
            // INICIALIZAÇÃO
            controlador = new ControladorNotas(mapa, VELOCIDADE, zonasP1, null, pontuacaoP1, null, TEMPO_MAXIMO_C, totalNotasMapeadas); 
            mundo.addObject(controlador, 0, 0);
            
            TocadorMusica.tocarMusica("MusicaC.mp3"); 
        }
    }
}
    
    

