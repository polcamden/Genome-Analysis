import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Takes a sequence file and amino acids file, analysis the amino acids and
 * genes
 */
public class GenomeAnalyzer {
    private File sequenceFile;
    
    private ArrayList<AminoAcid> aminoAcids;

    private ArrayList<Gene> genes;

    final int ORF = 50;

    /**
     * Takes a Sequence file and aminoAcids, start analysis
     * 
     * @param sequenceFile sequence file
     * @throws IOException
     * @author Camden
     */
    public GenomeAnalyzer(File sequenceFile, ArrayList<AminoAcid> aminoAcids) throws IOException {
        this.sequenceFile = sequenceFile;
        this.aminoAcids = aminoAcids;
        analyzeGenome();
    }

    /**
     * copys a genomeAnalyzer
     * 
     * @param genomeAnalyzer analyzer to clone
     * @throws IOException
     * @author Camden
     */
    public GenomeAnalyzer(GenomeAnalyzer genomeAnalyzer) throws IOException {
        sequenceFile = genomeAnalyzer.sequenceFile;
        aminoAcids = genomeAnalyzer.aminoAcids;
        analyzeGenome();
    }

    /*
     * !!!! These next commented out methods were written by Nidhi 
     * but I moved them to main and edited them to work with what
     * Camden already wrote - Gianna
     */

    
    // public void runMenu() throws FileNotFoundException {
    //     Scanner scanner = new Scanner(System.in);

    //     while (true) {
    //         System.out.println("\nMenu:");
    //         System.out.println("1. Lookup an Amino Acid by its Single-Letter Code");
    //         System.out.println("2. Print a Report of All Amino Acids");
    //         System.out.println("3. Find Genes and Track Amino Acids");
    //         System.out.println("4. Exit");
    //         System.out.print("Enter your choice: ");

    //         int choice = scanner.nextInt();
    //         scanner.nextLine(); // consume newline character

    //         switch (choice) {
    //             case 1:
    //                 System.out.print("Enter the single-letter code: ");
    //                 char letter = scanner.nextLine().charAt(0);
    //                 lookupAminoAcid(letter);
    //                 break;
    //             case 2:
    //                 printAllAminoAcidsReport();
    //                 break;
    //             case 3:
    //                 System.out.print("Enter the DNA sequence: ");
    //                 String sequence = scanner.nextLine();
    //                 findGenes(sequence);
    //                 break;
    //             case 4:
    //                 System.out.println("Exiting...");
    //                 scanner.close();
    //                 return;
    //             default:
    //                 System.out.println("Invalid choice. Please enter a number between 1 and 4.");
    //         }
    //     }
    // }

    // public void lookupAminoAcid(char letter) {
    //     for (AminoAcid aminoAcid : aminoAcids) {
    //         if (aminoAcid.getSingleLetterCode() == letter) {
    //             System.out.println("Name: " + aminoAcid.getFullName());
    //             System.out.println("Letter: " + aminoAcid.getSingleLetterCode());
    //             System.out.println("Codons: " + aminoAcid.getCodons());
    //             return;
    //         }
    //     }
    //     System.out.println("Amino acid not found for the given letter.");
    // }

    // public void printAllAminoAcidsReport() throws FileNotFoundException {
    //     File out = new File(getSequenceFileName() + "_CodonBias.txt");
    //     PrintWriter outfile = new PrintWriter(out);

    //     outfile.println("** Codon analysis **\n");

    //     for (AminoAcid aminoAcid : aminoAcids) {
    //         outfile.println("Name: " + aminoAcid.getFullName());
    //         outfile.println("Letter: " + aminoAcid.getSingleLetterCode());
    //         outfile.println("Codons: " + aminoAcid.getCodons() + "\n");
    //     }

    //     outfile.close();
    //     System.out.println("Report generated successfully.");
    // }

    // public void findGenes(String sequence) {
    //     int codonCount = 0;
    //     int geneStart = -1;
    //     StringBuilder geneSequence = new StringBuilder();

    //     for (int i = 0; i < sequence.length() - 2; i += 3) {
    //         String codon = sequence.substring(i, i + 3);
    //         codonCount++;

    //         if (codon.equals("ATG")) {
    //             if (geneStart == -1) {
    //                 geneStart = codonCount;
    //                 geneSequence.setLength(0);
    //             }
    //         }

    //         if (geneStart != -1) {
    //             AminoAcid aminoAcid = findAminoAcid(codon);
    //             if (aminoAcid != null) {
    //                 geneSequence.append(aminoAcid.getSingleLetterCode());
    //             }

