import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        // String filePath = "C:\\Users\\Ana-Maria\\IdeaProjects\\MSc-Thesis\\src\\grammar.txt";
        String filePath = "C:\\Users\\anton\\IdeaProjects\\MSc-Thesis\\src\\grammar.txt";
        CharStream input = new ANTLRInputStream(new FileInputStream(filePath));
        MScGrammarLexer lexer = new MScGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MScGrammarParser parser = new MScGrammarParser(tokens);

        // Empty listener, to test out the grammar
        // MScGrammarListener listener = new MScGrammarBaseListener();
        // Petri Net parser
        MScGrammarListener listener = new MscGrammarPetriNetListener();
        // Declare parser
        // MScGrammarListener listener = new MScGrammarDeclareListener();

        ParseTree tree = parser.description();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        System.out.println(); // print a \n after translation
    }
}
