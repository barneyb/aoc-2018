// @flow strict

export type Answer = string | number;

export type Solver = {
    partOne: (string) => Answer,
    partTwo?: (string) => Answer,
};

export type Problem = {
    event: string,
    number: number,
    title: string,
    intro?: string,
    partOne: (string) => Answer,
    partTwo?: (string) => Answer,
};

export const wrap = (event: string) => (title: string, number: number, solver: Solver, intro?: string) => ({
    event,
    title,
    intro,
    number,
    ...solver,
});
