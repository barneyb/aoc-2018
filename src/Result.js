// @flow local-strict
import React, {Component} from "react";
import PropTypes from "prop-types";
import {Dimmer, Header, Loader, Segment,} from "semantic-ui-react";
import type {Answer} from "./problems/utils/flow";

export type Props = {
    label: string,
    working: boolean,
    value?: Answer,
    elapsed?: number,
};

class Result extends Component<Props> {
    render() {
        const {
            label,
            value,
            elapsed,
            working,
        } = this.props;

        return <Segment>
            <Header>{label}</Header>
            {working && <Dimmer active>
                <Loader size='small'>Working</Loader>
            </Dimmer>}
            {value == null
                ? <p><em>not yet solved</em></p>
                : <p>{value} (in {elapsed} ms)</p>}
        </Segment>
    }
}

Result.propTypes = {
    label: PropTypes.string.isRequired,
    value: PropTypes.oneOfType([
        PropTypes.string,
        PropTypes.number,
    ]),
    elapsed: PropTypes.number,
    working: PropTypes.bool.isRequired,
};

export default Result;
