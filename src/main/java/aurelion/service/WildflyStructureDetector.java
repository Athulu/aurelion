package aurelion.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WildflyStructureDetector {

    public boolean isWildflyStructure(File baseDir) {
        if (baseDir == null || !baseDir.isDirectory()) {
            return false;
        }

        File configDir = new File(baseDir, "standalone/configuration");
        File standaloneXml = new File(configDir, "standalone.xml");

        return standaloneXml.exists() && standaloneXml.isFile();
    }

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
        File rootDirectory = new File("D:\\inne\\inne 2025\\wildfly-17.0.0.Final");
        WildflyStructureDetector detector = new WildflyStructureDetector();

        List<File> wildflyInstances = detector.findWildflyInstances(rootDirectory);
        for (File instance : wildflyInstances) {
            System.out.println(instance.getAbsolutePath());
        }
    }
}