    //             if (codon.equals("TAA") || codon.equals("TGA") || codon.equals("TAG")) {
    //                 if (geneStart != -1) {
    //                     System.out.println("Gene found from codon " + geneStart + " to " + codonCount + ": " + geneSequence.toString());
    //                     geneStart = -1;
    //                 }
    //             }
    //         }
    //     }
    // }

    // Note from Gianna: I made this method public, it was private
    // before (lol)

    /**
     * analysis the amino acids and genes
     * 
     * @throws IOException invalid file
     * @author Camden
     */
    public void analyzeGenome() throws IOException {
        Scanner in = new Scanner(sequenceFile);

        // reset aminoAcids and genes
        // cloneAminoAcids(aminoAcids);
        genes = new ArrayList<>();

        // gets amino acids from file
        String line = in.nextLine();
        String[] tokens = line.split(",");

        Gene currentGene = null;

        for (int i = 0; i < tokens.length; i++) {
            String codon = tokens[i];

            if (codon.equals("ATG") && currentGene == null) // gene start
            {
                currentGene = new Gene(i * 3);
            } else if (codon.equals("TAG") || codon.equals("TAA") || codon.equals("TGA")) // gene end
            {
                if (currentGene != null && currentGene.getAminoAcidLength() >= ORF) {
                    currentGene.addAminoAcid('*'); // adds end
                    genes.add(currentGene);
                }
                currentGene = null;
            }

            AminoAcid find = findAminoAcid(codon);

            if (find != null && currentGene != null) {
                currentGene.addAminoAcid(find.getSingleLetterCode());
            }
        }

        in.close();
    }

    /**
     * Finds an amino acid with matching codon, adds to found amino acids codon
     * count
     * 
     * @param codon Set of 3 Nucleotides
     * @return Amino acid
     * @author Camden
     */
    private AminoAcid findAminoAcid(String codon) {
        for (AminoAcid aminoAcid : aminoAcids) {
            List<String> codons = aminoAcid.getCodons();

            for (int j = 0; j < codons.size(); j++) {
                if (codons.get(j).equals(codon)) {
                    aminoAcid.countCodon(j);
                    return aminoAcid; // amino acid found can break
                }
            }
        }

        return null;
    }

    /**
     * Clones amino acids resetting the counter
     * 
     * @param acids array of all acids
     */
    /*
     * private void cloneAminoAcids(AminoAcid[] acids){
     * aminoAcids = new AminoAcid[acids.length];
     * 
     * }
     */

    /**
     * gets the AminoAcid in aminoAcids array at i
     * 
     * @param i index
     * @return AminoAcid
     * @author Camden
     */
    public AminoAcid getAminoAcid(int i) {
        // return aminoAcids[i];
        return aminoAcids.get(i);
    }

    /**
     * gets the length of aminoAcid array
     * 
     * @return aminoAcids array length
     * @author Camden
     */
    public int getAminoAcidLength() {
        // return aminoAcids.length;
        return aminoAcids.size();
    }

    /**
     * gets the Gene in genes list at i
     * 
     * @param i index
     * @return Gene
     * @author Camden
     */
    public Gene getGene(int i) {
        return genes.get(i);
    }

    /**
     * gets the length of genes list
     * 
     * @return genes arrayList size
     * @author Camden
     */
    public int getGeneLength() {
        return genes.size();
    }
    /**
     * 
     * @return file name
     * @author Camden
     */
    public String getSequenceFileName(){

        String str = sequenceFile.getName();

        return str.substring(0, str.length() - 4);
    }

    @Override
    /**
     * @return object as a string
     * @author Camden
     */
    public String toString() {
        String retval = "";

        retval += "Amino Acids: " + getAminoAcidLength() + "\n";
        
        for (int i = 0; i < getAminoAcidLength(); i++) {
            retval += getAminoAcid(i) + ", ";
        }

        retval += "\n---\n";

        retval += "Genes: " + getGeneLength() + "\n";

        for (int i = 0; i < getGeneLength(); i++) {
            retval += getGene(i) + ", ";
        }

        return retval;
    }

    @Override
    /**
     * @return boolean if equal or not
     * @author Camden
     */
    public boolean equals(Object obj) {
        boolean equal = false;
        if (getClass() != obj.getClass()) {
            GenomeAnalyzer other = (GenomeAnalyzer) obj;
            if (sequenceFile != null && sequenceFile.equals(other.sequenceFile)) {
                equal = true;
            }
        }

        return equal;
    }
    /**
     * @return a clone GenomeAnalyzer
     * @author Camden
     */
    public GenomeAnalyzer clone() {
        GenomeAnalyzer analyzer = null;
        try {
            analyzer = new GenomeAnalyzer(this);
        } catch (IOException e) {
            analyzer = null;
        }

        return analyzer;
    }
}