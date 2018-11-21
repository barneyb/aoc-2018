// @flow local-strict
import type {Problem} from "../problems/utils/flow";
import {ReduceStore} from 'flux/utils';
import Dispatcher from "./Dispatcher";
import type {Action} from "./Actions";
import problems from "../problems";

export type EventProblems = {
    [string]: Problem
};
export type State = {
    [string]: EventProblems
};

const initialState : State = {};
const loadProblem : Problem => void = p => {
    if (!initialState.hasOwnProperty(p.event)) {
        initialState[p.event] = {}
    }
    const e = initialState[p.event];
    if (e.hasOwnProperty(p.day)) {
        throw new Error(`Problem ${p.event}.${p.day} was registered twice`);
    }
    e[p.day.toString()] = p;
};

for (const event in problems) {
    for (const item of Object.keys(problems[event])) {
        loadProblem(problems[event][item]);
    }
}

/*
 * This may seem "silly", but it allows all data access to operate the same way,
 * even though for these data, it's overkill. Consistency!
 */
class ProblemStore extends ReduceStore<State> {
    constructor() {
        super(Dispatcher);
    }

    getInitialState(): State {
        return initialState;
    }

    reduce(state: State, action: Action): State {
        return state;
    }

    getProblem(event: string, day: number): Problem {
        return this.getState()[event][day.toString()];
    }

    getProblemsForEvent(event: string): EventProblems {
        return this.getState()[event];
    }

    getEvents(): string[] {
        return Object.keys(this.getState()).sort((a, b) => {
            if (a < b) return -1;
            if (a > b) return 1;
            return 0;
        })
    }
}

export default new ProblemStore();
