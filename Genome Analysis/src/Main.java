import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args)throws IOException, FileNotFoundException {
    
        ArrayList<AminoAcid> aminoAcids = new ArrayList<AminoAcid>();
        File aminoAcidTable = new File("src/genomeLabNeededFiles/aminoAcidTable.csv");
        Scanner infile = new Scanner(aminoAcidTable);

        //make an instance of an AminoAcid for each line of the file
        infile.nextLine(); //skip the first line
        while(infile.hasNext()){
            String str = infile.nextLine();
            String[] tokens = str.split(",");
            //put codons into an ArrayList
            ArrayList<String> codons = new ArrayList<String>();
            for(int i = 3; i < tokens.length; i++){
                codons.add(tokens[i]);
            }
            AminoAcid temp = new AminoAcid(tokens[0],tokens[2].charAt(0),codons);
            aminoAcids.add(temp);
        }

        infile.close();
        /*for (AminoAcid aminoAcid : aminoAcids) {
            System.out.println(aminoAcid);
        }*/

        File sequenceFile = new File("genomeLabNeededFiles/covidSequenceRF3.csv");

        GenomeAnalyzer genomeAnalyzer = new GenomeAnalyzer(sequenceFile, aminoAcids.toArray(new AminoAcid[aminoAcids.size()]));

        printFileAminoAcids(genomeAnalyzer);
        printFileGeneAnalysis(genomeAnalyzer);
    }

    public static void printFileAminoAcids(GenomeAnalyzer genomeAnalyzer) throws FileNotFoundException{
        File out = new File(genomeAnalyzer.getSequenceFileName() + "_CodonBias.txt");

        PrintWriter outfile = new PrintWriter(out);

        outfile.println("** Codon analysis for file: " + genomeAnalyzer.getSequenceFileName() + ".csv **\n");

        for (int i = 0; i < genomeAnalyzer.getAminoAcidLength(); i++) {
            outfile.println(genomeAnalyzer.getAminoAcid(i));
        }

        outfile.close();
    }
    /**
     * 
     * @param genomeAnalyzer genomeAnalyzer for the chosen reading frame
     * @throws FileNotFoundException
     * This method uses the GenomeAnalyzer class to print gene analysis file for the chosen reading frame
     */
    public static void printFileGeneAnalysis(GenomeAnalyzer genomeAnalyzer) throws FileNotFoundException{
        File out = new File(genomeAnalyzer.getSequenceFileName() + "_GeneAnalysis.txt");

        PrintWriter outfile = new PrintWriter(out);

        outfile.println("** Gene analysis for file: " + genomeAnalyzer.getSequenceFileName() + ".csv **\n");

        for (int i = 0; i < genomeAnalyzer.getGeneLength(); i++) {
            outfile.println(genomeAnalyzer.getGene(i) + "\n");
        }

        outfile.close();
    }
}

// public class Main {
//     public static void main(String[] args) {
//         AminoAcid[] aminoAcids = {
//             new AminoAcid("Alanine", 'A', Arrays.asList("GCT", "GCC", "GCA", "GCG")),
//             new AminoAcid("Arginine", 'R', Arrays.asList("CGT", "CGC", "CGA", "CGG", "AGA", "AGG")),
//             new AminoAcid("Asparagine", 'N', Arrays.asList("AAT", "AAC")),
//             new AminoAcid("Aspartic Acid", 'D', Arrays.asList("GAT", "GAC")),
//             new AminoAcid("Cysteine", 'C', Arrays.asList("TGT", "TGC")),
//             new AminoAcid("Glutamine", 'Q', Arrays.asList("CAA", "CAG")),
//             new AminoAcid("Glutamic Acid", 'E', Arrays.asList("GAA", "GAG")),
//             new AminoAcid("Glycine", 'G', Arrays.asList("GGT", "GGC", "GGA", "GGG")),
//             new AminoAcid("Histidine", 'H', Arrays.asList("CAT", "CAC")),
//             new AminoAcid("Isoleucine", 'I', Arrays.asList("ATT", "ATC", "ATA")),
//             new AminoAcid("Leucine", 'L', Arrays.asList("TTA", "TTG", "CTT", "CTC", "CTA", "CTG")),
//             new AminoAcid("Lysine", 'K', Arrays.asList("AAA", "AAG")),
//             new AminoAcid("Methionine", 'M', Arrays.asList("ATG")),
//             new AminoAcid("Phenylalanine", 'F', Arrays.asList("TTT", "TTC")),
//             new AminoAcid("Proline", 'P', Arrays.asList("CCT", "CCC", "CCA", "CCG")),
//             new AminoAcid("Serine", 'S', Arrays.asList("TCT", "TCC", "TCA", "TCG", "AGT", "AGC")),
//             new AminoAcid("Threonine", 'T', Arrays.asList("ACT", "ACC", "ACA", "ACG")),
//             new AminoAcid("Tryptophan", 'W', Arrays.asList("TGG")),
//             new AminoAcid("Tyrosine", 'Y', Arrays.asList("TAT", "TAC")),
//             new AminoAcid("Valine", 'V', Arrays.asList("GTT", "GTC", "GTA", "GTG"))
//         };

//         /for testing
//         File sequenceFile = new File("measlesTestData/measlesSequenceRF3.csv");
//         GenomeAnalyzer analyzer = null;

//         try {
//             analyzer = new GenomeAnalyzer(sequenceFile, aminoAcids);
//         } catch (IOException e) {
//             System.out.println("Invalid File, analysis unavailable");
//             analyzer = null;
//             return;
//         }

//         System.out.println("show analysis\nAminoAcids");
//         for (int i = 0; i < analyzer.getAminoAcidLength(); i++) {
//             System.out.println(analyzer.getAminoAcid(i));
//         }

//         System.out.println("Genes");
//         for (int i = 0; i < analyzer.getGeneLength(); i++) {
//             System.out.println(analyzer.getGene(i));
//         }
//     }
// }
