// @flow strict
import type {Solver} from "./flow";

export default ({
    partOne: input =>
        input.length,
    partTwo: input =>
        input.trim().length,
} : Solver);
