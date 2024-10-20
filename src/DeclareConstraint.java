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
    public boolean equals(Object obj) {
        return toString().equals(obj.toString());
    }
}
