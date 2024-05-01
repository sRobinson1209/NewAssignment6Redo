
import java.util.Arrays;
        import java.util.LinkedList;
        import java.util.List;

public class ElectionSystem {
    public static void main(String[] args) {
        Election election = new Election();
        LinkedList<String> candidates = new LinkedList<>(Arrays.asList("Candidate A", "Candidate B", "Candidate C"));
        election.initializeCandidates(candidates);

        // Simulate some votes
        election.castVote("Candidate A");
        election.castVote("Candidate B");
        election.castVote("Candidate A");
        election.castRandomVote();
        election.castRandomVote();

        // Rig the election for Candidate C
        election.rigElection("Candidate C");

        // Get top candidates
        List<String> topCandidates = election.getTopKCandidates(2);
        System.out.println("Top candidates: " + topCandidates);

        // Audit the election
        election.auditElection();
    }
}
