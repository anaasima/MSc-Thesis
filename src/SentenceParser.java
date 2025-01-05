import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public interface SentenceParser {
    void setInputFileName(String fileName);
    void setStatementMetadata(StatementMetadata statementMetadata);

    void handleInitialStatement(List<TerminalNode> initialTransition);
    void handleClosingStatementSequence();
    void handleClosingStatementAnd();
    void handleClosingStatementOr();
    void handleActivity(List<TerminalNode> activityText);
    void handleAspDeclaration(String aspId);
    void handleOspDeclaration(String ospId);

    /**
     * After A ends, immediately start B.
     * Chain Succession(A,B)
     * DONE
     */
    void handlePreSequencePostSequence();

    /**
     * Here we have some problems with the design of the model, look through notes to see what's it about
     * UPDATE: Apparently not anymore? OLD: Here we must do Not Chain Succession between initial and end node and vice versa
     * After A ends, immediately start B and start C and start (D_or_E).
     * AlternateSuccession(A,B)
     * AlternateSuccession(A,C)
     * AlternatePrecedence(D,A)
     * AlternatePrecedence(E,A)
     * Co-Existence(B,C) - this one should be between all AND branches
     * Not Co-Existence(D,E) - this one because they're part of an XOR
     * Exactly1(A)
     * DONE
     */
    void handlePreAndPostSequence();

    /**
     * After A ends, immediately repeat since B.
     * same mapping as handlePreSequencePostSequence.
     */
    void handlePreRepeatSincePostSequence();

    /**
     * After A ends, immediately either start B or start C or start (D_and_E).
     * Exactly1(A)
     * AlternatePrecedence(B,A)
     * AlternatePrecedence(C,A)
     * AlternatePrecedence(D,A)
     * AlternatePrecedence(E,A)
     * Not Co-Existence(B,C)
     * Not Co-Existence(B,D) // or instead of D any other branch in the AND group
     * Not Co-Existence(C,D) // same as above
     * Co-Existence(D,E)
     * DONE
     */
    void handlePreOrPostSequence();

    /**
     * After A ends, eventually start B.
     * Succession(A,B)
     */
    void handePreEventuallyPostSequence();

    /**
     * Same problem as in handlePreAndPostSequence
     * After A ends and B ends and (D_or_E) ends, immediately start C.
     * AlternateSuccession(A,C)
     * AlternateSuccession(B,C)
     * AlternateResponse(D,C)
     * AlternateResponse(E,C)
     * Not Co-Existence(D,E)
     * DONE
     */
    void handlePreSequencePostAnd();

    /**
     * Not going to implement anymore
     */
    void handlePreAndPostAnd();

    /**
     * After A ends and B ends and (D_or_E) ends, immediately repeat since C.
     * same mapping as handlePreSequencePostAnd.
     * DONE
     */
    void handlePreRepeatSincePostAnd();

    /**
     * Not going to implement anymore
     */
    void handlePreOrPostAnd();

    /**
     * After A ends and B ends and (D_or_E) ends, eventually start C.
     * Same implementation as in handlePreSequencePostAnd
     */
    void handePreEventuallyPostAnd();

    /**
     * After either A ends or B ends or (D_and_E) ends, immediately start C.
     * AlternateResponse(A,C)
     * AlternateResponse(B,C)
     * AlternateResponse(D,C)
     * AlternateResponse(E,C)
     * Co-Existence(D,E)
     * Not Co-Existence(A,B)
     * Not Co-Existence(A,D) // instead of D can be any other branch from the AND group
     * Not Co-Existence(B,D) // same as above
     * Exactly1(C)
     * Not Chain Succession(initial node before A, B and D_and_E, C)
     * DONE
     */
    void handlePreSequencePostOr();

    /**
     * Not going to implement anymore
     */
    void handlePreAndPostOr();

    /**
     * After either A ends or B ends or (E_and_F) ends, immediately repeat since C.
     * Same mapping as handlePreSequencePostOr.
     */
    void handlePreRepeatSincePostOr();

    /**
     * Not going to implement anymore
     */
    void handlePreOrPostOr();

    /**
     * After either A ends or B ends or (D_and_E) ends, eventually start C.
     * Same implementation as in handlePreEventuallyPostOr
     */
    void handePreEventuallyPostOr();

    void printAndSaveModel();
}
