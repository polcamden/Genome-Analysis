import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Takes a sequence file and amino acids file, analysis the amino acids and genes
 */
public class GenomeAnalyzer {
    private File sequenceFile;

    private AminoAcid[] aminoAcids;
    private ArrayList<Gene> genes;

    /**
     * Takes a Sequence file, does not start analysis
     * @param sequenceFile Genome sequence file
     */
    public GenomeAnalyzer(File sequenceFile, File AminoAcidsFile) throws IOException
    {
        this.sequenceFile = sequenceFile;
        loadAminoAcids(AminoAcidsFile);
        analyzeGenome();
    }

    /**
     * analysis the amino acids and genes 
     * @throws IOException invalid file
     */
    private void analyzeGenome() throws IOException{
        Scanner in = new Scanner(sequenceFile);
        
        /*aminoAcidCounts = new int[aminoAcids.length];
        aminoAcidTotal = 0;*/
        genes = new ArrayList<>();


        in.close();
    }

    /**
     * Loads amino acids from a csv file
     * @param file file location
     */
    private void loadAminoAcids(File file){

    }

    /**
     * gets the AminoAcid in aminoAcids array at i
     * @param i index
     * @return AminoAcid
     */
    public AminoAcid getAminoAcid(int i){
        return aminoAcids[i];
    }

    /**
     * gets the length of aminoAcid array
     * @return aminoAcids array length
     */
    public int getAminoAcidLength(){
        return aminoAcids.length;
    }

    /**
     * gets the Gene in genes list at i
     * @param i index
     * @return Gene
     */
    public Gene getGene(int i){
        return genes.get(i);
    }

    /**
     * gets the length of genes list
     * @return genes arrayList size
     */
    public int getGeneLength(){
        return genes.size();
    }

    @Override
    public String toString() {
        return "GenomeAnalyzer File " + sequenceFile;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;
        if(getClass() != obj.getClass()){
            GenomeAnalyzer other = (GenomeAnalyzer) obj;
            if(sequenceFile != null && sequenceFile.equals(other.sequenceFile)){
                equal = true;
            }
        }

        return equal;
    }
}