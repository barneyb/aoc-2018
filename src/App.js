import React, {Component} from "react";
import PropTypes from "prop-types";
import {Container, Dropdown, Image, Menu,} from "semantic-ui-react";
import logo from "./logo.svg";
import Home from "./Home";
import Problem from "./Problem";

class App extends Component {

    render() {
        const {
            events,
            currentProblem,
            solution,
            doHome,
            doProblem,
            doSolve,
        } = this.props;
        const {
            event: currEvent,
            day: currDay
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
                                        <Dropdown.Item key={p.day}
                                                       onClick={eo => doProblem(e.event, p.day)}
                                                       active={currEvent === e.event && currDay === p.day}
                                        >Day {p.day}: {p.title}</Dropdown.Item>
                                    )}
                                </Dropdown.Menu>
                            </Dropdown>)}
                    </Container>
                </Menu>
                {currentProblem
                    ? <Problem problem={currentProblem} solution={solution} doSolve={input => doSolve(currentProblem.event, currentProblem.day, input)}/>
                    : <Home/>
                }
            </div>
        );
    }
}

App.propTypes = {
    events: PropTypes.arrayOf(PropTypes.shape({
        event: PropTypes.string.isRequired,
        problems: PropTypes.arrayOf(
            PropTypes.shape({
                day: PropTypes.number.isRequired,
                title: PropTypes.string.isRequired,
            })
        )
    })).isRequired,
    currentProblem: PropTypes.shape({
        event: PropTypes.string.isRequired,
        day: PropTypes.number.isRequired,
    }),
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
    doHome: PropTypes.func.isRequired,
    doProblem: PropTypes.func.isRequired,
    doSolve: PropTypes.func.isRequired,
};

export default App;
