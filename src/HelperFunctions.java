import org.antlr.v4.codegen.model.decl.Decl;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class HelperFunctions {
    public static final String END_SUFFIX = "_end";
    public static final String START_SUFFIX = "_start";
    public static final String EXECUTING_SUFFIX = "_executing";

    public static String getActivityText(List<TerminalNode> words) {
        StringBuilder sb = new StringBuilder();
        for (TerminalNode word : words) {
            sb.append(word.getText());
            sb.append("_");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static String getActivityTextStart(List<TerminalNode> words) {
        return getActivityText(words) + START_SUFFIX;
    }

    public static String getActivityTextEnd(List<TerminalNode> words) {
        return getActivityText(words) + END_SUFFIX;
    }

    public static String getActivityIntermediatePlaceText(List<TerminalNode> words) {
        return getActivityText(words) + EXECUTING_SUFFIX;
    }

    public static String getPlaceBetweenActivities(String fromActivity, String toActivity) {
        return fromActivity + "__" + toActivity;
    }

    public static String getSilentTransitionName(String activityName, int number) {
        return "silent__" + activityName + "__" + number;
    }

    public static String getChainedActivityNames(List<Activity> activities) {
        StringBuilder sb = new StringBuilder();
        for (Activity activity : activities) {
            sb.append(activity.getName());
            sb.append("_");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static String getIntermediatePlace(List<Activity> fromActivities, List<Activity> toActivities) {
        StringBuilder fromPlace = new StringBuilder();
        fromActivities.forEach(a -> fromPlace.append(a.getName()).append("_"));

        StringBuilder toPlace = new StringBuilder();
        toActivities.forEach(a -> toPlace.append(a.getName()).append("_"));

        return getPlaceBetweenActivities(fromPlace.substring(0, fromPlace.length() - 1), toPlace.substring(0, toPlace.length() - 1));
    }

    public static List<Flow> getFlowsToActivity(String toActivity, String intermediatePlace) {
        return getFlowsToActivityOrSilentTransition(toActivity, intermediatePlace, false);
    }

    public static List<Flow> getFlowsToActivityOrSilentTransition(String toActivity, String intermediatePlace, boolean isSilentToActivity) {
        List<Flow> flows = new ArrayList<>();

        if (isSilentToActivity) {
            flows.add(new Flow(intermediatePlace, toActivity));
        } else {
            flows.add(new Flow(intermediatePlace, toActivity + START_SUFFIX));
            flows.add(new Flow(toActivity + START_SUFFIX, toActivity + EXECUTING_SUFFIX));
            flows.add(new Flow(toActivity + EXECUTING_SUFFIX, toActivity + END_SUFFIX));
        }

        return flows;
    }

    public static List<Flow> getFlowsBetweenActivities(String fromActivity, String toActivity, String intermediatePlace, boolean isSilentFromActivity) {
        List<Flow> flows = new ArrayList<>();

        String flowFrom = isSilentFromActivity ? fromActivity : fromActivity + END_SUFFIX;
        flows.add(new Flow(flowFrom, intermediatePlace));
        flows.addAll(getFlowsToActivity(toActivity, intermediatePlace));
        return flows;
    }

    public static List<Flow> getFlowsBetweenActivityAndOsp(List<Activity> orActivities, String intermediatePlace) {
        List<Flow> flows = new ArrayList<>();

        for (Activity activity : orActivities) {
            flows.add(new Flow(intermediatePlace, activity.getName() + START_SUFFIX));
            flows.add(new Flow(activity.getName() + START_SUFFIX, activity.getName() + EXECUTING_SUFFIX));
            flows.add(new Flow(activity.getName() + EXECUTING_SUFFIX, activity.getName() + END_SUFFIX));
        }

        return flows;
    }

    public static List<Flow> getFlowsBetweenActivityAndAsp(String silentTransition, List<Activity> andActivities) {
        List<Flow> flows = new ArrayList<>();

        for (Activity activity : andActivities) {
            String intermediatePlace = getPlaceBetweenActivities(silentTransition, activity.getName());
            flows.add(new Flow(silentTransition, intermediatePlace));
            flows.add(new Flow(intermediatePlace, activity.getName() + START_SUFFIX));
            flows.add(new Flow(activity.getName() + START_SUFFIX, activity.getName() + EXECUTING_SUFFIX));
            flows.add(new Flow(activity.getName() + EXECUTING_SUFFIX, activity.getName() + END_SUFFIX));
        }

        return flows;
    }

    public static List<Flow> getFlowsFromOsp(List<Activity> orActivities, String intermediatePlace) {
        List<Flow> flows = new ArrayList<>();

        for (Activity activity : orActivities) {
            flows.add(new Flow(activity.getName() + END_SUFFIX, intermediatePlace));
        }

        return flows;
    }

    public static List<Flow> getFlowsBetweenAspAndActivity(String silentTransition, List<Activity> andActivities) {
        List<Flow> flows = new ArrayList<>();

        for (Activity activity : andActivities) {
            String intermediatePlace = getPlaceBetweenActivities(activity.getName(), silentTransition);
            flows.add(new Flow(activity.getName() + END_SUFFIX, intermediatePlace));
            flows.add(new Flow(intermediatePlace, silentTransition));
        }

        return flows;
    }

    public static List<DeclareConstraint> getConstraintsForAspPreActivity(String fromActivity, List<String> aspActivities) {
        List<DeclareConstraint> constraints = new ArrayList<>();

        aspActivities.forEach(a -> constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_PRECEDENCE, fromActivity, a)));

        return constraints;
    }

    public static List<DeclareConstraint> getConstraintsForOspPreActivity(String fromActivity, List<String> ospActivities) {
        List<DeclareConstraint> constraints = new ArrayList<>();

        ospActivities.forEach(a -> constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_PRECEDENCE, fromActivity, a)));
        constraints.addAll(getNotCoExistenceConstraintsForOr(ospActivities));

        return constraints;
    }

    public static List<DeclareConstraint> getConstraintsForOspPostActivity(String toActivity, List<String> ospActivities) {
        List<DeclareConstraint> constraints = new ArrayList<>();

        ospActivities.forEach(a -> constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_RESPONSE, a, toActivity)));
        constraints.addAll(getNotCoExistenceConstraintsForOr(ospActivities));

        return constraints;
    }

    public static List<DeclareConstraint> getNotCoExistenceConstraintsForOr(List<String> orActivities) {
        List<DeclareConstraint> constraints = new ArrayList<>();

        for (int i = 0; i < orActivities.size() - 1; i++) {
            for (int j = i + 1; j < orActivities.size(); j++) {
                constraints.add(new DeclareConstraint(DeclareConstraintType.NOT_COEXISTENCE, orActivities.get(i), orActivities.get(j)));
            }
        }

        return constraints;
    }

    public static List<DeclareConstraint> getCoExistenceConstraintsForAnd(List<String> andActivities) {
        List<DeclareConstraint> constraints = new ArrayList<>();

        for (int i = 0; i < andActivities.size() - 1; i++) {
            for (int j = i + 1; j < andActivities.size(); j++) {
                constraints.add(new DeclareConstraint(DeclareConstraintType.COEXISTENCE, andActivities.get(i), andActivities.get(j)));
            }
        }

        return constraints;
    }
}
