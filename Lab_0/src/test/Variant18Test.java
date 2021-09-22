package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import Lab_0.Variant18;

public class Variant18Test {

    @DataProvider (name = "IntegerProvider")
    public Object[][] dpInteger(){
        return new Object[][] {{1000, 1}, {2354, 2}, {73600, 3}};
    }

    @Test(dataProvider = "IntegerProvider")
    public void testInteger(int N, int expected) {
        assertEquals(Variant18.Integer(N), expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIntegerException()
    {
        Variant18.Integer(60);
    }

    @DataProvider (name = "BooleanExpressionsProvider")
    public Object[][] dpBooleanExpressions(){
        return new Object[][] {{1, 2, 3, false}, {1, 1, 2, true}, {-1, -1, -1, true}};
    }

    @Test(dataProvider = "BooleanExpressionsProvider")
    public void testBooleanExpressions(int a, int b, int c, boolean expected) {
        assertEquals(Variant18.BooleanExpressions(a, b, c), expected);
    }

    @DataProvider (name = "ConditionalOperatorProvider")
    public Object[][] dpConditionalOperator(){
        return new Object[][] {{1, 1, 0,  2}, {0, 1, 1, 0}, {1, 0, 1, 1}};
    }

    @Test(dataProvider = "ConditionalOperatorProvider")
    public void testConditionalOperator(int a, int b, int c, int expected) {
        assertEquals(Variant18.ConditionalOperator(a, b, c), expected);
    }

    @DataProvider (name = "SelectionOperatorProvider")
    public Object[][] dpSelectionOperator(){
        return new Object[][] {{100, "сто"}, {444, "чотириста сорок чотири"},
                {703, "сімсот три"}, {217, "двісті сімнадцять"}, {550, "п'ятсот п'ятдесят"}};
    }

    @Test(dataProvider = "SelectionOperatorProvider")
    public void testSelectionOperator(int N, String expected) {
        assertEquals(Variant18.SelectionOperator(N), expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSelectionOperatorException()
    {
        Variant18.SelectionOperator(21);
    }

    @DataProvider (name = "LoopWithParameterProvider")
    public Object[][] dpLoopWithParameter(){
        return new Object[][] {{2.5, 5, -69.46875}, {4, 4, 205}};
    }

    @Test(dataProvider = "LoopWithParameterProvider")
    public void testLoopWithParameter(double A, int N, double expected) {
        assertEquals(Variant18.LoopWithParameter(A, N), expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testLoopWithParameterException()
    {
        Variant18.LoopWithParameter(2,-1);
    }

    @DataProvider (name = "ConditionalLoopProvider")
    public Object[][] dpConditionalLoop(){
        return new Object[][] {{11, new int[]{2, 2}}, {403, new int[]{3, 7}}};
    }

    @Test(dataProvider = "ConditionalLoopProvider")
    public void testConditionalLoop(int N, int[] expected) {
        assertEquals(Variant18.ConditionalLoop(N), expected);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConditionalLoopException()
    {
        Variant18.ConditionalLoop(-1);
    }

    @DataProvider (name = "MaxElementsProvider")
    public Object[][] dpMaxElements(){
        return new Object[][] {{5, new int[]{4, 5, 2, 3, 5}, 2}, {4, new int[] {1, 2, 3, 4}, 0}};
    }

    @Test(dataProvider = "MaxElementsProvider")
    public void testMaxElements(int N, int[] A, int expected) {
        assertEquals(Variant18.MaxElements(N, A), expected);
    }

    @DataProvider (name = "ArrayElementsProvider")
    public Object[][] dpArrayElements(){
        return new Object[][] {{new int[]{5, 3, 1, 4, 9, 7, 3, 4, 5, 2}, 2}, {
            new int[]{4, 6, 5, 7, 7, 5, 6, 8, 9, 5}, 0}, {
            new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1}, 0}};
    }

    @Test(dataProvider = "ArrayElementsProvider")
    public void testArrayElements(int[] A, int expected) {
        assertEquals(Variant18.ArrayElements(A), expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testArrayElementsException()
    {
        Variant18.ArrayElements(new int[]{1, 2, 3});
    }

    @DataProvider (name = "MatrixProductsProvider")
    public Object[][] dpMatrixProducts(){
        return new Object[][] {{new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 3, 3, 1, new int[]{6, 6}},
                {new int[][]{{1, 2, 3, 4}, {1, 7, 1, 7}}, 2, 4, 2, new int[]{16, 49}}};
    }

    @Test(dataProvider = "MatrixProductsProvider")
    public void testMatrixProducts(int[][] A, int M, int N, int K, int[] expected) {
        assertEquals(Variant18.MatrixProducts(A, M, N, K), expected);
    }

}