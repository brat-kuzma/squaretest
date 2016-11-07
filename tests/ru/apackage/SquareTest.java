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
    // ������� ����������, ������� ����� ���������� � ������������
    private double actualSideLength; // ����� �������
    private double anotherSideLength; // ������ ����� �������
    private double expectedSquare; // ��������� �������

    private Square square; // ����� square
    private Square sameSquare; // ������ sameSquare
    private Square anotherSquare; // ������ anotherSquare

    final static double sideLengthMin = -100.0; // ��� �� ���������� �������� ��� ��� ����������� ��������
    final static double sideLengthMax = 100.0;
    final static double sideLengthStep = 1.0; // ���
    final static double delta = 1e-6; // ������ ��� �����

    @Parameters(name = "#{index}: side={0} another={1} square={2}") // �������������� junit, ��� ���� - placeholder ��� ����� arrayList
    public static Iterable<Double[]> testData() {
        List<Double[]> data = new ArrayList<Double[]>(); // ������� ArrayList - data (���������� ����)
        for (double sideLength = sideLengthMin; sideLength <= sideLengthMax; sideLength += sideLengthStep) {//���� � ������� �� ��������� ��� arrayList
            data.add(new Double[] {sideLength, sideLength + 10, sideLength * sideLength}); // ������� ����������
        }
        return data;
    }

    public SquareTest(double actualSideLength, double anotherSideLength, double expectedSquare) { // �����������, 2-� ������� ��������������
        this.actualSideLength = actualSideLength; // �������� �������� ����������
        this.anotherSideLength = anotherSideLength;
        this.expectedSquare = expectedSquare;

        square = SquareFactory.newSquare(actualSideLength);
        sameSquare = SquareFactory.newSquare(actualSideLength);
        anotherSquare = SquareFactory.newSquare(anotherSideLength);
    }

    @Test  // �������� �� �����. ����� �������
    public void testNegativeSideLength() { // ����� �� ������� ������� ������������� �����
        assertFalse(square.sideLength() < 0.0);
    }

    @Test // �������� �� ��������� ������� ����
    public void testNotNullSideLength() {
        assertNotEquals(square.sideLength(), 0.0, delta);
    }

    @Test // ���������� �������� �������� ������� � �����������
    public void testSideLength() {
        assertEquals(actualSideLength, square.sideLength(), delta);
    }

    @Test // �������� � ������
    public void testEqualsSquareFalse(){
        assertFalse(square.equalsSquare(anotherSquare)); // �������� � ������
        assertNotEquals(square.square(), anotherSquare.square(), delta);
    }

    @Test // ���������� �������
    public void testSquare(){
        assertEquals(expectedSquare, square.square(), delta);
    }

    @Test //  ��������� � ����� ��
    public void testEqualsSquareTrue(){
        assertTrue(square.equalsSquare(sameSquare));
        assertEquals(square.square(), sameSquare.square(), delta);
    }
}
