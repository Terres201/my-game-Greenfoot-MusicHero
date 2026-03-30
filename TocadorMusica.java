import greenfoot.*;  

/**
 * Classe estática (controlada por métodos estáticos) responsável por gerenciar a reprodução
 * de áudio principal (música de fundo ou preview) e efeitos sonoros do jogo.
 */

public class TocadorMusica extends Actor
{
    private static GreenfootSound musicaAtual;
    private static GreenfootSound efeitoSom;
    private static String nomeMusicaAtual = null;
    private static String nomeEfeitoSom = null;
    private static boolean pausada = false;

    public static void atualizarMusicaMundo(World mundo)
    {
        if (mundo instanceof Menu)
        {
            tocarMusica("Menu.mp3");
        }
        else
        {
            pararMusica();
        }
    }

    public static void tocarMusica(String nome)
    {
        if (musicaAtual != null && nome.equals(nomeMusicaAtual) && musicaAtual.isPlaying())
            return;
        
        if (musicaAtual != null) 
            musicaAtual.stop();
            
        musicaAtual = new GreenfootSound(nome);
        nomeMusicaAtual = nome;
        musicaAtual.playLoop();
        pausada = false;
    }

    public static void pararMusica()
    {
        if (musicaAtual != null)
        {
            musicaAtual.stop();
            nomeMusicaAtual = null;
            pausada = false;
        }
    }
    
    public static void pausarMusica()
    {
        if (musicaAtual != null && musicaAtual.isPlaying())
        {
            musicaAtual.pause();
            pausada = true;
        }
    }
    
    public static void retomarMusica()
    {
        if (musicaAtual != null && pausada)
        {
            musicaAtual.play();
            pausada = false;
        }
    }
    
    public static void tocarEfeito(String nome)
    {
        efeitoSom = new GreenfootSound(nome);
        nomeEfeitoSom = nome;
        efeitoSom.play();
    }
    
    public static void pararEfeito()
    {
        if (efeitoSom != null)
        {
            efeitoSom.stop();
            nomeEfeitoSom = null;
        }
    }
    
    public static boolean estaPausada()
    {
        return pausada;
    }
}