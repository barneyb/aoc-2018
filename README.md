# Barney's AoC 2018

This project contains solutions to [Advent of Code](https://adventofcode.com),
along with a simple web UI to exercise the solutions with your own input. Both
parts are written in ES6, built via a `create-react-app` toolchain, which
requires Node `10.13.0` and NPM `6.4.1`.

Solutions can be found in the `src/problems/2018` folder, along with their
corresponding tests, and are headless.

The web UI is built with React and Flux, and requires Chrome `70` to use.

Similar versions of the various packages will probably work just fine. NPM can
probably be replaced with Yarn, and Chrome with any modern browser. But only
the software/versions listed above have been tested. 

## Using NVM (Optional)

NVM is a simple package that helps with using multiple versions of Node/NPM on
the same machine for different projects. From the project root, run `nvm
install`. It'll ensure the right versions of Node and NPM are installed and set
up in your shell. Each time you launch a new shell, you'll need to run `nvm
use` (or `nvm install`).

## Running The Solver UI

Once Node, NPM, and Chrome are available:

    npm install
    npm start

Chrome should open automatically, but if not, type in one of the addresses that
get printed out. Select a problem, give it some input, and hit "Solve!".

## Developing Solutions

Once Node and NPM are available:

    npm install
    npm test

Copy the `src/problems/template.js` file to the appropriate location, fill in
the problem's details, write some tests next to it, and then implement the
`partOne` and `partTwo` functions.

When your solution is ready to go, register it in `src/data/ProblemStore.js`
with the other solutions.

For a one-shot test-everything run (instead of watching w/ auto rerun), use:

    CI=true npm test

## Flow

Flow is used for typechecking. Testing does _not_ enforce Flow's checks, but
builds do. So you don't have to run it during development, but you will before
you can create a production bundle. To start the Flow server (and get the
initial status report):

    npm run flow

## Linting

ESLint is used for linting, and runs automatically after running tests or Flow.
It can also be invoked manually:

    npm run lint

