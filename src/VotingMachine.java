import java.util.Scanner;

public class VotingMachine {


    public ElectionData data;



       public VotingMachine(){
           this.data = new ElectionData();
       }


    /**
     * user interface for someone who wants to vote
     */
    public void screen() {
           this.data.printBallot();
            System.out.println("Put in your first, second, and third choices in order from 1-3.");
            String candidate1 = data.keyboard.next();
            String candidate2 = data.keyboard.next();
            String candidate3 = data.keyboard.next();

            try {
                this.data.processVote(candidate1, candidate2, candidate3);
                System.out.println("You voted!" + " First Choice is: " + candidate1 + " " + " Second choice is:" + candidate2 + " " +
                        "Third Choice is: " + candidate3);
            } catch (UnknownCandidateException e) {
                System.out.print("candidate does not exist. Press 'y' to add to ballot");
                String confirm = data.keyboard.next();
                if (confirm.toLowerCase().equals("y")){
                    addWriteIn(e.getUnknownName());
                }
                screen();
            } catch (DuplicateVotesException e) {
                System.out.print("can't vote for same candidate twice." + " ");
                screen();
            }

        }

    /**
     * adds name to the ballot during voting
     * @param name
     */
        public void addWriteIn(String name)  {
           try{
               data.addCandidate(name);
           }
           catch (CandidateExistsException e){
               System.out.println("Candidate already exists on the ballot.");
           }

        }
            }
