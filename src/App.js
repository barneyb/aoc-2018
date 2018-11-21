// @flow local-strict
import React, {Component} from "react";
import {Container, Dropdown, Image, Menu,} from "semantic-ui-react";
import logo from "./logo.svg";
import Home from "./Home";
import Solver from "./Solver";
import type {Problem} from "./problems/utils/flow";
import type {Solutions} from "./data/SolutionStore";
import type {Event} from "./data/listProblemsByEvent";

export type Props = {
    events: Event[],
    currentProblem: Problem,
    solution: Solutions,
    doHome: () => void,
    doProblem: (string, number) => void,
    doSolve: (string, number) => void,
    updateInput: (string, number, string) => void,
};

class App extends Component<Props> {

    render() {
        const {
            events,
            currentProblem,
            solution,
            doHome,
            doProblem,
            updateInput,
            doSolve,
        } = this.props;
        const {
            event: currEvent,
            number: currDay
        } = currentProblem || {};

        return (
            <div className="App">
                <Menu fixed="top">
                    <Container>
                        <Menu.Item as="a" header onClick={() => doHome()}>
                            <Image size="mini" src={logo} style={{marginRight: "1.5em"}}/>
                            Barney's AoC
                        </Menu.Item>
                        {events && events.map(e =>
                            <Dropdown key={e.event} item text={e.event}
                                      className={currEvent === e.event ? "active" : ""}>
                                <Dropdown.Menu>
                                    {e.problems.map(p =>
                                        <Dropdown.Item key={p.number}
                                                       onClick={eo => doProblem(e.event, p.number)}
                                                       active={currEvent === e.event && currDay === p.number}
                                        >Day {p.number}: {p.title}</Dropdown.Item>
                                    )}
                                </Dropdown.Menu>
                            </Dropdown>)}
                    </Container>
                </Menu>
                {currentProblem
                    ? <Solver problem={currentProblem}
                              solution={solution}
                              doSolve={() => doSolve(currentProblem.event, currentProblem.number)}
                              updateInput={input => updateInput(currentProblem.event, currentProblem.number, input)}
                    />
                    : <Home/>
                }
            </div>
        );
    }
}

export default App;
