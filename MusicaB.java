import greenfoot.*;  

/**
 * Representa uma fase jogável específica, herdando a funcionalidade base de MusicaBase.
 * É responsável por chamar o modo de jogo apropriado (ModoSingleNormal, ModoMultiDificil, etc.)
 * e iniciar o ControladorNotas com o mapeamento e as configurações de dificuldade corretas
 * para a Música [A, B, ou C].
 */

public class MusicaB extends MusicaBase
{
    public MusicaB(ModoJogo modo, Dificuldade dificuldade) 
    {
        super(modo, dificuldade);
    }

    public void act() 
    {
        super.act();
        
        if (!iniciado)
        {
            prepararModo();
            iniciado = true;
        }
    }

    @Override
    protected void prepararModo() 
    {
        if (modo == ModoJogo.SINGLEPLAYER) 
        {
            if (dificuldade == Dificuldade.NORMAL)
                new ModoSingleNormal(this, "MusicaB");
            else 
                new ModoSingleDificil(this, "MusicaB");
        } 
        else 
        {
            if (dificuldade == Dificuldade.NORMAL) 
                new ModoMultiNormal(this, "MusicaB");
            else 
                new ModoMultiDificil(this, "MusicaB");
        }
    }
}