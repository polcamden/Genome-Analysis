import java.util.List;

public class AminoAcid {
    private String fullName;
    private char singleLetterCode;
    private List<String> codons;
    private int[] codonCounts;

    public AminoAcid(String fullName, char singleLetterCode, List<String> codons) {
        this.fullName = fullName;
        this.singleLetterCode = singleLetterCode;
        this.codons = codons;

        codonCounts = new int[codons.size()];
    }

    public String getFullName() {
        switch (singleLetterCode) {
            case 'A':
                fullName = "Alanine";
                break;
            case 'R':
                fullName = "Arginine";
                break;
            case 'N':
                fullName = "Asparagine";
                break;
            case 'D':
                fullName = "Aspartic Acid";
                break;
            case 'C':
                fullName = "Cysteine";
                break;
            case 'Q':
                fullName = "Glutamine";
                break;
            case 'E':
                fullName = "Glutamic Acid";
                break;
            case 'G':
                fullName = "Glycine";
                break;
            case 'H':
                fullName = "Histidine";
                break;
            case 'I':
                fullName = "Isoleucine";
                break;
            case 'L':
                fullName = "Leucine";
                break;
            case 'K':
                fullName = "Lysine";
                break;
            case 'M':
                fullName = "Methionine";
                break;
            case 'F':
                fullName = "Phenylalanine";
                break;
            case 'P':
                fullName = "Proline";
                break;
            case 'S':
                fullName = "Serine";
                break;
            case 'T':
                fullName = "Threonine";
                break;
            case 'W':
                fullName = "Tryptophan";
                break;
            case 'Y':
                fullName = "Tyrosine";
                break;
            case 'V':
                fullName = "Valine";
                break;
            default:
                fullName = "Unknown";
        }
        return fullName;
    }

    public char getSingleLetterCode() {
        return singleLetterCode;
    }

    public List<String> getCodons() {
        return codons;
    }

    /**
     * gets the codonCounts array length
     * @return codonCounts array length
     */
    public int getCodonsLength(){
        return codonCounts.length;
    }

    /**
     * gets the codon count at i
     * @param i index
     * @return codon count as a int
     */
    public int getCodonCount(int i){
        return codonCounts[i];
    }

    /**
     * adds 1 to codonCount at i
     * @param i index
     */
    public void countCodon(int i){
        codonCounts[i]++;
    }

    @Override
    public String toString(){
        String str = getFullName() + '\n';

        for (int i = 0; i < codonCounts.length; i++) {
            str += codons.get(i) + " : " + codonCounts[i] + '\n';
        }

        return str;
    }
}