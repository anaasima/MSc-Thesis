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

    private static String getChainedActivityNames(List<String> activities) {
        StringBuilder sb = new StringBuilder();
        for (String activity : activities) {
            sb.append(activity);
            sb.append("_");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static String getXorPlace(List<Activity> fromActivities, List<Activity> toActivities) {
        StringBuilder fromPlace = new StringBuilder();
        fromActivities.forEach(a -> fromPlace.append(a.getName()).append("_"));

        StringBuilder toPlace = new StringBuilder();
        toActivities.forEach(a -> toPlace.append(a.getName()).append("_"));

        return getPlaceBetweenActivities(fromPlace.substring(0, fromPlace.length() - 1), toPlace.substring(0, toPlace.length() - 1));
    }

    public static List<Flow> getFlowsToActivity(String toActivity, String intermediatePlace) {
        List<Flow> flows = new ArrayList<>();

        flows.add(new Flow(intermediatePlace, toActivity + START_SUFFIX));
        flows.add(new Flow(toActivity + START_SUFFIX, toActivity + EXECUTING_SUFFIX));
        flows.add(new Flow(toActivity + EXECUTING_SUFFIX, toActivity + END_SUFFIX));

        return flows;
    }

    public static List<Flow> getFlowsBetweenActivities(String fromActivity, String toActivity, String intermediatePlace) {
        List<Flow> flows = new ArrayList<>();

        flows.add(new Flow(fromActivity + END_SUFFIX, intermediatePlace));
        flows.addAll(getFlowsToActivity(toActivity, intermediatePlace));
        return flows;
    }

    public static List<Flow> getFlowsBetweenActivityAndOsp(String fromActivity, List<Activity> orActivities, String intermediatePlace) {
        List<Flow> flows = new ArrayList<>();
        flows.add(new Flow(fromActivity + END_SUFFIX, intermediatePlace));

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
}
