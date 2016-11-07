package ru.apackage;

import org.junit.Test;
import ru.socialquantum.testtasks.Square;
import ru.socialquantum.testtasks.SquareFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SquareTest {
    // создаем переменные, которые будут определены в конструкторе
    private double actualSideLength; // длина стороны
    private double anotherSideLength; // другая длина стороны
    private double expectedSquare; // ожидаемая площадь

    private Square square; // объкт square
    private Square sameSquare; // объект sameSquare
    private Square anotherSquare; // объект anotherSquare

    final static double sideLengthMin = -100.0; // тут мы определяем значения для для итеративной проверки
    final static double sideLengthMax = 100.0;
    final static double sideLengthStep = 1.0; // шаг
    final static double delta = 1e-6; // дельта для дабла

    @Parameters(name = "#{index}: side={0} another={1} square={2}") // параметризация junit, эти поля - placeholder для моего arrayList
    public static Iterable<Double[]> testData() {
        List<Double[]> data = new ArrayList<Double[]>(); // создаем ArrayList - data (переименуй меня)
        for (double sideLength = sideLengthMin; sideLength <= sideLengthMax; sideLength += sideLengthStep) {//цикл в котором мы наполняем мой arrayList
            data.add(new Double[] {sideLength, sideLength + 10, sideLength * sideLength}); // процесс наполенния
        }
        return data;
    }

    public SquareTest(double actualSideLength, double anotherSideLength, double expectedSquare) { // конструктор, 2-е условие параметризации
        this.actualSideLength = actualSideLength; // присваем значения переменным
        this.anotherSideLength = anotherSideLength;
        this.expectedSquare = expectedSquare;

        square = SquareFactory.newSquare(actualSideLength);
        sameSquare = SquareFactory.newSquare(actualSideLength);
        anotherSquare = SquareFactory.newSquare(anotherSideLength);
    }

    @Test  // проверка на отриц. длину стороны
    public void testNegativeSideLength() { // можно ли создать сторону отрицательной длины
        assertFalse(square.sideLength() < 0.0);
    }

    @Test // проверка на равенство стороны нулю
    public void testNotNullSideLength() {
        assertNotEquals(square.sideLength(), 0.0, delta);
    }

    @Test // сравниваем заданное значение стороны с возвращемым
    public void testSideLength() {
        assertEquals(actualSideLength, square.sideLength(), delta);
    }

    @Test // сравение с другим
    public void testEqualsSquareFalse(){
        assertFalse(square.equalsSquare(anotherSquare)); // сравение с другим
        assertNotEquals(square.square(), anotherSquare.square(), delta);
    }

    @Test // сравниваем площади
    public void testSquare(){
        assertEquals(expectedSquare, square.square(), delta);
    }

    @Test //  сравнение с таким же
    public void testEqualsSquareTrue(){
        assertTrue(square.equalsSquare(sameSquare));
        assertEquals(square.square(), sameSquare.square(), delta);
    }
}
