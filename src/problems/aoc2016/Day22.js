// @flow strict
import type {Solver} from "../utils/flow";
import {parseByLine} from "../utils/parse";

export default ({
    partOne: input =>
        countViablePairs(buildNodeList(parse(input))),
    // partTwo: input =>
    //     -1,
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

export const buildNodeList: Line[] => Node[] = lines =>
    lines.map(l => ({
        size: l[2],
        used: l[3],
        avail: l[4],
    }));

export const isViablePair: (Node, Node) => boolean = (a, b) => {
    if (a.used === 0) return false;
    if (a === b) return false;
    return a.used <= b.avail;
};

export const countViablePairs: (Node[] => number) = nodes =>
    nodes.reduce((n, a) =>
        n + nodes.reduce((n, b) =>
            isViablePair(a, b) ? n + 1 : n, 0), 0);
