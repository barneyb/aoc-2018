const Day02 = {
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

export const sum = ns =>
    ns.reduce((t, n) => t + n, 0);

export const parse = input =>
    input.trim()
        .split("\n")
        .map(it => it.trim())
        .filter(it => it.length > 0)
        .map(parseLine);

export const parseLine = line =>
    line.trim()
        .split("x")
        .map(it => it.trim())
        .map(it => parseInt(it));

export const surfaceArea = (w, h, d) =>
    2 * w * h + 2 * w * d + 2 * h * d;

export const extraArea = (w, h, d) => {
    const p = smallestPair(w, h, d);
    return p[0] * p[1];
};

export const smallestPair = (w, h, d) => {
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

export const wrappingArea = (w, h, d) =>
    surfaceArea(w, h, d) + extraArea(w, h, d);

export const wrapLength = (w, h, d) => {
    const p = smallestPair(w, h, d);
    return 2 * (p[0] + p[1]);
};

export const bowLength = (w, h, d) =>
    w * h * d;

export const ribbonLength = (w, h, d) =>
    wrapLength(w, h, d) + bowLength(w, h, d);

export default Day02;