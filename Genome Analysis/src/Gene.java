public class Gene {
    private String aminoAcidSequence;
    private int startNucleotide;
    private int endNucleotide;

    public Gene(String aminoAcidSequence, int startNucleotide, int endNucleotide) {
        this.aminoAcidSequence = aminoAcidSequence;
        this.startNucleotide = startNucleotide;
        this.endNucleotide = endNucleotide;
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

    public int getGeneLength() {
        return endNucleotide - startNucleotide + 1;
    }
}