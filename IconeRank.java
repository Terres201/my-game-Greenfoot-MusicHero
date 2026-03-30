import greenfoot.*;  

/**
 * Ator visual usado na tela de Game Over para exibir a imagem da classificação
 * final do jogador (Rank S, A, B, C, D, ou F).
 */

public class IconeRank extends Actor
{
    public IconeRank(String rank)
    {
        String rankMaiusculo = rank.toUpperCase();
        String nomeArquivo = "IconeRank" + rankMaiusculo + ".png";
        
        setImage(nomeArquivo);
    }
}
