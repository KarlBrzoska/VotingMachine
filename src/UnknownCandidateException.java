public class UnknownCandidateException extends Exception {
    private String unknownName;


    public UnknownCandidateException(String unknownName){
        this.unknownName = unknownName;

    }

    /**
     * gets the unknown name that was types in
     * @return the unknown name that was voted for
     */
    public String getUnknownName() {
        return unknownName;
    }
}
