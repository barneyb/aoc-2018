// @flow strict

import p, {
    bowLength,
    parse,
    parseLine,
    ribbonLength,
    smallestPair,
    sum,
    surfaceArea,
    wrappingArea,
    wrapLength,
} from "./Day02";

const {
    partOne,
    partTwo,
} = p;

test("surfaceArea", () => {
    expect(surfaceArea(2, 3, 4)).toBe(52);
    expect(surfaceArea(1, 1, 10)).toBe(42);
});

test("smallestPair", () => {
    expect(smallestPair(2, 3, 4)).toEqual([2, 3]);
    expect(smallestPair(2, 4, 3)).toEqual([2, 3]);
    expect(smallestPair(4, 2, 3)).toEqual([2, 3]);
    expect(smallestPair(4, 3, 2)).toEqual([2, 3]);
    expect(smallestPair(3, 2, 4)).toEqual([2, 3]);
    expect(smallestPair(3, 4, 2)).toEqual([2, 3]);
});

test("wrappingArea", () => {
    expect(wrappingArea(2, 3, 4)).toBe(58);
    expect(wrappingArea(1, 1, 10)).toBe(43);
});

test("parseLine", () => {
    expect(parseLine("1x2x3")).toEqual([1, 2, 3]);
    expect(parseLine(" 1 x 2 x 3 ")).toEqual([1, 2, 3]);
});

test("parse", () => {
    expect(parse("")).toEqual([
    ]);
    expect(parse("1x2x3\n")).toEqual([
        [1, 2, 3],
    ]);
    expect(parse("1x2x3\n4x5x6")).toEqual([
        [1, 2, 3],
        [4, 5, 6],
    ]);
});

test("sum", () => {
   expect(sum([1, 2, 3])).toBe(6);
   expect(sum([4, 5, 6])).toBe(15);
});

test("partOne", () =>  {
    expect(partOne("1x1x10\n2x3x4")).toBe(58 + 43)
});

test("wrapLength", () => {
    expect(wrapLength(2, 3 ,4)).toBe(10);
    expect(wrapLength(1, 1, 10)).toBe(4);
});

test("bowLength", () => {
    expect(bowLength(2, 3 ,4)).toBe(24);
    expect(bowLength(1, 1, 10)).toBe(10);
});

test("ribbonLength", () => {
    expect(ribbonLength(2, 3 ,4)).toBe(34);
    expect(ribbonLength(1, 1, 10)).toBe(14);
});

partTwo && test("partTwo", () => {
    expect(partTwo("1x1x10\n2x3x4")).toBe(34 + 14)
});