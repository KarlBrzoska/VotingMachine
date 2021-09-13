
import org.junit.Test;
import static org.junit.Assert.*;

public class Examples {

    // method to set up a ballot and cast votes


    ElectionData Setup1 () {

        ElectionData ED = new ElectionData();

        // put candidates on the ballot
        try {

            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");

        } catch (Exception e) {}

        // cast votes


        try {

            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("husky", "ziggy", "gompei");
            ED.processVote("husky", "gompei", "ziggy");
            ED.processVote("husky", "gompei", "ziggy");



        }
        catch (UnknownCandidateException e) {
            System.out.println("unknown candidate");

        }
        catch (DuplicateVotesException e) {
        System.out.println("can't vote for candidate twice");

    }


        return(ED);


    }

    // now run a test on a specific election

    @Test
    public void testMostFirstWinner () {

        assertEquals ("husky", Setup1().findWinnerMostFirstVotes());
    }

    @Test
    public void findWinnerMostPoints () {

        assertEquals ("husky", Setup1().findWinnerMostPoints());
    }





    ElectionData Setup2 () {

        ElectionData ED = new ElectionData();

        // put candidates on the ballot
        try {

            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");
            ED.addCandidate("bobby");
            ED.addCandidate("leseroy");

        } catch (Exception e) {}

        // cast votes
        try {
            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("husky", "gompei", "ziggy");
            ED.processVote("leseroy", "bobby", "ziggy");
            ED.processVote("leseroy", "bobby", "ziggy");
            ED.processVote("leseroy", "bobby", "ziggy");
            ED.processVote("leseroy", "bobby", "ziggy");
            ED.processVote("leseroy", "bobby", "ziggy");

        }
        catch (UnknownCandidateException e) {
            System.out.println("unknown candidate");

        }
        catch (DuplicateVotesException e) {
            System.out.println("can't vote for candidate twice");

        }


        return(ED);


    }

    // now run a test on a specific election


    @Test
    public void testMostFirstWinner1 () {

        assertEquals ("leseroy", Setup2().findWinnerMostFirstVotes());
    }

    @Test
    public void findWinnerMostPoints1 () {

        assertEquals ("leseroy", Setup2().findWinnerMostPoints());
        //System.out.println(Setup1().ballot);
        // System.out.println(Setup1().firstChoice.get("husky"));
    }






    ElectionData Setup3 () {

        ElectionData ED = new ElectionData();

        // put candidates on the ballot
        try {

            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");

        } catch (Exception e) {}

        // cast votes


        try {
            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("ziggy", "husky", "gompei");
            ED.processVote("ziggy", "gompei", "husky");
            ED.processVote("husky", "gompei", "ziggy");
            ED.processVote("husky", "gompei", "ziggy");

        }
        catch (UnknownCandidateException e) {
            System.out.println("unknown candidate");

        }
        catch (DuplicateVotesException e) {
            System.out.println("can't vote for candidate twice");

        }


        return(ED);


    }

    // now run a test on a specific election

    @Test
    public void testMostFirstWinnerTies () {

        assertEquals ("Runoff required", Setup3().findWinnerMostFirstVotes());
    }

    @Test
    public void findWinnerMostPointsTies () {
        String output = Setup3().findWinnerMostPoints();
        assertTrue(output.equals("gompei") || output.equals("husky"));

    }




    ElectionData SetupExceptions () {

        ElectionData ED = new ElectionData();

        // put candidates on the ballot
        try {

            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");
            ED.addCandidate("lewis");

        } catch (Exception e) {}

        // cast votes


        try {
            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("ziggy", "husky", "gompei");
            ED.processVote("lewis", "gompei", "husky");
            ED.processVote("lewis", "gompei", "ziggy");
            ED.processVote("lewis", "gompei", "ziggy");
            ED.processVote("lewis", "gompei", "ziggy");

        }
        catch (UnknownCandidateException e) {
            System.out.println("unknown candidate");

        }
        catch (DuplicateVotesException e) {
            System.out.println("can't vote for candidate twice");

        }


        return(ED);


    }

    @Test
    public void testMostFirstWinnerFirstPlaceVotesAnotha () {

        assertEquals ("lewis", SetupExceptions().findWinnerMostFirstVotes());
    }

    @Test
    public void findWinnerMostPointsAnotha () {
        assertEquals ("gompei", SetupExceptions().findWinnerMostPoints());


    }



    @Test(expected=DuplicateVotesException.class)
    public void testDuplicateVotesException() throws DuplicateVotesException , UnknownCandidateException
    {
        SetupExceptions().processVote("ziggy", "gompei", "ziggy");
    }

    @Test(expected=DuplicateVotesException.class)
    public void testDuplicateVotesException1() throws DuplicateVotesException , UnknownCandidateException
    {
        SetupExceptions().processVote("gompei", "gompei", "ziggy");
    }

    @Test(expected=UnknownCandidateException.class)
    public void testUnknownCandidateException() throws UnknownCandidateException, DuplicateVotesException
    {
        SetupExceptions().processVote("gompei", "gompeiii", "ziggy");
    }

    @Test(expected=UnknownCandidateException.class)
    public void testUnknownCandidateException3() throws UnknownCandidateException, DuplicateVotesException
    {
        SetupExceptions().processVote("gompei", "husky", "ziggyyy");
    }

    @Test(expected=UnknownCandidateException.class)
    public void testBothExceptions() throws UnknownCandidateException, DuplicateVotesException
    {
        SetupExceptions().processVote("gompei", "gompei", "ziggyyy");
    }

    @Test(expected=CandidateExistsException.class)
    public void testUnknownCandidateException1add() throws CandidateExistsException
    {
        SetupExceptions().addCandidate("ziggy");
    }

    @Test(expected=CandidateExistsException.class)
    public void testUnknownCandidateException4add() throws CandidateExistsException
    {
        SetupExceptions().addCandidate("gompei");
    }

    @Test(expected=CandidateExistsException.class)
    public void testUnknownCandidateException2() throws CandidateExistsException
    {
        SetupExceptions().addCandidate("husky");
    }





}
