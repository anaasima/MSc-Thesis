import java.util.Objects;

public class Flow {
    private String from;
    private String to;

    public Flow(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return from + " => " + to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flow flow = (Flow) o;
        return Objects.equals(from, flow.from) && Objects.equals(to, flow.to);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(from);
        result = 31 * result + Objects.hashCode(to);
        return result;
    }
}
