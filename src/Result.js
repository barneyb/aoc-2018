import React, {Component} from "react";
import PropTypes from "prop-types";
import {Dimmer, Header, Loader, Segment,} from "semantic-ui-react";

class Result extends Component {
    render() {
        const {
            label,
            value,
            elapsed,
            stale,
            working,
        } = this.props;

        return <Segment>
            <Header>{label} {value != null && stale && "(stale)"}</Header>
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
    stale: PropTypes.bool.isRequired,
    working: PropTypes.bool.isRequired,
};

export default Result;
