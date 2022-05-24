package main;

import lib.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NewMain {
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        try {

            String inputFile = readInputFile("./src/data/Vaccum_NLP.txt");
            String input = cleanText(inputFile);
//            System.out.println(input);
            ANTLRInputStream inputStream = new ANTLRInputStream(input);
            New_grammarLexer textToAADLLexer = new New_grammarLexer(inputStream);
            CommonTokenStream tokens = new CommonTokenStream(textToAADLLexer);
            New_grammarParser parser = new New_grammarParser(tokens);
            Visitor visitor1= new Visitor();
            visitor1.visit(parser.nlparch());
            for(int i=0;i<=visitor1.index_names-1;i++){
                System.out.println(visitor1.system_declaration[i][0]);
                System.out.println(visitor1.system_declaration[i][1]);
            }

            for(int i=0;i<=visitor1.ind_system_in_features-1;i++)
            {
                System.out.println(visitor1.system_in_features[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String readInputFile(String path) throws FileNotFoundException {
        File NLP_Output = new File(path);
        Scanner sc = new Scanner(NLP_Output);
        String text = "";
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            text += s+"\n";
        }
        return text;
    }
    public static String cleanText(String s){
        return s;
    }
}