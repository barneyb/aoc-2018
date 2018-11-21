// @flow local-strict
import Day00 from "./Day00";
import type {Problem} from "../utils/flow";
import {wrap as rawWrap} from "../utils/flow";

const wrap = rawWrap("AoC 2018");

const problems : Problem[] = [
    wrap("Length Finder", 0, Day00, "Design an algorithm that will measure the length of a non-null string (your input)."),
];

export default problems;