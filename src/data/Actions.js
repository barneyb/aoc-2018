// @flow strict
import type {Answer} from "../problems/utils/flow";

export type Action = {
    type: "from-url",
} | {
    type: "go-home",
} | {
    type: "select-problem",
    event: string,
    number: number,
} | {
    type: "update-input",
    event: string,
    number: number,
    input: string,
} | {
    type: "solve",
    event: string,
    number: number,
} | {
    type: "solved-part",
    event: string,
    number: number,
    part: string,
    value?: Answer,
    error?: Error,
    elapsed: number,
};
