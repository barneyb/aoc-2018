// @flow strict
import type {Problem} from "../utils/flow";

const Day02 : Problem = {
    event: "2015",
    day: 2,
    title: "I Was Told There Would Be No Math",
    // intro: "",
    partOne: input =>
        sum(parse(input)
            .map(p => wrappingArea(p))),
    partTwo: input =>
        sum(parse(input)
            .map(p => ribbonLength(p))),
};

export default Day02;

type Package = {
    w: number,
    h: number,
    d: number,
};

type PackageStat = Package => number;

type Pair<T> = [T, T];

export const sum = (ns : number[]) =>
    ns.reduce((t, n) => t + n, 0);

export const parse : (string) => Package[] = input =>
    input.trim()
        .split("\n")
        .map(it => it.trim())
        .filter(it => it.length > 0)
        .map(parseLine);

export const parseLine : (string) => Package = line => {
    const ds = line.trim()
        .split("x")
        .map(it => it.trim())
        .map(it => parseInt(it));
    return {
        w: ds[0],
        h: ds[1],
        d: ds[2],
    };
};

export const surfaceArea : PackageStat = p =>
    2 * p.w * p.h + 2 * p.w * p.d + 2 * p.h * p.d;

export const extraArea : PackageStat = p => {
    const ds = smallestPair(p.w, p.h, p.d);
    return ds[0] * ds[1];
};

export const smallestPair : (number, number, number) => Pair<number> = (w, h, d) => {
    let a, b;
    if (w < h) {
        a = w;
        b = d < h ? d : h;
    } else {
        a = h;
        b = d < w ? d : w;
    }
    return a < b ? [a, b] : [b, a];
};

export const wrappingArea : PackageStat = p =>
    surfaceArea(p) + extraArea(p);

export const wrapLength : PackageStat = p => {
    const ds = smallestPair(p.w, p.h, p.d);
    return 2 * (ds[0] + ds[1]);
};

export const bowLength : PackageStat = p =>
    p.w * p.h * p.d;

export const ribbonLength : PackageStat = p =>
    wrapLength(p) + bowLength(p);