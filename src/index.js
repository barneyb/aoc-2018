import React from "react";
import ReactDOM from "react-dom";
import "semantic-ui-css/semantic.min.css";
import AppContainer from "./AppContainer";
import Dispatcher from "./data/Dispatcher";

if (process.env.NODE_ENV !== "production") {
    Dispatcher.register(console.log);
}

ReactDOM.render(<AppContainer />, document.getElementById("root"));
