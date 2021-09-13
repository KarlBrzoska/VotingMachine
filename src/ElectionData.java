import org.omg.CORBA.INTERNAL;
import java.util.Random;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Hashtable;

public class ElectionData {
    private LinkedList<String> ballot = new LinkedList<String>();
     private Hashtable<String, Integer> firstChoice = new Hashtable<String, Integer>();
     private Hashtable<String, Integer> secondChoice = new Hashtable<String, Integer>();
     private Hashtable<String, Integer> thirdChoice = new Hashtable<String, Integer>();
    Random randomGenerator = new Random();
    Scanner keyboard = new Scanner(System.in);

    /**
     * prints out user interface for user to vote
     */
    public void printBallot () {
        System.out.println("The candidates are" + " ");
        for (String s : ballot) {
            System.out.println(s);
        }

    }

    /**
     * getter for ballot
     * @return returns ballot
     */
    public LinkedList<String> getBallot(){
        return ballot;
    }


     public ElectionData(){
         firstChoice = new Hashtable<String, Integer>();
         secondChoice = new Hashtable<String, Integer>();
         thirdChoice = new Hashtable<String, Integer>();
         randomGenerator = new Random();

     }

    /**
     * Adds votes to the Hastable
     * @param firstCandidate
     * @param secondCandidate
     * @param thirdCandidate
     * @throws DuplicateVotesException
     * @throws UnknownCandidateException
     */
    public void processVote(String firstCandidate, String secondCandidate, String thirdCandidate) throws DuplicateVotesException, UnknownCandidateException {

        if (!ballot.contains(firstCandidate)) {
            throw new UnknownCandidateException(firstCandidate);
        }
        if (!ballot.contains(secondCandidate)) {
            throw new UnknownCandidateException(secondCandidate);
        }
        if (!ballot.contains(thirdCandidate)) {
            throw new UnknownCandidateException(thirdCandidate);
        }
        if (firstCandidate.equals(secondCandidate) || firstCandidate.equals(thirdCandidate)) {
            throw new DuplicateVotesException(firstCandidate);
        }
        if (secondCandidate.equals(thirdCandidate)) {
            throw new DuplicateVotesException(secondCandidate);
        }
        int currentVotes1 = firstChoice.get(firstCandidate) ;
        int currentVotes2 = secondChoice.get(secondCandidate);
        int currentVotes3 = thirdChoice.get(thirdCandidate);

        currentVotes1 = currentVotes1 +1;
        currentVotes2 = currentVotes2 +1;
        currentVotes3 = currentVotes3 +1;

        firstChoice.put(firstCandidate, currentVotes1 );
        secondChoice.put(secondCandidate, currentVotes2 );
        thirdChoice.put(thirdCandidate, currentVotes3 );


    }

    /**
     * adds candiate to the ballot and initalizes hashtable
     * @param name
     * @throws CandidateExistsException
     */
    public void addCandidate(String name) throws CandidateExistsException {
        if (!ballot.contains(name)) {
            ballot.add(name);
            firstChoice.put(name, 0);
            secondChoice.put(name, 0);
            thirdChoice.put(name, 0);
        } else {
            throw new CandidateExistsException(name);
        }
    }


    /**
     * Calculates the candidate with the most first place votes
     * @return returns candidate if they have more than half of first place votes
     */
    public String findWinnerMostFirstVotes() {
        int sum = 0;
        for (String candidate : ballot) {
            sum = sum + firstChoice.get(candidate);
        }
        String winner="";
        for (String candidate : ballot) {
            if (firstChoice.get(candidate) > (sum / 2)) {
                winner = candidate;
                break;
            }
            else {
                winner = "Runoff required";
            }

        }
        return winner;
    }





    /**
     * calculates the candidate with the most points(3 for first palce 2 for second place 1 for third place)
     * @return candidate with most points
     */

    public String findWinnerMostPoints() {


        //String most = null;
        LinkedList<String> possibleWinners = new LinkedList<>();
        int max = 0;
        for (String candidate : ballot) {
            if (3 * firstChoice.get(candidate) + 2 * secondChoice.get(candidate) + thirdChoice.get(candidate) >= max) {
                //most = candidate;
                max = 3 * firstChoice.get(candidate) + 2 * secondChoice.get(candidate) + thirdChoice.get(candidate);

            }

        }

        for (String candidate : ballot){
            if (3 * firstChoice.get(candidate) + 2 * secondChoice.get(candidate) + thirdChoice.get(candidate) == max){
                possibleWinners.add(candidate);
            }
        }
        int x = randomGenerator.nextInt(possibleWinners.size());
        String candidate = possibleWinners.get(x);
        return candidate;
        //return most;
    }

    }




















