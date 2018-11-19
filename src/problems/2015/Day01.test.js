import p from "./Day01"

test("part one examples", () => {
    expect(p.partOne("(())")).toBe(0);
    expect(p.partOne("()()")).toBe(0);
    expect(p.partOne("(((")).toBe(3);
    expect(p.partOne("(()(()(")).toBe(3);
    expect(p.partOne("))(((((")).toBe(3);
    expect(p.partOne("())")).toBe(-1);
    expect(p.partOne("))(")).toBe(-1);
    expect(p.partOne(")))")).toBe(-3);
    expect(p.partOne(")())())")).toBe(-3);
});

test("part two examples", () => {
    expect(p.partTwo(")")).toBe(1);
    expect(p.partTwo("()())")).toBe(5);
});
