import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatementMetadata {
    private StatementType statementType;
    private PreActivityType preActivityType;
    private PostActivityType postActivityType;
    private ContextType currentContextType;

    private final List<Activity> preActivities;
    private final List<Activity> postActivities;

    public StatementMetadata() {
        preActivities = new ArrayList<>();
        postActivities = new ArrayList<>();
    }

    public ContextType getCurrentContextType() {
        return currentContextType;
    }

    public void setCurrentContextType(ContextType currentContextType) {
        this.currentContextType = currentContextType;
    }

    public StatementType getStatementType() {
        return statementType;
    }

    public void setStatementType(StatementType statementType) {
        this.statementType = statementType;
    }

    public PreActivityType getPreActivityType() {
        return preActivityType;
    }

    public void setPreActivityType(PreActivityType preActivityType) {
        this.preActivityType = preActivityType;
    }

    public PostActivityType getPostActivityType() {
        return postActivityType;
    }

    public void setPostActivityType(PostActivityType postActivityType) {
        this.postActivityType = postActivityType;
    }

    public void addActivity(Activity activity) {
        if (currentContextType == ContextType.PRE_ACTIVITY_IMMEDIATELY || currentContextType == ContextType.PRE_ACTIVITY_EVENTUALLY) {
            preActivities.add(activity);
        } else if (currentContextType == ContextType.POST_ACTIVITY) {
            postActivities.add(activity);
        }
    }

    public List<Activity> getPreActivities() {
        return preActivities;
    }

    public List<Activity> getPostActivities() {
        return postActivities;
    }

    @Override
    public String toString() {
        return "StatementMetadata{" +
                "statementType=" + statementType +
                ", preActivityType=" + preActivityType +
                ", postActivityType=" + postActivityType +
                ", preActivities=" + preActivities +
                ", postActivities=" + postActivities +
                ", currentContextType=" + currentContextType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatementMetadata that = (StatementMetadata) o;
        return statementType == that.statementType &&
               preActivityType == that.preActivityType &&
               postActivityType == that.postActivityType &&
               currentContextType == that.currentContextType &&
               Objects.equals(preActivities, that.preActivities) &&
               Objects.equals(postActivities, that.postActivities);
    }
}
