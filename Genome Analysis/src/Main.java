import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ///for testing
        File aminoAcidsFile = new File("genomeLabNeededFiles/aminoAcidTable.csv");
        File sequenceFile = new File("measlesTestData/measlesSequenceRF1.csv");
        
        try {
            GenomeAnalyzer analyzer = new GenomeAnalyzer(sequenceFile, aminoAcidsFile);
        } catch (IOException e) {
            System.out.println("Invalid File, analysis unavailable");
            return;
        }

        System.out.println("show analysis");
    }
}
