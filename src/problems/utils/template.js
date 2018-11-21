// @flow strict
import type {Solver} from "./flow";

const solver : Solver = {
    partOne: input =>
        input.length,
    partTwo: input =>
        input.trim().length,
};

export default solver;