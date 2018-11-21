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
            currentProblem: curr && ProblemStore.getProblem(curr.event, curr.day),
            solution: curr && SolutionStore.getSolution(curr.event, curr.day),
            doHome: () => Dispatcher.dispatch({
                type: "go-home",
            }),
            doProblem: (event, day) => Dispatcher.dispatch({
                type: "select-problem",
                event,
                day,
            }),
            updateInput: (event, day, input) => Dispatcher.dispatch({
                type: "update-input",
                event,
                day,
                input,
            }),
            doSolve: (event, day) => Dispatcher.dispatch({
                type: "solve",
                event,
                day,
            }),
        };
    },
);

export default AppContainer;