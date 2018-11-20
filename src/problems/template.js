// @flow strict
import type {Problem} from "./utils/flow";

const Day : Problem = {
    event: "",
    day: 0,
    title: "",
    // intro: "",
    partOne: input =>
        input.length,
    partTwo: input =>
        input.trim().length,
};

export default Day;