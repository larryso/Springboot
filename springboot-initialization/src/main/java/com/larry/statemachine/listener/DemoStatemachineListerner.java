package com.larry.statemachine.listener;

import com.larry.statemachine.event.Events;
import com.larry.statemachine.state.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.Objects;

@Slf4j
public class DemoStatemachineListerner extends StateMachineListenerAdapter<States, Events> {
    @Override
    public void stateChanged(State<States, Events> from, State<States, Events> to) {
        if(from != null && from.getId() != null){
            log.info("State changed from {} to {}", from.getId(), to.getId());
        }
        super.stateChanged(from, to);
    }

    @Override
    public void transition(Transition<States, Events> transition) {
        if(Objects.nonNull(transition) && Objects.nonNull(transition.getSource()) && Objects.nonNull(transition.getTarget())){
            log.info("Transition from {} to {}", transition.getSource().getId(), transition.getTarget().getId());
        }
        super.transition(transition);
    }

    @Override
    public void transitionStarted(Transition<States, Events> transition) {
        log.info("Transition started: {}", transition);
        super.transitionStarted(transition);
    }

    @Override
    public void transitionEnded(Transition<States, Events> transition) {
        log.info("Transition ended: {}", transition);
        super.transitionEnded(transition);
    }

    @Override
    public void stateMachineStarted(StateMachine<States, Events> stateMachine) {
        log.info("Machine started with ID: {}", stateMachine.getId());
        super.stateMachineStarted(stateMachine);
    }
}
