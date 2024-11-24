public enum DeclareConstraintType {
    INIT("Init", 2),
    EXACTLY_ONE("Exactly1", 2),

    NOT_COEXISTENCE("Not Co-Existence", 3),
    COEXISTENCE("Co-Existence", 3),

    CHAIN_SUCCESSION("Chain Succession", 3),
    ALTERNATE_SUCCESSION("Alternate Succession", 3),
    ALTERNATE_RESPONSE("Alternate Response", 3),
    ALTERNATE_PRECEDENCE("Alternate Precedence", 3),
    SUCCESSION("Succession", 3),
    ;

    private String rumName;
    private int trailingPipes;

    DeclareConstraintType(String rumName, int trailingPipes) {
        this.rumName = rumName;
        this.trailingPipes = trailingPipes;
    }

    public String getRumName() {
        return rumName;
    }

    public int getTrailingPipes() {
        return trailingPipes;
    }
}
