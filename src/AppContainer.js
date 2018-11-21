// @flow local-strict
import React from "react";
import {Container} from 'flux/utils';
import App from "./App";
import ProblemStore from "./data/ProblemStore";
import Dispatcher from "./data/Dispatcher";
import listProblemsByEvent from "./data/listProblemsByEvent";
import NavStore from "./data/NavStore";
import SolutionStore from "./data/SolutionStore";
import type { Props as AppProps } from "./App";

const AppContainer = Container.createFunctional(
    props => <App {...props} />,
    () => [
        ProblemStore,
        NavStore,
        SolutionStore,
    ],
    (): AppProps => {
        let curr = NavStore.getCurrentProblem();
        return {
            events: listProblemsByEvent(ProblemStore.getState()),
            currentProblem: curr && ProblemStore.getProblem(curr.event, curr.number),
            solution: curr && SolutionStore.getSolution(curr.event, curr.number),
            doHome: () => Dispatcher.dispatch({
                type: "go-home",
            }),
            doProblem: (event, number) => Dispatcher.dispatch({
                type: "select-problem",
                event,
                number,
            }),
            updateInput: (event, number, input) => Dispatcher.dispatch({
                type: "update-input",
                event,
                number,
                input,
            }),
            doSolve: (event, number) => Dispatcher.dispatch({
                type: "solve",
                event,
                number,
            }),
        };
    },
);

export default AppContainer;