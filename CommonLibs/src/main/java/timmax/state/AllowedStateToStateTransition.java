package timmax.state;

import timmax.common.Classes;

public class AllowedStateToStateTransition {
    protected final State state1;
    protected final State state2;

    public AllowedStateToStateTransition(State state1, State state2) {
        if (Classes.isInstanceOf(state1, state2.getClass()) ||
                Classes.isInstanceOf(state2, state1.getClass())) {
            //  ToDo:   Избавиться от System.err.println(...).
            //          В конструкторе:
            //          ClientStateAutomaton :: ClientStateAutomaton(IFabricOfClientStates<GameMatchX> iFabricOfClientStates)
            //          есть группа строк, где перечисляются допустимые переходы между состояниями.
            //          Если вызвать конструктор AllowedStateToStateTransition, у которого оба аргумента идентичны,
            //          то здесь мы войдём в этот if.
            //          Но вместо того, чтобы распечатать стек исключения, будет лишь распечатано:
            //          Exception in Application start method
            //          И приложение закроется.
            //          Вот пример строки, чтобы вызвать эту ситуацию:
            //          allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition<>(clientState02ConnectNonIdent, clientState02ConnectNonIdent));
            //          Предположительно это из-за многопоточности.
            //          Поэтому сейчас здесь System.err.println(...).
            System.out.println("AllowedStateToStateTransition :: AllowedStateToStateTransition(State state1, State state2)");
            System.err.println("  state 1 (" + state1 + ") and state 2 (" + state1 + ") are ancestor and successor to each other or vice versa.");
            throw new RuntimeException( "AllowedStateToStateTransition :: AllowedStateToStateTransition(State state1, State state2)\n" +
                    "state 1 (" + state1 + ") and state 2 (" + state1 + ") are ancestor and successor to each other or vice versa.");
        }
        this.state1 = state1;
        this.state2 = state2;
    }

    public State getState1() {
        return state1;
    }

    public State getState2() {
        return state2;
    }

    @Override
    public String toString() {
        return "StateToState{" +
                "state1=" + state1 +
                ", state2=" + state2 +
                '}';
    }
}
