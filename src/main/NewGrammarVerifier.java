package main;

import lib.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileInputStream;

public class NewGrammarVerifier {
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args){
        try {
            File NLPArchFile = new File("./src/data/CoffeeMaker2.txt");
            FileInputStream is = new FileInputStream(NLPArchFile);
            New_grammarLexer new_grammarLexer = new New_grammarLexer(CharStreams.fromStream(is));
            CommonTokenStream tokens = new CommonTokenStream(new_grammarLexer);
            New_grammarParser new_grammarParser = new New_grammarParser(tokens);
            new_grammarParser.removeErrorListeners();
            new_grammarParser.nlparch();
            Visitor visitor=new Visitor();
            ParseTree tree = new_grammarParser.nlparch();
            ParseTreeWalker walker = new ParseTreeWalker();
            System.out.println(visitor.visit(tree));
            New_grammarListener listener = new New_grammarBaseListener();
            walker.walk(listener, tree);
        } catch(Exception e){
            e.printStackTrace();
        }
    }


}