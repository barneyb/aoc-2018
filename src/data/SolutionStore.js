// @flow local-strict
import {ReduceStore} from 'flux/utils';
import Dispatcher from "./Dispatcher";
import ProblemStore from "./ProblemStore";
import type {Answer} from "../problems/utils/flow";
import type {Action} from "./Actions";

type Result = {
    value: Answer,
    elapsed: number,
};

export type Solution = {
    working: boolean,
    value?: Answer,
    elapsed?: number,
};

export type Solutions = {
    input: string,
    one: Solution,
    two: Solution,
};

export type State = {
    [string]: Solutions
};

const timedRun: string => (string => Answer) => Result = input => work => {
    const start = Date.now();
    const value = work(input);
    const elapsed = Date.now() - start;
    return {
        value,
        elapsed,
    };
};

const getKey = (event, day) => `${event}/${day}`;

class SolutionStore extends ReduceStore<State> {
    constructor() {
        super(Dispatcher);
    }

    getInitialState() {
        return {};
    }

    reduce(state: State, action: Action) {
        switch (action.type) {
            case "update-input":
                return {
                    ...state,
                    [getKey(action.event, action.day)]: {
                        one: { working: false, },
                        two: { working: false, },
                        ...state[getKey(action.event, action.day)],
                        input: action.input,
                    },
                };
            case "solve":
                Dispatcher.waitFor([
                    ProblemStore.getDispatchToken(),
                ]);
                const event = action.event;
                const day = action.day;
                const p = ProblemStore.getProblem(event, action.day);
                const input = state[getKey(event, action.day)].input;
                const runner = timedRun(input);
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
                            value: action.value,
                            elapsed: action.elapsed,
                        }
                    },
                };
            default:
                return state;
        }
    }

    getSolution(event: string, day: number) {
        return this.getState()[getKey(event, day)];
    }

}

export default new SolutionStore();
