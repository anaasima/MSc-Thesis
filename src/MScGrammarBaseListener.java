// Generated from C:/Users/Ana-Maria/IdeaProjects/MSc-Thesis/src/MScGrammar.g4 by ANTLR 4.13.2

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * This class provides an empty implementation of {@link MScGrammarListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
@SuppressWarnings("CheckReturnValue")
public class MScGrammarBaseListener implements MScGrammarListener {
	private static final String INITIAL_PLACE = "pn_initial";
	private static final String FINAL_PLACE = "pn_final";

	private final Set<String> places = new HashSet<>();
	private final Set<String> transitions = new HashSet<>();
	private final Set<Flow> flows = new HashSet<>();

	private Set<String> preActivityTransitions;
	private Set<String> postActivityTransitions;

	@Override
	public void enterClosingStatement(MScGrammarParser.ClosingStatementContext ctx) {
		places.add(FINAL_PLACE);
	}

	@Override
	public void exitClosingStatement(MScGrammarParser.ClosingStatementContext ctx) {
		// TODO: implement only one activity for the closing statement post activity expression
		flows.add(new Flow(postActivityTransitions.stream().findFirst().orElse(null), FINAL_PLACE));
	}

	@Override
	public void enterPostActivityExpression(MScGrammarParser.PostActivityExpressionContext ctx) {
		postActivityTransitions = new HashSet<>();
	}

	@Override
	public void exitPostActivityExpression(MScGrammarParser.PostActivityExpressionContext ctx) {
		for (MScGrammarParser.ActivityContext activity : ctx.activity()) {
			postActivityTransitions.add(HelperFunctions.getActivityText(activity.WORD()));
		}
	}

	@Override
	public void enterPreActivityExpression(MScGrammarParser.PreActivityExpressionContext ctx) {
		preActivityTransitions = new HashSet<>();
	}

	@Override
	public void exitPreActivityExpression(MScGrammarParser.PreActivityExpressionContext ctx) {
		for (MScGrammarParser.ActivityContext activity : ctx.activity()) {
			preActivityTransitions.add(HelperFunctions.getActivityText(activity.WORD()));
		}
	}

	@Override
	public void enterStatement(MScGrammarParser.StatementContext ctx) {
	}

	@Override
	public void exitStatement(MScGrammarParser.StatementContext ctx) {
		for (String postActivityTransition : postActivityTransitions) {
			for (String preActivityTransition : preActivityTransitions) {
				String place = postActivityTransition + "__" + preActivityTransition;
				places.add(place);
				flows.add(new Flow(postActivityTransition, place));
				flows.add(new Flow(place, preActivityTransition));
			}
		}
	}

	@Override
	public void exitActivity(MScGrammarParser.ActivityContext ctx) {
		transitions.add(HelperFunctions.getActivityText(ctx.WORD()));
	}

	@Override
	public void enterInitialStatement(MScGrammarParser.InitialStatementContext ctx) {
		places.add(INITIAL_PLACE);
	}

	@Override
	public void exitInitialStatement(MScGrammarParser.InitialStatementContext ctx) {
		// TODO: implement only one activity for the initial statement pre activity expression
		flows.add(new Flow(INITIAL_PLACE, preActivityTransitions.stream().findFirst().orElse(null)));
	}


	@Override
	public void visitErrorNode(ErrorNode node) {
		System.out.println("Visit error node: " + node);
	}

	@Override
	public void exitDescription(MScGrammarParser.DescriptionContext ctx) {
		System.out.println("Places: " + places);
		System.out.println("Transitions: " + transitions);
		System.out.println("Flows: " + flows);

        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(new FileWriter("./petri_net.tpn"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

		for (String place: places) {
			printWriter.printf("place %s init %d;\n", place, place.equals(INITIAL_PLACE) ? 1 : 0);
		}
		printWriter.println();

		for (String transition: transitions) {
			List<String> inPlaces = new ArrayList<>();
			List<String> outPlaces = new ArrayList<>();

			for (Flow flow: flows) {
				if (flow.getFrom().equals(transition)) {
					outPlaces.add(flow.getTo());
				} else if (flow.getTo().equals(transition)) {
					inPlaces.add(flow.getFrom());
				}
			}

			printWriter.printf("trans %s in %s out %s;\n", transition, getPlacesString(inPlaces), getPlacesString(outPlaces));
		}

		printWriter.close();
	}

	private String getPlacesString(List<String> places) {
		StringBuilder builder = new StringBuilder();
		for (String place: places) {
			builder.append(place);
			builder.append(" ");
		}
		return builder.toString().trim();
	}

	// -----------------------------------------------------------------------------------------------
	// Methods below this line are unused
	// -----------------------------------------------------------------------------------------------

	@Override
	public void enterDescription(MScGrammarParser.DescriptionContext ctx) { }

	@Override
	public void enterLeadingStatement(MScGrammarParser.LeadingStatementContext ctx) { }

	@Override
	public void exitLeadingStatement(MScGrammarParser.LeadingStatementContext ctx) { }

	@Override
	public void enterStatementList(MScGrammarParser.StatementListContext ctx) { }

	@Override
	public void exitStatementList(MScGrammarParser.StatementListContext ctx) { }

	@Override
	public void enterEveryRule(ParserRuleContext ctx) { }

	@Override
	public void exitEveryRule(ParserRuleContext ctx) { }

	@Override
	public void visitTerminal(TerminalNode node) { }

	@Override
	public void enterActivity(MScGrammarParser.ActivityContext ctx) { }
}