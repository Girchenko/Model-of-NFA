package nedetermin;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NFA {
    /**
     * Checks if NFA accept string or not
     *
     * @param string to check
     * @return true if yes, false otherwise
     */
    public boolean accept(String string) {
        Set<States> currStates = new HashSet<>();
        currStates.add(States.Q0);

        for (int i = 0; i < string.length(); i++) {
            currStates = union(currStates, string.charAt(i), i + 1);


        } //System.out.println(currStates);
        return currStates.stream().anyMatch(s -> s.accept);
    }

    // helper function which returns set of next states
    private Set<States> union(Set<States> currStates, char ch, int itr) {
        System.out.println("Iteration " + itr + ":");
        Set<States> nextStates = new HashSet<>();
        for (States cState : currStates) {
            nextStates.addAll(cState.transition(ch));
            for (States ns : nextStates) {
                System.out.println(cState.name() + " → " + ch + " => " + ns.name());
            }
        }
        System.out.println("States for iteration " + (itr + 1) + ": " + nextStates);
        return nextStates;
    }

    private enum States {

        Q0(false), Q1(false), Q2(false), Q3(false), Q4(true);

        // transition table
        static {
            Q0.a = new HashSet<>(Arrays.asList(Q1, Q2));
            Q0.b = new HashSet<>(Arrays.asList(Q4));

            Q1.a = new HashSet<>(Arrays.asList(Q0));
            Q1.b = Collections.EMPTY_SET;

            Q2.a = new HashSet<>(Arrays.asList(Q3));
            Q2.b = Collections.EMPTY_SET;

            Q3.a = Collections.EMPTY_SET;
            Q3.b = new HashSet<>(Arrays.asList(Q0));

            Q4.a = Collections.EMPTY_SET;
            Q4.b = Collections.EMPTY_SET;
        }

        final boolean accept;
        Set<States> a;
        Set<States> b;

        // Constructor for state. Every state is either accepting or not.
        States(boolean accept) {
            this.accept = accept;
        }

        /**
         * Represents transition function δ : Q × Σ → P(Q).
         *
         * @param ch symbol from alphabet, Σ = {a, b}
         * @return set of states
         */
        Set<States> transition(char ch) {

            switch (ch) {
                case 'a':
                    return this.a;
                case 'b':
                    return this.b;
                default:
                    return Collections.EMPTY_SET;
            }

        }
    }

    /**
     * Unit tests the {@code NFA} data type.
     *
     * @param args the command-line arguments
     */


}
