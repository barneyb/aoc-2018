import React from "react";
import {Container} from 'flux/utils';
import App from "./App";
import ProblemStore from "./data/ProblemStore";
import Dispatcher from "./data/Dispatcher";
import Actions from "./data/Actions";
import listProblemsByEvent from "./data/listProblemsByEvent";
import NavStore from "./data/NavStore";
import SolutionStore from "./data/SolutionStore";

const AppContainer = Container.createFunctional(
    props => <App {...props} />,
    () => [
        ProblemStore,
        NavStore,
        SolutionStore,
    ],
    () => {
        let curr = NavStore.getCurrentProblem();
        return {
            events: listProblemsByEvent(ProblemStore.getState()),
            currentProblem: curr && ProblemStore.getProblem(curr.event, curr.day),
            solution: curr && SolutionStore.getSolution(curr.event, curr.day),
            doHome: () => Dispatcher.dispatch({
                type: Actions.GO_HOME,
            }),
            doProblem: (event, day) => Dispatcher.dispatch({
                type: Actions.SELECT_PROBLEM,
                event,
                day,
            }),
            updateInput: (event, day, input) => Dispatcher.dispatch({
                type: Actions.UPDATE_INPUT,
                event,
                day,
                input,
            }),
            doSolve: (event, day) => Dispatcher.dispatch({
                type: Actions.SOLVE,
                event,
                day,
            }),
        };
    },
);

export default AppContainer;