// @flow local-strict
import React, {Component} from "react";
import {Container, Header,} from "semantic-ui-react";

export type Props = {};

class Home extends Component<Props> {
    render() {
        return <Container style={{marginTop: "7em"}}>
            <Header as="h1">Barney's AoC Solver</Header>
            <p>Pick a problem from the menu above to load the solver.
            </p>
        </Container>
    }
}

export default Home;