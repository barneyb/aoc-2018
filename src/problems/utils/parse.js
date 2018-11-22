// @flow strict

type ParseOptions = {
    skipFirstNLines?: number, // default 0
    includeEmptyLines?: boolean, // default false
    leaveWhitespace?: boolean, // default false
    leaveFinalNewline?: boolean, // default false
}
export const parseByLine = <R>(input: string, parseLine: string => R, options: ParseOptions = {}): R[] => {
    // temp var to beat https://github.com/facebook/flow/issues/7206
    let _v = options.leaveFinalNewline == null || ! options.leaveFinalNewline;
    let lines = input.split("\n");
    while (_v && lines.length > 0 && lines[lines.length - 1] === "") {
        lines.pop();
    }
    // temp var to beat https://github.com/facebook/flow/issues/7206
    _v = options.leaveWhitespace == null || ! options.leaveWhitespace;
    if (_v) {
        lines = lines.map(it => it.trim());
    }
    // temp var to beat https://github.com/facebook/flow/issues/7206
    _v = options.includeEmptyLines == null || ! options.includeEmptyLines;
    if (_v) {
        lines = lines.filter(it => it.length > 0)
    }
    if (options.skipFirstNLines != null) {
        if (options.skipFirstNLines < 0) {
            throw new Error(`Illegal 'skipFirstNLines' value: ${options.skipFirstNLines}`)
        }
        lines = lines.slice(options.skipFirstNLines);
    }
    return lines.map(parseLine);
};
