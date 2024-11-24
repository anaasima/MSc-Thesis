import java.util.Objects;

public class DeclareConstraint {
    private DeclareConstraintType type;
    private String from;
    private String to;

    public DeclareConstraint(DeclareConstraintType type, String from, String to) {
        this.type = type;
        this.from = from;
        this.to = to;
    }

    public DeclareConstraintType getType() {
        return type;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getRumString() {
        StringBuilder baseString = new StringBuilder(type.getRumName() + "[" + from);
        if (to != null) {
            baseString.append(", ").append(to);
        }
        baseString.append("]");
        baseString.append(" |".repeat(type.getTrailingPipes()));

        return baseString.toString();
    }

    @Override
    public String toString() {
        String basePrint = type + "(" + from;
        if (to != null) {
            basePrint += ", " + to;
        }
        basePrint += ")";
        return basePrint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeclareConstraint that = (DeclareConstraint) o;
        return type == that.type && Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(type);
        result = 31 * result + Objects.hashCode(from);
        result = 31 * result + Objects.hashCode(to);
        return result;
    }
}
