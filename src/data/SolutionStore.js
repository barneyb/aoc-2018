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

class SolutionStore extends ReduceStore {
    constructor() {
        super(Dispatcher);
    }

    getInitialState() {
        return {};
    }

    reduce(state, action) {
        switch (action.type) {
            case Actions.SOLVE:
                Dispatcher.waitFor([
                    ProblemStore.getDispatchToken(),
                ]);
                const p = ProblemStore.getProblem(action.event, action.day);
                const runner = timedRun(action.input);
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
                    [action.event]: {
                        ...state[action.event],
                        [action.day]: {
                            input: action.input,
                            one: {working: true},
                            two: {working: !!p.partTwo},
                        }
                    },
                };
            case Actions.SOLVED_PART:
                return {
                    ...state,
                    [action.event]: {
                        ...state[action.event],
                        [action.day]: {
                            ...state[action.event][action.day],
                            [action.part]: {
                                working: false,
                                value: action.value,
                                elapsed: action.elapsed,
                            }
                        }
                    },
                };
            default:
                return state;
        }
    }

    getSolution(event, day) {
        let s = this.getState();
        return s.hasOwnProperty(event) ? s[event][day] : null;
    }

}

export default new SolutionStore();
