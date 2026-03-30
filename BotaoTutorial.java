import greenfoot.*;  

/**
* Classe pública que representa os botões de controle do jogo, que herda características de Botão
*/

public class BotaoTutorial extends Botao
{  
    public BotaoTutorial()
    {
        super("BotaoTutorial.png","BotaoTutorialSele.png");
    }
    
    @Override
    public void acaoSelecionada()
    {
        Greenfoot.setWorld(new MenuTutorial());
    }
}
