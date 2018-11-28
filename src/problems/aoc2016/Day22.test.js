// @flow strict
import p, {
    buildGrid,
    buildNodeList, countViablePairs, gridAt, gridSet, isViablePair,
    parse,
    parseLine,
} from "./Day22";

const { partOne, partTwo } = p;

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

test("buildGrid", () => {
    expect(buildGrid([
        [0, 0, 1, 2, 3],
        [0, 1, 2, 3, 4],
        [1, 0, 3, 4, 5],
        [1, 1, 4, 5, 6],
    ])).toEqual({
        w: 2,
        h: 2,
        data: [
            {size: 1, used: 2, avail: 3},
            {size: 3, used: 4, avail: 5},
            {size: 2, used: 3, avail: 4},
            {size: 4, used: 5, avail: 6},
        ]
    });
});

const makeGrid = () => ({
    w: 3,
    h: 3,
    data: [1, 2, 3, 4, 5, 6, 7, 8, 9]
});

test("gridAt", () => {
    const g = makeGrid();
    expect(gridAt(g, 0, 0)).toBe(1);
    expect(gridAt(g, 1, 0)).toBe(2);
    expect(gridAt(g, 2, 0)).toBe(3);
    expect(gridAt(g, 0, 1)).toBe(4);
    expect(gridAt(g, 1, 1)).toBe(5);
    expect(gridAt(g, 2, 1)).toBe(6);
    expect(gridAt(g, 0, 2)).toBe(7);
    expect(gridAt(g, 1, 2)).toBe(8);
    expect(gridAt(g, 2, 2)).toBe(9);
});

test("gridSet", () => {
    const g = makeGrid();
    gridSet(g, 0, 0, "a");
    gridSet(g, 1, 0, "b");
    gridSet(g, 2, 0, "c");
    gridSet(g, 0, 1, "d");
    gridSet(g, 1, 1, "e");
    gridSet(g, 2, 1, "f");
    gridSet(g, 0, 2, "g");
    gridSet(g, 1, 2, "h");
    gridSet(g, 2, 2, "i");
    expect(g.data).toEqual(["a", "b", "c", "d", "e", "f", "g", "h", "i"]);
});

const EXAMPLE_TWO = "root@ebhq-gridcenter# df -h\n" +
    "Filesystem            Size  Used  Avail  Use%\n" +
    "/dev/grid/node-x0-y0   10T    8T     2T   80%\n" +
    "/dev/grid/node-x0-y1   11T    6T     5T   54%\n" +
    "/dev/grid/node-x0-y2   32T   28T     4T   87%\n" +
    "/dev/grid/node-x1-y0    9T    7T     2T   77%\n" +
    "/dev/grid/node-x1-y1    8T    0T     8T    0%\n" +
    "/dev/grid/node-x1-y2   11T    7T     4T   63%\n" +
    "/dev/grid/node-x2-y0   10T    6T     4T   60%\n" +
    "/dev/grid/node-x2-y1    9T    8T     1T   88%\n" +
    "/dev/grid/node-x2-y2    9T    6T     3T   66%\n";

test("lkjsdf", () => {
    let s = "";
    const g = buildGrid(parse(EXAMPLE_TWO));
    for (let i = 0; i < g.data.length; i++) {
        if (i % g.w === 0) {
            s += "\n"
        }
        s += " " + pad(g.data[i].used, 3) + "/" + pad(g.data[i].size, 3);
    }
    console.log(s)
});

const pad = (s, n) => {
    let r = s.toString();
    while (r.length < n) {
        r = " " + r;
    }
    return r;
};

test("Part One on Example Two", () => {
    // all nodes, except the big one, are viable with the empty one
    expect(partOne(EXAMPLE_TWO)).toBe(7);
});

partTwo && test("example two", () => {
    // per the example, seven steps to move the data over
    expect(partTwo(EXAMPLE_TWO)).toBe(7);
});
