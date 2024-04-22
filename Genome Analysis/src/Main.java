import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.io.File;
public class Main {
    public static void main(String[] args)throws IOException {
    
        ArrayList<AminoAcid> aminoAcids = new ArrayList<AminoAcid>();
        File aminoAcidTable = new File("src/aminoAcidTable.csv");
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
        for (AminoAcid aminoAcid : aminoAcids) {
            System.out.println(aminoAcid);
        }
    }
}
