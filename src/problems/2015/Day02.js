// @flow strict
import type {Problem} from "../utils/flow";

type Package = [number, number, number];

type PackageFunc<T> = (number, number, number) => T;

type PackageStat = PackageFunc<number>;

type Pair<T> = [T, T];

const Day02 : Problem = {
    event: "2015",
    day: 2,
    title: "I Was Told There Would Be No Math",
    // intro: "",
    partOne: input =>
        sum(parse(input)
            .map(([w, h, d]) => wrappingArea(w, h, d))),
    partTwo: input =>
        sum(parse(input)
            .map(([w, h, d]) => ribbonLength(w, h, d))),
};

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
    return [ds[0], ds[1], ds[2]]
};

export const surfaceArea : PackageStat = (w, h, d) =>
    2 * w * h + 2 * w * d + 2 * h * d;

export const extraArea : PackageStat = (w, h, d) => {
    const p = smallestPair(w, h, d);
    return p[0] * p[1];
};

export const smallestPair : PackageFunc<Pair<number>> = (w, h, d) => {
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

export const wrappingArea : PackageStat = (w, h, d) =>
    surfaceArea(w, h, d) + extraArea(w, h, d);

export const wrapLength : PackageStat = (w, h, d) => {
    const p = smallestPair(w, h, d);
    return 2 * (p[0] + p[1]);
};

export const bowLength : PackageStat = (w, h, d) =>
    w * h * d;

export const ribbonLength : PackageStat = (w, h, d) =>
    wrapLength(w, h, d) + bowLength(w, h, d);

export default Day02;