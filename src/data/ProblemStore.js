import {ReduceStore} from 'flux/utils';
import Dispatcher from "./Dispatcher";

const initialState = {};
const loadProblem = p => {
    if (!initialState.hasOwnProperty(p.event)) {
        initialState[p.event] = {}
    }
    const e = initialState[p.event];
    if (e.hasOwnProperty(e, p.day)) {
        throw new Error(`Problem ${p.event}.${p.day} was registered twice`);
    }
    e[p.day] = p;
};

loadProblem(require("../problems/2015/Day01").default);
loadProblem(require("../problems/2018/Day00").default);

/*
 * This may seem "silly", but it allows all data access to operate the same way,
 * even though for these data, it's overkill. Consistency!
 */
class ProblemStore extends ReduceStore {
    constructor() {
        super(Dispatcher);
    }

    getInitialState() {
        return initialState;
    }

    reduce(state, action) {
        return state;
    }

    getProblem(event, day) {
        return this.getState()[event][day];
    }

    getProblemsForEvent(event) {
        return this.getState()[event];
    }

    getEvents() {
        return Object.keys(this.getState()).sort((a, b) => {
            if (a < b) return -1;
            if (a > b) return 1;
            return 0;
        })
    }
}

export default new ProblemStore();
