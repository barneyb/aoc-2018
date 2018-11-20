// @flow local-strict
import type {Problem} from "../problems/utils/flow";
import ReduceStore from 'flux/lib/FluxReduceStore';
import Dispatcher from "./Dispatcher";
import type {Action} from "./Actions";

export type State = {
    [string]: {
        [number]: Problem
    }
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
    e[p.day] = p;
};

loadProblem(require("../problems/2015/Day01").default);
loadProblem(require("../problems/2015/Day02").default);
loadProblem(require("../problems/2018/Day00").default);

/*
 * This may seem "silly", but it allows all data access to operate the same way,
 * even though for these data, it's overkill. Consistency!
 */
class ProblemStore extends ReduceStore<State> {
    constructor() {
        super(Dispatcher);
    }

    getInitialState() {
        return initialState;
    }

    reduce(state: State, action: Action) {
        return state;
    }

    getProblem(event: string, day: number) {
        return this.getState()[event][day];
    }

    getProblemsForEvent(event: string) {
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
