import java.util.*;
public class Election {
        private Map<String, Integer> candidateVotes;
        private PriorityQueue<Map.Entry<String, Integer>> votePriorityQueue;

        public Election() {
            candidateVotes = new HashMap<>();
            votePriorityQueue = new PriorityQueue<>(
                    (a, b) -> b.getValue().compareTo(a.getValue())
            );
        }

        public void initializeCandidates(LinkedList<String> candidates) {
            for (String candidate : candidates) {
                candidateVotes.put(candidate, 0);
            }
        }

        public void castVote(String candidate) {
            if (candidateVotes.containsKey(candidate)) {
                candidateVotes.put(candidate, candidateVotes.get(candidate) + 1);
            }
            updatePriorityQueue();
        }

        public void castRandomVote() {
            Random random = new Random();
            List<String> candidateList = new ArrayList<>(candidateVotes.keySet());
            String randomCandidate = candidateList.get(random.nextInt(candidateList.size()));
            castVote(randomCandidate);
        }

        public void rigElection(String candidate) {
            int remainingVotes = candidateVotes.size();
            for (Map.Entry<String, Integer> entry : candidateVotes.entrySet()) {
                if (!entry.getKey().equals(candidate)) {
                    int votesNeeded = remainingVotes * 2 + 1;
                    candidateVotes.put(entry.getKey(), votesNeeded);
                    remainingVotes--;
                }
            }
            candidateVotes.put(candidate, remainingVotes + 1);
            updatePriorityQueue();
        }

        public List<String> getTopKCandidates(int k) {
            List<String> topCandidates = new ArrayList<>();
            for (int i = 0; i < k && !votePriorityQueue.isEmpty(); i++) {
                topCandidates.add(votePriorityQueue.poll().getKey());
            }
            return topCandidates;
        }

        public void auditElection() {
            for (Map.Entry<String, Integer> entry : votePriorityQueue) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
            }
        }

        private void updatePriorityQueue() {
            votePriorityQueue.clear();
            votePriorityQueue.addAll(candidateVotes.entrySet());
        }
    }
