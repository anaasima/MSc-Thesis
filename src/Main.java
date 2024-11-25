import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.util.List;

public class Main {
    public static final String FOLDER_PATH = "C:\\Users\\anton\\IdeaProjects\\MSc-Thesis\\src\\examples\\";

    public static void main(String[] args) throws Exception {
        // String filePath = "C:\\Users\\Ana-Maria\\IdeaProjects\\MSc-Thesis\\src\\grammar.txt";
        String fileName = "pre_sequence_post_or";
        String filePath = FOLDER_PATH + "input\\" + fileName + ".txt";
        CharStream input = new ANTLRInputStream(new FileInputStream(filePath));
        MScGrammarLexer lexer = new MScGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MScGrammarParser parser = new MScGrammarParser(tokens);

        SentenceParser petriNetParser = new MscGrammarPetriNetListener();
        SentenceParser declareParser = new MScGrammarDeclareListener();

        List<SentenceParser> parsers = List.of(petriNetParser);
//        List<SentenceParser> parsers = List.of(declareParser);
        for (SentenceParser sentenceParser : parsers) {
            sentenceParser.setInputFileName(fileName);
            MScGrammarListener listener = new MScGrammarContextListener(sentenceParser);
            ParseTree tree = parser.description();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listener, tree);
            System.out.println(); // print a \n after translation
        }
    }
}
