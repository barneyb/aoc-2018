// @flow strict
import {parseByLine} from "./parse";

test("parseByLine", () => {
    expect(parseByLine("1\n2\n3", l => parseInt(l))).toEqual([1, 2, 3]);
    expect(parseByLine("\n\n  \n1\n\n2\n\t\n\n3\n", l => parseInt(l))).toEqual([1, 2, 3]);
});

test("parseByLine options", () => {
    const input = "\nheader\n\n \na\nbb\n\n1\n22\n333\n";
    expect(parseByLine(input, l => l.length, {
    })).toEqual([6, 1, 2, 1, 2, 3]);
    expect(parseByLine(input, l => l.length, {
        skipFirstNLines: 2,
    })).toEqual([2, 1, 2, 3]);
    expect(parseByLine(input, l => l.length, {
        skipFirstNLines: 3,
        includeEmptyLines: true,
    })).toEqual([0, 1, 2, 0, 1, 2, 3]);
    expect(parseByLine(input, l => l.length, {
        includeEmptyLines: true,
    })).toEqual([0, 6, 0, 0, 1, 2, 0, 1, 2, 3]);
    expect(parseByLine(input, l => l.length, {
        includeEmptyLines: true,
        leaveWhitespace: true,
    })).toEqual([0, 6, 0, 1, 1, 2, 0, 1, 2, 3]);
    expect(parseByLine(input, l => l.length, {
        includeEmptyLines: true,
        leaveFinalNewline: true,
    })).toEqual([0, 6, 0, 0, 1, 2, 0, 1, 2, 3, 0]);
});
