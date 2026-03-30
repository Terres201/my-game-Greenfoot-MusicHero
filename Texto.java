import greenfoot.*;

/**
 * Ator simples usado para exibir texto na tela com tamanho, cor e estilo (negrito) customizados.
 * Usado para placares, mensagens de Game Over e informações de menu.
 */

public class Texto extends Actor
{
    private String texto;
    private int tamanho;
    private Color cor;

    public Texto(String texto, int tamanho, Color cor)
    {
        this.texto = texto;
        this.tamanho = tamanho;
        this.cor = cor;
        atualizarImagem();
    }
    
    private void atualizarImagem()
    {
        // Cria uma nova imagem transparente.
        GreenfootImage imagem = new GreenfootImage(tamanho * 20, tamanho + 20); 
        
        // Define a cor e a fonte.
        imagem.setColor(cor);
        imagem.setFont(new Font("Arial",false, false, tamanho));
        
        // Desenha o texto. O valor 'tamanho' é usado para centralizar.
        imagem.drawString(texto, 0, tamanho);
        
        setImage(imagem);
    }
    
    public void setTexto(String novoTexto)
    {
        this.texto = novoTexto;
        atualizarImagem();
    }
}
