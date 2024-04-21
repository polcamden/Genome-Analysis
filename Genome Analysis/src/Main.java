import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AminoAcid[] aminoAcids = {
            new AminoAcid("Alanine", 'A', Arrays.asList("GCT", "GCC", "GCA", "GCG")),
            new AminoAcid("Arginine", 'R', Arrays.asList("CGT", "CGC", "CGA", "CGG", "AGA", "AGG")),
            new AminoAcid("Asparagine", 'N', Arrays.asList("AAT", "AAC")),
            new AminoAcid("Aspartic Acid", 'D', Arrays.asList("GAT", "GAC")),
            new AminoAcid("Cysteine", 'C', Arrays.asList("TGT", "TGC")),
            new AminoAcid("Glutamine", 'Q', Arrays.asList("CAA", "CAG")),
            new AminoAcid("Glutamic Acid", 'E', Arrays.asList("GAA", "GAG")),
            new AminoAcid("Glycine", 'G', Arrays.asList("GGT", "GGC", "GGA", "GGG")),
            new AminoAcid("Histidine", 'H', Arrays.asList("CAT", "CAC")),
            new AminoAcid("Isoleucine", 'I', Arrays.asList("ATT", "ATC", "ATA")),
            new AminoAcid("Leucine", 'L', Arrays.asList("TTA", "TTG", "CTT", "CTC", "CTA", "CTG")),
            new AminoAcid("Lysine", 'K', Arrays.asList("AAA", "AAG")),
            new AminoAcid("Methionine", 'M', Arrays.asList("ATG")),
            new AminoAcid("Phenylalanine", 'F', Arrays.asList("TTT", "TTC")),
            new AminoAcid("Proline", 'P', Arrays.asList("CCT", "CCC", "CCA", "CCG")),
            new AminoAcid("Serine", 'S', Arrays.asList("TCT", "TCC", "TCA", "TCG", "AGT", "AGC")),
            new AminoAcid("Threonine", 'T', Arrays.asList("ACT", "ACC", "ACA", "ACG")),
            new AminoAcid("Tryptophan", 'W', Arrays.asList("TGG")),
            new AminoAcid("Tyrosine", 'Y', Arrays.asList("TAT", "TAC")),
            new AminoAcid("Valine", 'V', Arrays.asList("GTT", "GTC", "GTA", "GTG"))
        };

        ///for testing
        File sequenceFile = new File("measlesTestData/measlesSequenceRF1.csv");
        GenomeAnalyzer analyzer = null;

        try {
            analyzer = new GenomeAnalyzer(sequenceFile, aminoAcids);
        } catch (IOException e) {
            System.out.println("Invalid File, analysis unavailable");
            analyzer = null;
            return;
        }

        System.out.println("show analysis");
        for (int i = 0; i < analyzer.getAminoAcidLength(); i++) {
            System.out.println(analyzer.getAminoAcid(i));
        }
    }
}
