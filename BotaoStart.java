import greenfoot.*; 

/**
* Classe pública que representa os botões de iniciar do jogo, que herda características de Botão
*/

public class BotaoStart extends Botao
{
    private boolean trocando = false;
    private int contadorTroca = 0;

    public BotaoStart() {
        super("BotaoStart.png", "BotaoStartSele.png");
    }

    @Override
    public void acaoSelecionada() 
    {
        Greenfoot.setWorld(new Loading(new MenuMusicas()));
    }
}

