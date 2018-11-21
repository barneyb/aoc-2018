// @flow strict
import type {Solver} from "../utils/flow";

export default ({
    partOne: input =>
        input.length,
    partTwo: input =>
        input.trim().length,
} : Solver);