// @flow local-strict
import React, {Component} from "react";
import {Dimmer, Header, Label, Loader, Segment,} from "semantic-ui-react";
import type {Answer} from "./problems/utils/flow";

export type Props = {
    label: string,
    working: boolean,
    stale: boolean,
    value?: Answer,
    error?: Error,
    elapsed?: number,
};

class Result extends Component<Props> {
    render() {
        const {
            label,
            value,
            error,
            elapsed,
            stale,
            working,
        } = this.props;

        return <Segment style={{
            opacity: stale ? 0.5 : 1
        }}>
            <Header>
                {label}
                {stale && <Label color="orange" pointing="left">
                    Stale!
                </Label>}
            </Header>
            {working && <Dimmer active>
                <Loader size='small'>Working</Loader>
            </Dimmer>}
            {error != null
                ? <Label color="red" basic>{error.message}</Label>
                : value != null
                    ? <code style={{
                        display: "inline-block",
                        padding: "0 4px",
                        margin: "0 4px",
                        border: "1px solid #c99",
                        backgroundColor: "#fff7f7",
                        borderRadius: "3px",
                    }}>{value.toString()}</code>
                    : <em>not yet solved</em>
            }
            {elapsed != null && <span style={{
                fontStyle: "italic",
                color: "#999",
            }}>(in {elapsed} ms)</span>}
        </Segment>
    }
}

export default Result;
