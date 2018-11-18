import {ReduceStore} from 'flux/utils';
import Actions from "./Actions";
import Dispatcher from "./Dispatcher";

class NavStore extends ReduceStore {
    constructor() {
        super(Dispatcher);
    }

    getInitialState() {
        return {
            currentProblem: {
                event: "2018",
                day: 0,
            },
        };
    }

    reduce(state, action) {
        switch (action.type) {
            case Actions.GO_HOME:
                return {
                    ...state,
                    currentProblem: null,
                };
            case Actions.SELECT_PROBLEM:
                return {
                    ...state,
                    currentProblem: {
                        event: action.event,
                        day: action.day,
                    },
                };
            default:
                return state;
        }
    }

    getCurrentProblem() {
        return this.getState().currentProblem;
    }
}

export default new NavStore();
