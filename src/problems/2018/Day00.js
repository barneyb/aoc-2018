// @flow strict
import type {Problem} from "../utils/flow";

const Day00 : Problem = {
    event: "2018",
    number: 0,
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