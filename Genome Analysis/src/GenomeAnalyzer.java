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

    // I switched out the raw array for an ArrayList! All methods have been
    // converted to use that accordingly...
    // However, the original code that uses arrays is still there and it all
    // functions the same regardless!
    // private AminoAcid[] aminoAcids;
    private ArrayList<AminoAcid> aminoAcids;

    private ArrayList<Gene> genes;

    final int ORF = 50;

    /**
     * Takes a Sequence file and aminoAcids, start analysis
     * 
     * @param sequenceFile sequence file
     * @throws IOException
     */
    // public GenomeAnalyzer(File sequenceFile, AminoAcid[] aminoAcids) throws
    // IOException
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
     */
    public GenomeAnalyzer(GenomeAnalyzer genomeAnalyzer) throws IOException {
        sequenceFile = genomeAnalyzer.sequenceFile;
        aminoAcids = genomeAnalyzer.aminoAcids;
        analyzeGenome();
    }

    // Note from Gianna: I made this method public, it was private
    // before (lol)

    /**
     * analysis the amino acids and genes
     * 
     * @throws IOException invalid file
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
     */
    public AminoAcid getAminoAcid(int i) {
        // return aminoAcids[i];
        return aminoAcids.get(i);
    }

    /**
     * gets the length of aminoAcid array
     * 
     * @return aminoAcids array length
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
     */
    public Gene getGene(int i) {
        return genes.get(i);
    }

    /**
     * gets the length of genes list
     * 
     * @return genes arrayList size
     */
    public int getGeneLength() {
        return genes.size();
    }

    public String getSequenceFileName(){

        String str = sequenceFile.getName();

        return str.substring(0, str.length() - 4);
    }

    @Override
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