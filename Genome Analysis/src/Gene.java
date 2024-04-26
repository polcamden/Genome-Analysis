/**
 * This class stores the data for one Gene
 * @author Nidhi
 */
public class Gene {
    private String aminoAcidSequence;
    private int startNucleotide;
    private int endNucleotide;

    public Gene(int startNucleotide){
        this.startNucleotide = startNucleotide;
        this.endNucleotide = startNucleotide;
        aminoAcidSequence = "";
    }

    public String getAminoAcidSequence() {
        return aminoAcidSequence;
    }

    public int getStartNucleotide() {
        return startNucleotide;
    }

    public int getEndNucleotide() {
        return endNucleotide;
    }

    public int getAminoAcidLength() {
        return (endNucleotide - startNucleotide + 1) / 3;
    }

    public int getNucleotideLength() {
        return endNucleotide - startNucleotide + 1;
    }

    /**
     * adds a amino acid to the sequence
     * @param aminoAcid amino acid single letter code
     *
     */
    public void addAminoAcid(char aminoAcid){
        aminoAcidSequence += aminoAcid;
        endNucleotide += 3;
    }

    public String toString() {
        return startNucleotide + ".." + endNucleotide + "\n" + aminoAcidSequence;
    }
}