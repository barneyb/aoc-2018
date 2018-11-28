// @flow strict

export const min: (number[]) => number = ns =>
    ns.reduce((m, n) => Math.min(m, n));

export const max: (number[]) => number = ns =>
    ns.reduce((m, n) => Math.max(m, n));
