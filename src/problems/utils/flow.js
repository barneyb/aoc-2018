// @flow strict

export type Answer = string | number;

export type Problem = {
    event: string,
    day: number,
    title: string,
    intro?: string,
    partOne: (string) => Answer,
    partTwo?: (string) => Answer,
};
