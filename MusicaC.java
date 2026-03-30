import greenfoot.*;  

/**
 * Representa uma fase jogável específica, herdando a funcionalidade base de MusicaBase.
 * É responsável por chamar o modo de jogo apropriado (ModoSingleNormal, ModoMultiDificil, etc.)
 * e iniciar o ControladorNotas com o mapeamento e as configurações de dificuldade corretas
 * para a Música [A, B, ou C].
 */

public class MusicaC extends MusicaBase
{
    public MusicaC(ModoJogo modo, Dificuldade dificuldade) 
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
                new ModoSingleNormal(this, "MusicaC");
            else 
                new ModoSingleDificil(this, "MusicaC");
        } 
        else 
        {
            if (dificuldade == Dificuldade.NORMAL) 
                new ModoMultiNormal(this, "MusicaC");
            else 
                new ModoMultiDificil(this, "MusicaC");
        }
    }
}
