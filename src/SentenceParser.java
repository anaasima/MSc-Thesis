import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public interface SentenceParser {
    void setInputFileName(String fileName);
    void setStatementMetadata(StatementMetadata statementMetadata);

    void handleInitialStatement(List<TerminalNode> initialTransition);
    void handleClosingStatement(List<TerminalNode> closingTransition);
    void handleActivity(List<TerminalNode> activityText);
    void handleAspDeclaration(String aspId);
    void handleOspDeclaration(String ospId);

    /**
     * After A ends, immediately start B.
     * Chain Succession(A,B)
     */
    void handlePreSequencePostSequence();

    /**
     * After A ends, immediately start B and start C and start (D_or_E).
     * AlternateSuccession(A,B)
     * AlternateSuccession(A,C)
     * AlternateSuccession(A,D_or_E)
     * Not Co-Existence(D,E)
     * Alternate Precedence(D,D_or_E)
     * AlternatePrecedence(E,D_or_E)
     * Exactly1(A)
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
     * AlternatePrecedence(D_and_E,A)
     * Not Co-Existence(B,C)
     * Not Co-Existence(B,D_and_E)
     * Not Co-Existence(C,D_and_E)
     * AlternateSuccession(D_and_E,D)
     * AlternateSuccession(D_and_E,E)
     */
    void handlePreOrPostSequence();

    /**
     * After A ends, eventually start B.
     * Succession(A,B)
     */
    void handePreEventuallyPostSequence();

    /**
     * Don't like how this one turned out
     * This one has problems
     * <p>
     *
     * After A ends and B ends and (D_or_E) ends, immediately start C.
     * Not Co-Existence(D,E)
     * AlternateSuccession(A,C)
     * AlternateSuccession(B,C)
     * AlternateSuccession(D_or_E,C)
     * Exactly1(D_or_E)
     * AlternateResponse(D,D_or_E)
     * AlternateResponse(E,D_or_E)
     * End(C)
     */
    void handlePreSequencePostAnd();

    /**
     * Don't like how this one turned out
     * The problem with this is that G_or_H appears more than once, even though We have the Exactly1(G_or_H). I think there is a problem with RuM
     * <p>
     *
     * After A ends and B ends and (D_or_E) ends, immediately start C and start F and start (G_or_H).
     * Exactly1(A)
     * Exactly1(B)
     * Exactly1(D_or_E)
     * Exactly1(G_or_H)
     * Not Co-Existence(D,E)
     * AlternateResponse(D,D_or_E)
     * AlternateResponse(E,D_or_E)
     * AlternateSuccession(A,C)
     * AlternateSuccession(B,C)
     * AlternateSuccession(D_or_E,C)
     * AlternateSuccession(A,F)
     * AlternateSuccession(B,F)
     * AlternateSuccession(D_or_E,F)
     * AlternateSuccession(A,G_or_H)
     * AlternateSuccession(B,G_or_H)
     * AlternateSuccession(D_or_E,G_or_H)
     * Not Co-Existence(G,H)
     * AlternatePrecedence(G,G_or_H)
     * AlternatePrecedence(H,G_or_H)
     */
    void handlePreAndPostAnd();

    /**
     * After A ends and B ends and (D_or_E) ends, immediately repeat since C.
     * same mapping as handlePreSequencePostAnd.
     */
    void handlePreRepeatSincePostAnd();

    /**
     * After A ends and B ends and (E_or_F) ends, immediately either start C or start D or start (G_and_H).
     * AlternateSuccession(A,or)
     * AlternateSuccession(B,or)
     * AlternateSuccession(E_or_F,or)
     * Not Co-Existence(E,F)
     * AlternateResponse(E,E_or_F)
     * AlternateResponse(F,E_or_F)
     * Exactly1(E_or_F)
     * Exactly1(A)
     * Exactly1(B)
     * AlternatePrecedence(C,or)
     * AlternatePrecedence(D,or)
     * AlternatePrecedence(G_and_H,or)
     * Not Co-Existence(C,D)
     * Not Co-Existence(C,G_and_H)
     * Not Co-Existence(D,G_and_H)
     * AlternateSuccession(G_and_H,G)
     * AlternateSuccession(G_and_H,H)
     */
    void handlePreOrPostAnd();

    /**
     * After A ends and B ends and (D_or_E) ends, eventually start C.
     * Succession(A,C)
     * Succession(B,C)
     * Succession(D_or_E,C)
     * Not Co-Existence(D,E)
     * AlternateResponse(D,D_or_E)
     * AlternateResponse(E,D_or_E)
     * Exactly1(A)
     * Exactly1(B)
     * Exactly1(C)
     * Exactly1(D_or_E)
     */
    void handePreEventuallyPostAnd();

    /**
     * After either A ends or B ends or (D_and_E) ends, immediately start C.
     * AlternateResponse(A,C)
     * AlternateResponse(B,C)
     * AlternateResponse(D_and_E,C)
     * Exactly1(C)
     * End(C)
     * Not Co-Existence(A,B)
     * Not Co-Existence(A,D_and_E)
     * Not Co-Existence(B,D_and_E)
     * AlternateSuccession(D,D_and_E)
     * AlternateSuccession(E,D_and_E)
     */
    void handlePreSequencePostOr();

    /**
     * After either A ends or B ends or (E_and_F) ends, immediately start C and start D and start (G_or_H).
     * AlternateResponse(A,and)
     * AlternateResponse(B,and)
     * AlternateResponse(E_and_F,and)
     * Not Co-Existence(A,B)
     * Not Co-Existence(A,E_and_F)
     * Not Co-Existence(B,E_and_F)
     * AlternateSuccession(E,E_and_F)
     * AlternateSuccession(F,E_and_F)
     * Exactly1(and)
     * Exactly1(G_or_H)
     * AlternateSuccession(and,C)
     * AlternateSuccession(and,D)
     * AlternateSuccession(and,G_or_H)
     * Not Co-Existence(G,H)
     * AlternatePrecedence(G,G_or_H)
     * AlternatePrecedence(H,G_or_H)
     */
    void handlePreAndPostOr();

    /**
     * After either A ends or B ends or (E_and_F) ends, immediately repeat since C.
     * Same mapping as handlePreSequencePostOr.
     */
    void handlePreRepeatSincePostOr();

    /**
     * After either A ends or B ends or (E_and_F) ends, immediately either start C or start D or start (G_and_H).
     * Not Co-Existence(A,B)
     * Not Co-Existence(A,E_and_F)
     * Not Co-Existence(B,E_and_F)
     * Exactly1(or)
     * AlternateResponse(A,or)
     * AlternateResponse(B,or)
     * AlternateResponse(E_and_F,or)
     * AlternateSuccession(E,E_and_F)
     * AlternateSuccession(F,E_and_F)
     * Not Co-Existence(C,D)
     * Not Co-Existence(C,G_and_H)
     * Not Co-Existence(D,G_and_H)
     * AlternatePrecedence(C,or)
     * AlternatePrecedence(D,or)
     * AlternatePrecedence(G_and_H,or)
     * AlternateSuccession(G_and_H,G)
     * AlternateSuccession(G_and_H,H)
     */
    void handlePreOrPostOr();

    /**
     * After either A ends or B ends or (D_and_E) ends, eventually start C.
     * AlternateResponse(A,C)
     * AlternateResponse(B,C)
     * AlternateResponse(D_and_E,C)
     * Exactly1(C)
     * End(C)
     * Not Co-Existence(A,B)
     * Not Co-Existence(A,D_and_E)
     * Not Co-Existence(B,D_and_E)
     * AlternateSuccession(D,D_and_E)
     * AlternateSuccession(E,D_and_E)
     */
    void handePreEventuallyPostOr();

    void printAndSaveModel();
}
