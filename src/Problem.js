import React, {Component} from "react";
import PropTypes from "prop-types";
import {Button, Container, Grid, Header,} from "semantic-ui-react";
import Result from "./Result";

const timedRun = input => work => {
    const start = Date.now();
    const value = work(input);
    const elapsed = Date.now() - start;
    return {
        value,
        elapsed,
    };
};

class Problem extends Component {
    constructor(props) {
        super(props);
        this.state = {
            input: "",
            lastSolvedInput: "",
            working: false,
            partOne: null,
            partTwo: null,
        };
        this.onInputChange = this.onInputChange.bind(this);
        this.doSolve = this.doSolve.bind(this);
    }

    onInputChange(e) {
        this.setState({
            input: e.target.value,
        });
    }

    doSolve(e) {
        this.setState({
            working: true,
            lastSolvedInput: this.state.input,
        });
        const runner = timedRun(this.state.input);
        setTimeout(() => {
            const p = this.props.problem;
            this.setState({
                partOne: runner(p.partOne),
                working: !!p.partTwo,
            });
            if (p.partTwo) {
                setTimeout(() => {
                    this.setState({
                        partTwo: runner(p.partTwo),
                        working: false,
                    });
                }, 0);
            }
        }, 0);
    }

    render() {
        const {
            problem: p,
        } = this.props;
        const {
            input,
            lastSolvedInput,
            working,
            partOne,
            partTwo,
        } = this.state;
        const stale = input !== lastSolvedInput;
        return <Container style={{marginTop: "7em"}}>
            <Header as="h1">Day {p.day}: {p.title}</Header>
            {p.intro && <p>{p.intro}
            </p>}
            {p.partOne
                ? <Grid>
                    <Grid.Row>
                        <Grid.Column width={10}>
                            <textarea style={{width: "100%", height: "300px"}} value={input}
                                      onChange={this.onInputChange} placeholder="Copy and paste your input here..."/>
                            <Button.Group floated="right">
                                <Button color="green" onClick={this.doSolve}>Solve!</Button>
                            </Button.Group>
                        </Grid.Column>
                        <Grid.Column width={6}>
                            <Result stale={stale} working={working} label="Part One" {...partOne} />
                            {p.partTwo
                                ? <Result stale={stale} working={working} label="Part Two" {...partTwo} />
                                : <p>Part two hasn't been implemented yet.</p>
                            }
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
                : <p>No implementation has been provided...</p>
            }
        </Container>
    }
}

Problem.propTypes = {
    problem: PropTypes.shape({
        event: PropTypes.string.isRequired,
        day: PropTypes.number.isRequired,
        title: PropTypes.string.isRequired,
        intro: PropTypes.string,
        partOne: PropTypes.func,
        partTwo: PropTypes.func,
    }).isRequired,
};

export default Problem;