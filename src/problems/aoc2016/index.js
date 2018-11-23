// @flow local-strict
import Day22 from "./Day22";
import type {Problem} from "../utils/flow";
import {wrap as rawWrap} from "../utils/flow";

const wrap = rawWrap("AoC 2016");

const problems : Problem[] = [
    wrap("Grid Computing", 22, Day22),
];

export default problems;
