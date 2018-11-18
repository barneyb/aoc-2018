import React from "react";
import {Container} from 'flux/utils';
import App from "./App";
import ProblemStore from "./data/ProblemStore";
import Dispatcher from "./data/Dispatcher";
import Actions from "./data/Actions";
import listProblemsByEvent from "./data/listProblemsByEvent";
import NavStore from "./data/NavStore";

const AppContainer = Container.createFunctional(
    props => <App {...props} />,
    () => [
        ProblemStore,
        NavStore,
    ],
    () => {
        let curr = NavStore.getCurrentProblem();
        return {
            events: listProblemsByEvent(ProblemStore.getState()),
            currentProblem: curr && ProblemStore.getProblem(curr.event, curr.day),
            doHome: () => Dispatcher.dispatch({
                type: Actions.GO_HOME,
            }),
            doProblem: (event, day) => Dispatcher.dispatch({
                type: Actions.SELECT_PROBLEM,
                event,
                day,
            }),
        };
    },
);

export default AppContainer;