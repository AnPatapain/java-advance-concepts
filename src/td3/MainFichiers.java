package td3;

import java.io.File;

public class MainFichiers {
    protected String nomFichier;
    protected File dir;
    protected static String path = "/home/kean/Documents/";

    public MainFichiers(String nomFichier) {
        this.nomFichier = nomFichier;
        dir = new File(nomFichier);
        traitement(dir);
    }

    public static void main(String args[]) {
        new MainFichiers(path);
    }

    private void traitement(File dir) throws RuntimeException {

        if (!dir.exists()) {
            System.out.println("le dir " + dir.getName() + "n'existe pas");
            System.exit(1);
        }
        if (!dir.isDirectory()) throw new RuntimeException("dir must be directory");

        System.out.println(" Nom du fichier    : " + dir.getName());
        System.out.println(" Chemin du fichier : " + dir.getPath());
        System.out.println(" Chemin absolu     : " + dir.getAbsolutePath());
        System.out.println(" Droit de lecture  : " + dir.canRead());
        System.out.println(" Droit d'ecriture  : " + dir.canWrite());


        File fichiers[] = dir.listFiles();
        for (int i = 0; i < fichiers.length; i++) {

            if (fichiers[i].isDirectory())
                traitement(fichiers[i]);
            else
                System.out.println("  " + fichiers[i].getName());
        }

    }

}
