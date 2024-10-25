public enum DeclareConstraintType {
    INIT("Init", 2),
    // END,
    // CHAIN_RESPONSE,
    CHAIN_SUCCESSION("Chain Succession", 3),
    COEXISTENCE("Co-Existence", 3),
    // RESPONSE,
    // PRECEDENCE,
    ALTERNATE_RESPONSE("Alternate Response", 3);

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
