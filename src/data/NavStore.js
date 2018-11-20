// @flow local-strict
import ReduceStore from 'flux/lib/FluxReduceStore';
import Dispatcher from "./Dispatcher";
import type {Action} from "./Actions";

const ACTIVE_PROBLEM_LOCAL_STORAGE_KEY = "aoc-active-problem";

export type State = {
    currentProblem?: {
        event: string,
        day: number,
    }
};

class NavStore extends ReduceStore<State> {
    constructor() {
        super(Dispatcher);
    }

    getInitialState() {
        const v = window.localStorage.getItem(ACTIVE_PROBLEM_LOCAL_STORAGE_KEY);
        let currProb;
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

    reduce(state: State, action: Action) {
        switch (action.type) {
            case "go-home":
                window.localStorage.removeItem(ACTIVE_PROBLEM_LOCAL_STORAGE_KEY);
                state = {
                    ...state,
                };
                delete state.currentProblem;
                return state;
            case "select-problem":
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
