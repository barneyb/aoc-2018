// @flow local-strict

import type {State} from "./ProblemStore";
import type {Problem} from "../problems/utils/flow";

export type Event = {
    event: string,
    problems: Problem[],
};

const explicitEventOrder = ["AoC 2018"];

const listProblemsByEvent: State => Event[] = state => {
    const problems = [];
    for (const e in state) {
        if (state.hasOwnProperty(e)) {
            for (const d in state[e]) {
                if (state[e].hasOwnProperty(d)) {
                    problems.push(state[e][d]);
                }
            }
        }
    }
    problems.sort((a, b) => {
        // by explicit ordering
        const ai = explicitEventOrder.indexOf(a.event);
        const bi = explicitEventOrder.indexOf(b.event);
        if (ai >= 0 && bi >= 0 && ai !== bi) return ai - bi;
        if (ai >= 0 && bi < 0) return -1;
        if (ai < 0 && bi >= 0) return 1;
        // by event name
        if (a.event < b.event) return -1;
        if (a.event > b.event) return  1;
        // by number
        return a.number - b.number;
    });
    let curr = {};
    const groups = [];
    for (const p of problems) {
        if (p.event === curr.event) {
            curr.problems.push(p);
        } else {
            curr = {
                event: p.event,
                problems: [p]
            };
            groups.push(curr);
        }
    }
    return groups;
};

export default listProblemsByEvent;
