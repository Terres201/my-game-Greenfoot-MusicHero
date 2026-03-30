import greenfoot.*;  

/**
* Classe pública que representa o botão de continuar do jogo, que herda características de Botão
*/

public class BotaoContinuar extends Botao
{
    private boolean trocando = false;
    private int contadorTroca = 0;
    private World mundoAnterior;

    public BotaoContinuar(World mundoAnterior) {
        super("BotaoStart.png", "BotaoStartSele.png");
        this.mundoAnterior = mundoAnterior;
    }

    @Override
    public void acaoSelecionada() 
    {
        if (mundoAnterior instanceof MusicaBase)
        {
            ((MusicaBase)mundoAnterior).retomar();
        }
        
        Greenfoot.setWorld(mundoAnterior);
    }
}
