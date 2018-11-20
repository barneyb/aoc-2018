// @flow strict
import type {Answer} from "../problems/utils/flow";

export type Action = {
    type: "from-url",
} | {
    type: "go-home",
} | {
    type: "select-problem",
    event: string,
    day: number,
} | {
    type: "update-input",
    event: string,
    day: number,
    input: string,
} | {
    type: "solve",
    event: string,
    day: number,
} | {
    type: "solved-part",
    event: string,
    day: number,
    part: string,
    value: Answer,
    elapsed: number,
};
