import {ReduceStore} from 'flux/utils';
import Actions from "./Actions";
import Dispatcher from "./Dispatcher";
import ProblemStore from "./ProblemStore";

const timedRun = input => work => {
    const start = Date.now();
    const value = work(input);
    const elapsed = Date.now() - start;
    return {
        value,
        elapsed,
    };
};

const getKey = (event, day) => `${event}/${day}`;

class SolutionStore extends ReduceStore {
    constructor() {
        super(Dispatcher);
    }

    getInitialState() {
        return {};
    }

    reduce(state, action) {
        switch (action.type) {
            case Actions.UPDATE_INPUT:
                return {
                    ...state,
                    [getKey(action.event, action.day)]: {
                        one: { working: false, },
                        two: { working: false, },
                        ...state[getKey(action.event, action.day)],
                        input: action.input,
                    },
                };
            case Actions.SOLVE:
                Dispatcher.waitFor([
                    ProblemStore.getDispatchToken(),
                ]);
                const p = ProblemStore.getProblem(action.event, action.day);
                const input = state[getKey(action.event, action.day)].input;
                const runner = timedRun(input);
                setTimeout(() => {
                    Dispatcher.dispatch({
                        type: Actions.SOLVED_PART,
                        event: action.event,
                        day: action.day,
                        part: "one",
                        ...runner(p.partOne)
                    });
                    if (p.partTwo) {
                        setTimeout(() => {
                            Dispatcher.dispatch({
                                type: Actions.SOLVED_PART,
                                event: action.event,
                                day: action.day,
                                part: "two",
                                ...runner(p.partTwo)
                            });
                        }, 0);
                    }
                }, 0);
                return {
                    ...state,
                    [getKey(action.event, action.day)]: {
                        ...state[getKey(action.event, action.day)],
                        one: {
                            working: true,
                        },
                        two: {
                            working: !!p.partTwo,
                        },
                    },
                };
            case Actions.SOLVED_PART:
                return {
                    ...state,
                    [getKey(action.event, action.day)]: {
                        ...state[getKey(action.event, action.day)],
                        [action.part]: {
                            working: false,
                            value: action.value,
                            elapsed: action.elapsed,
                        }
                    },
                };
            default:
                return state;
        }
    }

    getSolution(event, day) {
        return this.getState()[getKey(event, day)];
    }

}

export default new SolutionStore();
