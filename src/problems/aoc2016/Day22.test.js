// @flow strict
import {
    buildNodeList, countViablePairs, isViablePair,
    parse,
    parseLine,
} from "./Day22";

test("parseLine", () => {
    expect(
        parseLine("/dev/grid/node-x0-y3     91T   66T    25T   72%")
    ).toEqual([0, 3, 91, 66, 25]);
    expect(
        parseLine("/dev/grid/node-x12-y34     91T   66T    25T   72%")
    ).toEqual([12, 34, 91, 66, 25]);
});

test("parse", () => {
    expect(parse("root@ebhq-gridcenter# df -h\n" +
        "Filesystem              Size  Used  Avail  Use%\n" +
        "/dev/grid/node-x0-y0     94T   67T    27T   71%\n" +
        "/dev/grid/node-x0-y1     89T   67T    22T   75%\n" +
        "/dev/grid/node-x35-y24   85T   67T    18T   78%\n")
    ).toEqual([
        [0, 0, 94, 67, 27],
        [0, 1, 89, 67, 22],
        [35, 24, 85, 67, 18],
    ])
});

test("buildNodeList", () => {
    expect(buildNodeList([
        [1, 2, 12, 23, 34],
        [0, 0, 98, 76, 54],
    ])).toEqual([
        {size: 12, used: 23, avail: 34},
        {size: 98, used: 76, avail: 54},
    ])
});

test("isViablePair", () => {
    const a = {size: 100, used: 66, avail: 34};
    const b = {size: 100, used: 12, avail: 88};
    expect(isViablePair(a, b)).toBe(true);
    // A and B are different
    expect(isViablePair(b, b)).toBe(false);
    // A is not empty
    expect(isViablePair({size: 100, used: 0, avail: 100}, a)).toBe(false);
    // A.used <= B.avail
    expect(isViablePair(b, {size: 100, used: 90, avail: 10})).toBe(false);
});

test("countViablePairs", () => {
    const nodes = [
        {size: 100, used: 66, avail: 34},
        {size: 100, used: 98, avail: 2},
        {size: 100, used: 10, avail: 90},
    ];
    expect(countViablePairs(nodes)).toBe(2);
});