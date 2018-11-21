// @flow local-strict
import {ReduceStore} from 'flux/utils';
import Dispatcher from "./Dispatcher";
import ProblemStore from "./ProblemStore";
import type {Answer} from "../problems/utils/flow";
import type {Action} from "./Actions";

type Result = {
    value: Answer,
    elapsed: number,
} | {
    error: Error,
    elapsed: number,
};

export type Solution = {
    working: boolean,
    at: number,
    value?: Answer,
    error?: Error,
    elapsed?: number,
};

export type Solutions = {
    input: string,
    at: number,
    one: Solution,
    two: Solution,
};

export type State = {
    [string]: Solutions
};

const timedRun: string => (string => Answer) => Result = input => work => {
    const start = Date.now();
    let value: Answer;
    try {
        value = work(input);
        if (isNaN(value)) {
            throw new Error("NaN (Not a Number) returned by solver.");
        }
    } catch (error) {
        return {
            error,
            elapsed: Date.now() - start,
        };
    }
    return {
        value,
        elapsed: Date.now() - start,
    };
};

const getKey = (event, day) => `${event}/${day}`;

class SolutionStore extends ReduceStore<State> {
    constructor() {
        super(Dispatcher);
    }

    getInitialState(): State {
        return {};
    }

    reduce(state: State, action: Action): State {
        switch (action.type) {
            case "update-input":
                return {
                    ...state,
                    [getKey(action.event, action.day)]: {
                        one: { working: false, },
                        two: { working: false, },
                        ...state[getKey(action.event, action.day)],
                        input: action.input,
                        at: Date.now(),
                    },
                };
            case "solve":
                Dispatcher.waitFor([
                    ProblemStore.getDispatchToken(),
                ]);
                const event = action.event;
                const day = action.day;
                const p = ProblemStore.getProblem(event, day);
                const sol = state[getKey(event, day)];
                const runner = timedRun(sol == null ? "" : sol.input);
                setTimeout(() => {
                    Dispatcher.dispatch({
                        type: "solved-part",
                        event: event,
                        day: day,
                        part: "one",
                        ...runner(p.partOne)
                    });
                    setTimeout(() => {
                        if (p.partTwo) {
                            Dispatcher.dispatch({
                                type: "solved-part",
                                event: event,
                                day: day,
                                part: "two",
                                ...runner(p.partTwo)
                            });
                        }
                    }, 0);
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
            case "solved-part":
                return {
                    ...state,
                    [getKey(action.event, action.day)]: {
                        ...state[getKey(action.event, action.day)],
                        [action.part]: {
                            working: false,
                            at: Date.now(),
                            error: action.error,
                            value: action.value,
                            elapsed: action.elapsed,
                        }
                    },
                };
            default:
                return state;
        }
    }

    getSolution(event: string, day: number): Solutions {
        return this.getState()[getKey(event, day)];
    }

}

export default new SolutionStore();
