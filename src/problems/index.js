// @flow local-strict
import aoc2015 from "./aoc2015";
import aoc2016 from "./aoc2016";
import aoc2018 from "./aoc2018";
import type {Problem} from "./utils/flow";

const problems : Problem[] = [
    ...aoc2015,
    ...aoc2016,
    ...aoc2018,
];

export default problems;