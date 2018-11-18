import {ReduceStore} from 'flux/utils';
import Actions from "./Actions";
import Dispatcher from "./Dispatcher";

const ACTIVE_PROBLEM_LOCAL_STORAGE_KEY = "aoc-active-problem";

class NavStore extends ReduceStore {
    constructor() {
        super(Dispatcher);
    }

    getInitialState() {
        const v = window.localStorage.getItem(ACTIVE_PROBLEM_LOCAL_STORAGE_KEY);
        let currProb = null;
        if (v != null && v.indexOf("/") > 0) {
            const parts = v.split("/");
            currProb = {
                event: parts[0],
                day: parseInt(parts[1]),
            }
        }
        return {
            currentProblem: currProb,
        };
    }

    reduce(state, action) {
        switch (action.type) {
            case Actions.GO_HOME:
                window.localStorage.removeItem(ACTIVE_PROBLEM_LOCAL_STORAGE_KEY);
                return {
                    ...state,
                    currentProblem: null,
                };
            case Actions.SELECT_PROBLEM:
                window.localStorage.setItem(ACTIVE_PROBLEM_LOCAL_STORAGE_KEY, action.event + "/" + action.day);
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
