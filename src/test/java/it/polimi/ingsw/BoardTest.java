package it.polimi.ingsw;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Constants.Colors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
     Unit test for Board class. Tests:
     - addToHall
     - removeStudent
     - addToEntrance
     */
public class BoardTest
    {
        Board board;

        @BeforeEach
        void setUp(){
            board = new Board();
        }

        @Test
        @DisplayName("simple cases of addToHall")
        void testAddToHall(){
            int[] V1 = {0,0,0,0,1};
            int[] finalStud1 = {0,0,0,0,1};
            int i=0;
            Map<Colors, Integer> testStud1 = new HashMap<>();
            for (Colors c : Colors.values()){
                testStud1.put(c, V1[i]);
                i++;
            }
            assertTrue(board.addToHall(testStud1),"Test failed");
            i=0;
            for (Colors c : Colors.values()){
                assertEquals(finalStud1[i], board.getStudHall().get(c),"Test failed");
                i++;
            }

            int[] V2 = {0,0,2,3,1};
            int[] finalStud2 = {0,0,2,3,2};
            i=0;
            Map<Colors, Integer> testStud2 = new HashMap<>();
            for (Colors c : Colors.values()){
                testStud2.put(c, V2[i]);
                i++;
            }
            assertTrue(board.addToHall(testStud2),"Test failed");
            i=0;
            for (Colors c : Colors.values()){
                assertEquals(finalStud2[i], board.getStudHall().get(c),"Test failed");
                i++;
            }
        }

        @Test
        @DisplayName("student's overflow for one or more colors in addToHall")
        void testOverflowAddToHall(){
            int[] V1 = {0,0,0,0,1};
            int[] finalStud1 = {0,0,0,0,1};
            int i=0;
            Map<Colors, Integer> testStud1 = new HashMap<>();
            for (Colors c : Colors.values()){
                testStud1.put(c, V1[i]);
                i++;
            }
            assertTrue(board.addToHall(testStud1),"Test failed");
            i=0;
            for (Colors c : Colors.values()){
                assertEquals(finalStud1[i], board.getStudHall().get(c),"Test failed");
                i++;
            }

            int[] V2 = {0,1,12,0,13};
            int[] finalStud2 = {0,0,0,0,1};
            i=0;
            Map<Colors, Integer> testStud2 = new HashMap<>();
            for (Colors c : Colors.values()){
                testStud2.put(c, V2[i]);
                i++;
            }
            assertFalse(board.addToHall(testStud2),"Test failed");
            i=0;
            for (Colors c : Colors.values()) {
                assertEquals(finalStud2[i], board.getStudHall().get(c), "Test failed");
                i++;
            }
        }


        @Test
        @DisplayName("simple cases of remove from hall")
        void testRemoveFromHall(){
            int[] V0 = {2,3,0,0,0};
            int i=0;
            Map<Colors, Integer> initialHall = new HashMap<>();
            for (Colors c : Colors.values()){
                initialHall.put(c, V0[i]);
                i++;
            }
            board.addToHall(initialHall);

            int[] V1 = {1,0,0,0,0};
            int[] finalStud1 = {1,3,0,0,0};
            i=0;
            Map<Colors, Integer> testStud1 = new HashMap<>();
            for (Colors c : Colors.values()){
                testStud1.put(c, V1[i]);
                i++;
            }
            assertTrue(board.removeFromHall(testStud1),"Test failed");
            i=0;
            for (Colors c : Colors.values()){
                assertEquals(finalStud1[i], board.getStudHall().get(c),"Test failed");
                i++;
            }
        }

        @Test
        @DisplayName("given students are more than the students in studHall")
        void testOverflowRemoveFromHall(){
            int[] V0 = {2,3,0,0,0};
            int i=0;
            Map<Colors, Integer> initialHall = new HashMap<>();
            for (Colors c : Colors.values()){
                initialHall.put(c, V0[i]);
                i++;
            }
            board.addToHall(initialHall);

            int[] V1 = {4,1,0,0,0};
            int[] finalStud1 = {2,3,0,0,0};
            i=0;
            Map<Colors, Integer> testStud1 = new HashMap<>();
            for (Colors c : Colors.values()){
                testStud1.put(c, V1[i]);
                i++;
            }
            assertFalse(board.removeFromHall(testStud1),"Test failed");
            i=0;
            for (Colors c : Colors.values()){
                assertEquals(finalStud1[i], board.getStudHall().get(c),"Test failed");
                i++;
            }
        }


        @Test
        @DisplayName("simple cases")
        void testAddToEntrance(){
            int[] V1 = {0,0,0,0,1};
            int[] finalStud1 = {0,0,0,0,1};
            int i=0;
            Map<Colors, Integer> testStud1 = new HashMap<>();
            for (Colors c : Colors.values()){
                testStud1.put(c, V1[i]);
                i++;
            }
            assertTrue(board.addToEntrance(testStud1),"Test failed");
            i=0;
            for (Colors c : Colors.values()){
                assertEquals(finalStud1[i], board.getStudEntrance().get(c),"Test failed");
                i++;
            }

            int[] V2 = {0,0,2,3,1};
            int[] finalStud2 = {0,0,2,3,2};
            i=0;
            Map<Colors, Integer> testStud2 = new HashMap<>();
            for (Colors c : Colors.values()){
                testStud2.put(c, V2[i]);
                i++;
            }
            assertTrue(board.addToEntrance(testStud2),"Test failed");
            i=0;
            for (Colors c : Colors.values()){
                assertEquals(finalStud2[i], board.getStudEntrance().get(c),"Test failed");
                i++;
            }
        }

        @Test
        @DisplayName("the students exceed the cap of the Entrance")
        void testOverflowAddToEntrance(){
            int[] V1 = {0,0,2,3,10};
            int[] finalStud1 = {0,0,0,0,0};
            int i=0;
            Map<Colors, Integer> testStud2 = new HashMap<>();
            for (Colors c : Colors.values()){
                testStud2.put(c, V1[i]);
                i++;
            }
            assertFalse(board.addToEntrance(testStud2),"Test failed");
            i=0;
            for (Colors c : Colors.values()){
                assertEquals(finalStud1[i], board.getStudEntrance().get(c),"Test failed");
                i++;
            }
        }

        @Test
        @DisplayName("simple cases of remove from entrance")
        void testRemoveFromEntrance(){
            int[] V0 = {2,3,0,0,0};
            int i=0;
            Map<Colors, Integer> initialEntrance = new HashMap<>();
            for (Colors c : Colors.values()){
                initialEntrance.put(c, V0[i]);
                i++;
            }
            board.addToEntrance(initialEntrance);

            int[] V1 = {1,0,0,0,0};
            int[] finalStud1 = {1,3,0,0,0};
            i=0;
            Map<Colors, Integer> testStud1 = new HashMap<>();
            for (Colors c : Colors.values()){
                testStud1.put(c, V1[i]);
                i++;
            }
            assertTrue(board.removeFromEntrance(testStud1),"Test failed");
            i=0;
            for (Colors c : Colors.values()){
                assertEquals(finalStud1[i], board.getStudEntrance().get(c),"Test failed");
                i++;
            }
        }

        @Test
        @DisplayName("given students are more than the students in studEntrance")
        void testOverflowRemoveFromEntrance(){
            int[] V0 = {2,3,0,0,0};
            int i=0;
            Map<Colors, Integer> initialEntrance = new HashMap<>();
            for (Colors c : Colors.values()){
                initialEntrance.put(c, V0[i]);
                i++;
            }
            board.addToEntrance(initialEntrance);

            int[] V1 = {10,0,0,0,0};
            int[] finalStud1 = {2,3,0,0,0};
            i=0;
            Map<Colors, Integer> testStud1 = new HashMap<>();
            for (Colors c : Colors.values()){
                testStud1.put(c, V1[i]);
                i++;
            }
            assertFalse(board.removeFromEntrance(testStud1),"Test failed");
            i=0;
            for (Colors c : Colors.values()){
                assertEquals(finalStud1[i], board.getStudEntrance().get(c),"Test failed");
                i++;
            }
        }
    }
