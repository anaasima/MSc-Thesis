import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class HelperFunctions {
    public static String getActivityText(List<TerminalNode> words) {
        StringBuilder sb = new StringBuilder();
        for (TerminalNode word : words) {
            sb.append(word.getText());
            sb.append("_");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
