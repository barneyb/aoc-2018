// @flow strict
import type {Solver} from "../utils/flow";
import {parseByLine} from "../utils/parse";
import {max} from "../utils/math";

export default ({
    partOne: input =>
        countViablePairs(buildNodeList(parse(input))),
    partTwo: input =>
        countSteps(buildGrid(parse(input))),
} : Solver);

type Line = [number, number, number, number, number];

export const parse: string => Line[] = input =>
    parseByLine(input, parseLine, {
        skipFirstNLines: 2,
    });

export const parseLine: string => Line = line => {
    const ms = /^\/dev\/grid\/node-x([0-9]+)-y([0-9]+) *([0-9]+)T *([0-9]+)T *([0-9]+)T *[0-9]+%$/.exec(line);
    if (ms == null) {
        throw new Error("No match found");
    }
    const ns = ms.slice(1).map(s => parseInt(s));
    if (ns[2] - ns[3] !== ns[4]) {
        throw new Error(`Sizes are inconsistent: ${line}`);
    }
    return [ns[0], ns[1], ns[2], ns[3], ns[4]];
};

type Node = {
    size: number,
    used: number,
    avail: number,
};

const toNode: Line => Node = l => ({
    size: l[2],
    used: l[3],
    avail: l[4],
});

export const buildNodeList: Line[] => Node[] = lines =>
    lines.map(toNode);

export const isViablePair: (Node, Node) => boolean = (a, b) => {
    if (a.used === 0) return false;
    if (a === b) return false;
    return a.used <= b.avail;
};

export const countViablePairs: (Node[] => number) = nodes =>
    nodes.reduce((n, a) =>
        n + nodes.reduce((n, b) =>
            isViablePair(a, b) ? n + 1 : n, 0), 0);

/*
 * Data is a 1D array contained the grid, with i = (y * w + x)
 */
type Grid<T> = {
    w: number,
    h: number,
    data: T[],
};

export const buildGrid: (Line[]) => Grid<Node> = lines => {
    const g = {
        w: max(lines.map(it => it[0])) + 1,
        h: max(lines.map(it => it[1])) + 1,
        data: [],
    };
    for (const l of lines) {
        gridSet(g, l[0], l[1], toNode(l));
    }
    return g;
};

export const gridIdx = <T>(grid: Grid<T>, x: number, y: number): number =>
    y * grid.w + x;

export const gridSet = <T>(grid: Grid<T>, x: number, y: number, v: T): void => {
    grid.data[gridIdx(grid, x, y)] = v;
};

export const gridAt = <T>(grid: Grid<T>, x: number, y: number): T =>
    grid.data[gridIdx(grid, x, y)];

// part two's money function
const countSteps = (grid: Grid<Node>): number => {
    const target = gridAt(grid, grid.w - 1, 0);
    const targetSize = target.used;
    return countStepsHelper(grid);
};

const countStepsHelper = (grid: Grid<Node>, bestSoFar = 99999999): number => {
    return -1;
};
