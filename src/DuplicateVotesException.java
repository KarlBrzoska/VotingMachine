public class DuplicateVotesException extends Exception {
    private String duplicatename;

    public DuplicateVotesException(String duplicatenamename){
        this.duplicatename = duplicatenamename;

    }


}
