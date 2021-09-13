import java.util.LinkedList;

public class Main {

    public static void main(String[] args){
        ElectionData data = new ElectionData();
        VotingMachine votingMachine = new VotingMachine();
        votingMachine.addWriteIn("gompei");
        votingMachine.addWriteIn("husky");
        votingMachine.addWriteIn("ziggy");


        votingMachine.screen();
    }
}
