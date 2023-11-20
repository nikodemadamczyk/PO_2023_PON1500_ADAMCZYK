package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionParserTest {

    @Test
    void move() {
        String[] arg = { "f", "r", "l" };
        List<MoveDirection> expected = new ArrayList<>();
        expected.add(MoveDirection.FORWARD);
        expected.add(MoveDirection.RIGHT);
        expected.add(MoveDirection.LEFT);
        List<MoveDirection> result = OptionsParser.parse(arg);
        assertEquals(expected,result);

        String[] arg2 = {};
        List<MoveDirection> expected2 = new ArrayList<>();
        List<MoveDirection> result2 = OptionsParser.parse(arg2);
        assertEquals(expected2,result2);
    }
}