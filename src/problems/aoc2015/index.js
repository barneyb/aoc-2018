// @flow local-strict
import Day01 from "./Day01";
import Day02 from "./Day02";
import type {Problem} from "../utils/flow";
import {wrap as rawWrap} from "../utils/flow";

const wrap = rawWrap("AoC 2015");

const problems : Problem[] = [
    wrap("Not Quite Lisp", 1, Day01),
    wrap("I Was Told There Would Be No Math", 2, Day02),
];

export default problems;
