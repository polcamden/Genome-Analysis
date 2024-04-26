/**
 * This class lets the user choose a Covid-19 DNA reading frame and decide how to analyze it 
 * 4-25-24
 * section 200
 * @author Camden
 * @author Nidhi
 * @author Gianna
 */
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
    
        //create reading frame files
        File RF1 = new File("src/genomeLabNeededFiles/covidSequenceRF1.csv");
        File RF2 = new File("src/genomeLabNeededFiles/covidSequenceRF2.csv");
        File RF3 = new File("src/genomeLabNeededFiles/covidSequenceRF3.csv");

        //ask what reading frame
        Scanner kbd = new Scanner(System.in);
        System.out.println("*** Covid-19 DNA Sequence Analyzer ***");
        System.out.println("Please choose which reading frame to analyze:");
        System.out.println("1: Reading frame 1\n2. Reading frame 2\n3. Reading frame 3");
        System.out.print("Enter your choice: ");
        int choice = kbd.nextInt();

        switch(choice){
            case 1: 
                GenomeAnalyzer RF1Analyzer = new GenomeAnalyzer(RF1, aminoAcids);
                runMenu(RF1Analyzer, aminoAcids);
                break;
            case 2:
                GenomeAnalyzer RF2Analyzer = new GenomeAnalyzer(RF2, aminoAcids);
                runMenu(RF2Analyzer, aminoAcids);
                break;
            case 3:
                GenomeAnalyzer RF3Analyzer = new GenomeAnalyzer(RF3, aminoAcids);
                runMenu(RF3Analyzer, aminoAcids);
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 3: ");
                choice = kbd.nextInt();
                break;
        }
        kbd.close();

    }
    /**
     * This method prints the menu used after the user chooses the reading frame and calls other methods
     * @param chosenReadingFrame reading frame used to analyze
     * @param aminoAcids ArrayList of amino acids
     * @throws FileNotFoundException
     * @author Nidhi
     * @author Gianna
     */
    public static void runMenu(GenomeAnalyzer chosenReadingFrame, ArrayList<AminoAcid> aminoAcids) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHow would you like to analyze?:");
            System.out.println("1. Codon bias for a single amino acid");
            System.out.println("2. Print a codon bias report");
            System.out.println("3. Print a gene analysis report");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the single-letter code: ");
                    char letter = scanner.nextLine().charAt(0);
                    lookupAminoAcid(letter, aminoAcids);
                    break;
                case 2:
                    printFileAminoAcids(chosenReadingFrame);
                    System.out.println("Your file can be found in _CodonBias.txt");
                    break;
                case 3:
                    printFileGeneAnalysis(chosenReadingFrame);
                    System.out.println("Your file can be found in _GeneAnalysis.txt");
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    choice = scanner.nextInt();
            }
        }
    }
    /**
     * This method prints to the screen the codon bias for a specific amino acid
     * @param letter the letter code of the animo acid
     * @param aminoAcids the ArrayList of amino acids 
     * @author Nidhi
     * @author Gianna
     */
    public static void lookupAminoAcid(char letter, ArrayList<AminoAcid> aminoAcids) {
        for (AminoAcid aminoAcid : aminoAcids) {
            if (aminoAcid.getSingleLetterCode() == letter) {
                System.out.println("\n" + aminoAcid);
                return;
            }
        }
        System.out.println("Amino acid not found for the given letter.");
    }
    /**
     * This method uses the GenomeAnalyzer class to print the codon bias file for the chosen reading frame
     * @param genomeAnalyzer the analyzer for the chosen reading frame
     * @throws FileNotFoundException
     * @author Camden
     */
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
     * This method uses the GenomeAnalyzer class to print gene analysis file for the chosen reading frame
     * @param genomeAnalyzer genomeAnalyzer for the chosen reading frame
     * @throws FileNotFoundException
     * @author Camden
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
