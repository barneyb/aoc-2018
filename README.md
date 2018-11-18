# Barney's AoC 2018

In addition to the sources, you'll need NVM  `0.33.8` and Chrome 70. Similar
versions (or not-so-similar versions) should also work, but weren't tested. From
the project root:

    nvm install    // that's a 'v'
    npm install    // that's a 'p'
    npm start

Chrome will probably open automatically, but if not, type in one of the
addresses that get printed out. Select a problem, give it some input, and hit
"Solve!".

Tests (using Jest) can be executed two ways:

    npm test            // watch mode
    CI=true npm test    // run once and exit

In watch mode, Jest will check Git and watch the filesystem to only run tests
which are "live" based on your changes. In run once mode, it will run all tests
and exit with an appropriate status code.
