// @flow
import React, {Component} from "react";
import {Button, Container, Grid, Header, TextArea,} from "semantic-ui-react";
import Result from "./Result";
import type {Problem, Answer} from "./problems/utils/flow";

type TSolution = {
    working: boolean,
    value: Answer,
    elapsed: number,
}

type Props = {
    problem: Problem,
    solution: {
        input: string,
        one: TSolution,
        two: TSolution,
    },
    updateInput: (string) => void,
    doSolve: () => void,
}

class Solver extends Component<Props> {
    render() {
        const {
            problem: p,
            solution,
            updateInput,
            doSolve,
        } = this.props;
        const url = `https://adventofcode.com/${p.event}/day/${p.day}`;
        return <Container style={{marginTop: "7em"}}>
            <Header as="h1">
                Day {p.day}: {p.title}
                {p.day >=1 && p.day <= 25 && <Header.Subheader><a href={url}>{url}</a></Header.Subheader>}
            </Header>
            {p.intro && <p>{p.intro}
            </p>}
            {p.partOne
                ? <Grid>
                    <Grid.Row>
                        <Grid.Column width={10}>
                            <TextArea style={{width: "100%", height: "300px"}} value={(solution && solution.input) || ""}
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

export default Solver;