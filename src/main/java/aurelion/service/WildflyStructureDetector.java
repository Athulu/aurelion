package aurelion.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WildflyStructureDetector {

    /**
     * Sprawdza, czy dany folder zawiera strukturę WildFly/JBoss
     * (czyli plik 'standalone.xml' w podfolderze 'configuration').
     *
     * @param baseDir katalog do sprawdzenia
     * @return true, jeśli struktura jest zgodna z WildFly/JBoss
     */
    public boolean isWildflyStructure(File baseDir) {
        if (baseDir == null || !baseDir.isDirectory()) {
            return false;
        }

        File configDir = new File(baseDir, "standalone/configuration");
        File standaloneXml = new File(configDir, "standalone.xml");

        return standaloneXml.exists() && standaloneXml.isFile();
    }

    /**
     * Przeszukuje katalogi wewnątrz folderu root i zwraca listę tych,
     * które mają strukturę WildFly/JBoss.
     *
     * @param root katalog, w którym szukać instancji WildFly
     * @return lista folderów z rozpoznaną strukturą
     */
    public List<File> findWildflyInstances(File root) {
        List<File> wildflyFolders = new ArrayList<>();

        if (root == null || !root.isDirectory()) {
            return wildflyFolders;
        }

        File[] subDirs = root.listFiles(File::isDirectory);
        if (subDirs == null) return wildflyFolders;

        for (File dir : subDirs) {
            if (isWildflyStructure(dir)) {
                wildflyFolders.add(dir);
            }
        }

        return wildflyFolders;
    }

    public static void main(String[] args) {
        File rootDirectory = new File("/ścieżka/do/katalogu");
        WildflyStructureDetector detector = new WildflyStructureDetector();

        List<File> wildflyInstances = detector.findWildflyInstances(rootDirectory);
        for (File instance : wildflyInstances) {
            System.out.println("Znaleziono instancję WildFly: " + instance.getAbsolutePath());
        }
    }
}
