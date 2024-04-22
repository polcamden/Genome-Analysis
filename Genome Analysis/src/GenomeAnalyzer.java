import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Takes a sequence file and amino acids file, analysis the amino acids and genes
 */
public class GenomeAnalyzer {
    private File sequenceFile;

    private AminoAcid[] aminoAcids;
    private ArrayList<Gene> genes;

    /**
     * Takes a Sequence file and aminoAcids, start analysis
     * @param sequenceFile sequence file
     * @throws IOException
     */
    public GenomeAnalyzer(File sequenceFile, AminoAcid[] aminoAcids) throws IOException
    {
        this.sequenceFile = sequenceFile;
        this.aminoAcids = aminoAcids;
        analyzeGenome();
    }

    /**
     * copys a genomeAnalyzer
     * @param genomeAnalyzer analyzer to clone
     * @throws IOException
     */
    public GenomeAnalyzer(GenomeAnalyzer genomeAnalyzer) throws IOException{
        sequenceFile = genomeAnalyzer.sequenceFile;
        aminoAcids = genomeAnalyzer.aminoAcids;
        analyzeGenome();
    }

    /**
     * analysis the amino acids and genes 
     * @throws IOException invalid file
     */
    private void analyzeGenome() throws IOException{
        Scanner in = new Scanner(sequenceFile);
        
        //reset aminoAcids and genes
        //cloneAminoAcids(aminoAcids); 
        genes = new ArrayList<>();

        //gets amino acids from file
        String line = in.nextLine();
        String[] tokens = line.split(",");
        
        for (int i = 0; i < tokens.length; i++) {
            for (AminoAcid aminoAcid : aminoAcids) {
                List<String> codons = aminoAcid.getCodons();

                for (int j = 0; j < codons.size(); j++) {
                    if(codons.get(j).equals(tokens[i])){
                        aminoAcid.countCodon(j);
                        break; //amino acid found
                    }
                }
            }
        }

        in.close();
    }

    /**
     * Clones amino acids resetting the counter
     * @param acids array of all acids
     */
    private void cloneAminoAcids(AminoAcid[] acids){
        aminoAcids = new AminoAcid[acids.length];
        
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

    public GenomeAnalyzer clone(){
        GenomeAnalyzer analyzer = null;
        try{
            analyzer = new GenomeAnalyzer(this);
        }catch(IOException e){
            analyzer = null;
        }

        return analyzer;
    }
}