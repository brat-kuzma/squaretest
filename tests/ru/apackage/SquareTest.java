package ru.apackage;

import com.sun.javafx.binding.StringFormatter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.socialquantum.testtasks.Square;
import ru.socialquantum.testtasks.SquareFactory;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SquareTest {
    private Square square = SquareFactory.newSquare(100.1);
    private Square sameSquare = SquareFactory.newSquare(100.1);
    private Square anotherSquare = SquareFactory.newSquare(100.01);

    final static double sideLengthMax = 100.0;
    final static double sideLengthStep = 5.3;
    final static double delta = 1e-6;

    @Test
    public void testSideLength() {
        assertEquals(100.1, square.sideLength(), delta);
    }
    // в цикле проверяем возвращаемое значение мтода sideLength. Сравниваем исходоное с возвращенным.
    @Test
    public void testSideLengthRange() {
        for (double sideLength = 0.0; sideLength < sideLengthMax; sideLength += sideLengthStep) {
            Square s = SquareFactory.newSquare(sideLength);
            assertEquals(sideLength, s.sideLength(), delta);
        }
    }

    @Test
    public void testSquare(){
        assertEquals(10020.01, square.square(), delta);
    }
    // в цикле, проверяем на значениях в интрвале от 0 до 100, с шагом 5.3. Считаем площадь и сравниваем.
    @Test
    public void testSquareRange(){
        for (double sideLength = 0.0; sideLength < sideLengthMax; sideLength += sideLengthStep) {
            Square s = SquareFactory.newSquare(sideLength);
            assertEquals(sideLength * sideLength, s.square(), delta);
        }
    }

    @Test
    public void testEqualsSquare(){
        assertTrue(square.equalsSquare(sameSquare)); //  сравнение с таким же
        assertEquals(square.square(), sameSquare.square(), delta);

        assertFalse(square.equalsSquare(anotherSquare)); // сравение с другим
        assertNotEquals(square.square(), anotherSquare.square(), delta);
    }
}