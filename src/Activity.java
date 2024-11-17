import java.util.Objects;

public class Activity {
    private final String name;
    private final ActivityType type;

    public Activity(String name, ActivityType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ActivityType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;
        return Objects.equals(name, activity.name) && type == activity.type;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(type);
        return result;
    }
}
