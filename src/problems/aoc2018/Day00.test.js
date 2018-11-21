import p from "./Day00";

it("part one", () => {
    expect(p.partOne("cat")).toEqual(3);
    expect(p.partOne(" cat ")).toEqual(5);
});

it("part two", () => {
    expect(p.partTwo("cat")).toEqual(3);
    expect(p.partTwo(" cat ")).toEqual(3);
});
