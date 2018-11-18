const listProblemsByEvent = state => {
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
        // descending by event
        if (a.event > b.event) return -1;
        if (a.event < b.event) return  1;
        // assending by day
        if (a.day < b.day) return -1;
        if (a.day > b.day) return  1;
        return 0; // which violates ProblemStore's invariant
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
