import java.util.List;

public class AminoAcid {
    private String fullName;
    private char singleLetterCode;
    private List<String> codons;

    public AminoAcid(String fullName, char singleLetterCode, List<String> codons) {
        this.fullName = fullName;
        this.singleLetterCode = singleLetterCode;
        this.codons = codons;
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

    @Override
    public String toString() {
        return "AminoAcid " + fullName + ", " + singleLetterCode + ", " + codons;
    }
    
}
