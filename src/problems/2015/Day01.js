const Day00 = {
    event: "2015",
    day: 1,
    title: "Not Quite Lisp",
    partOne: input => {
        input = input.trim();
        let floor = 0;
        for (var i = 0, l = input.length; i < l; i++) {
            floor += (input.charAt(i) === "(" ? 1 : -1);
        }
        return floor;
    },
    partTwo: input => {
        input = input.trim();
        let floor = 0;
        for (var i = 0, l = input.length; i < l; i++) {
            floor += (input.charAt(i) === "(" ? 1 : -1);
            if (floor < 0) {
                return i + 1;
            }
        }
        return -1;
    },
};

export default Day00;