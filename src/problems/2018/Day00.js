const Day00 = {
    event: "2018",
    day: 0,
    title: "Length Finder",
    intro: "Design an algorithm that will measure the length of a non-null string (your input).",
    partOne: input => {
        return input.length;
    },
    partTwo: input => {
        return input.trim().length;
    },
};

export default Day00;