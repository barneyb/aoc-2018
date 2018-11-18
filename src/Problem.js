import React, {Component} from "react";
import PropTypes from "prop-types";
import {Button, Container, Grid, Header,} from "semantic-ui-react";
import Result from "./Result";

class Problem extends Component {
    render() {
        const {
            problem: p,
            solution,
            updateInput,
            doSolve,
        } = this.props;
        return <Container style={{marginTop: "7em"}}>
            <Header as="h1">Day {p.day}: {p.title}</Header>
            {p.intro && <p>{p.intro}
            </p>}
            {p.partOne
                ? <Grid>
                    <Grid.Row>
                        <Grid.Column width={10}>
                            <textarea style={{width: "100%", height: "300px"}} value={(solution && solution.input) || ""}
                                      onChange={e => updateInput(e.target.value)} placeholder="Copy and paste your input here..."/>
                            <Button.Group floated="right">
                                <Button color="green" onClick={doSolve}>Solve!</Button>
                            </Button.Group>
                        </Grid.Column>
                        {solution && <Grid.Column width={6}>
                            {solution.one && <Result label="Part One" {...solution.one} />}
                            {p.partTwo
                                ? <Result label="Part Two" {...solution.two} />
                                : <p>Part two hasn't been implemented yet.</p>
                            }
                        </Grid.Column>
                        }
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
    solution: PropTypes.shape({
        input: PropTypes.string.isRequired,
        one: PropTypes.shape({
            working: PropTypes.bool.isRequired,
            value: PropTypes.oneOfType([
                PropTypes.string,
                PropTypes.number,
            ]),
            elapsed: PropTypes.number,
        }),
        two: PropTypes.shape({
            working: PropTypes.bool.isRequired,
            value: PropTypes.oneOfType([
                PropTypes.string,
                PropTypes.number,
            ]),
            elapsed: PropTypes.number,
        }),
    }),
    updateInput: PropTypes.func.isRequired,
    doSolve: PropTypes.func.isRequired,
};

export default Problem;