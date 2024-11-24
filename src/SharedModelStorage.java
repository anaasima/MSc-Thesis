import java.util.*;

public class SharedModelStorage {
    private final Set<String> transitions = new HashSet<>();
    private final Map<String, List<Activity>> andSubProcesses = new HashMap<>();
    private final Map<String, List<Activity>> orSubProcesses = new HashMap<>();
    private static SharedModelStorage instance;

    private SharedModelStorage() {
    }

    public static SharedModelStorage getInstance() {
        if(instance == null) {
            instance = new SharedModelStorage();
        }

        return instance;
    }

    public void addTransition(String transition) {
        transitions.add(transition);
    }

    public void addAndSubProcess(String id, List<Activity> activities) {
        andSubProcesses.put(id, activities);
    }

    public void addOrSubProcess(String id, List<Activity> activities) {
        orSubProcesses.put(id, activities);
    }

    public List<Activity> getAndSubProcess(String id) {
        return andSubProcesses.get(id);
    }

    public List<Activity> getOrSubProcess(String id) {
        return orSubProcesses.get(id);
    }

    public List<String> getAndSubProcessNames(String id) {
        return andSubProcesses.get(id).stream().map(Activity::getName).toList();
    }

    public List<String> getOrSubProcessNames(String id) {
        return orSubProcesses.get(id).stream().map(Activity::getName).toList();
    }

    public Set<String> getTransitions() {
        return transitions;
    }
}
