import greenfoot.*;  

/**
 * Ator visual usado na tela de carregamento (Loading) para representar
 * o disco de vinil girando. Usado em conjunto com o som de vinil para indicar
 * que o sistema está em processo de transição de tela.
 */
public class IconeVinil extends Actor
{
    private GreenfootImage imagemIcone;
    private int angulo = 0;
    
    public IconeVinil ()
    {
        imagemIcone = new GreenfootImage("IconeVinil.png");
        setImage(imagemIcone);
    }
    
    public void act()
    {
        angulo = (angulo + 6) % 360;
        setRotation(angulo);
    }
}
