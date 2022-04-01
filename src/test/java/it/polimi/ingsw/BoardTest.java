package it.polimi.ingsw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        @DisplayName("simple cases")
        void testAddToHall(){
            int[] testStud1 = {0,0,0,0,1};
            int[] finalStud1 = {0,0,0,0,1};
            int[] testStud2 = {0,0,2,3,1};
            int[] finalStud2 = {0,0,2,3,2};
            assertTrue(board.addToHall(testStud1),"TestStud1 added correctly");
            assertArrayEquals(finalStud1, board.getStudHall(),"Students now are corrects");
            assertTrue(board.addToHall(testStud2),"TestStud2 added correctly");
            assertArrayEquals(finalStud2, board.getStudHall(),"Students now are corrects");
        }

        @Test
        @DisplayName("student's overflow for one or more colors")
        void testOverflowAddToHall(){
            int[] testStud1 = {0,0,0,0,1};
            int[] finalStud1 = {0,0,0,0,1};
            int[] testStud2 = {0,1,12,0,13};
            int[] finalStud2 = {0,0,0,0,1};
            assertTrue(board.addToHall(testStud1),"TestStud1 added correctly");
            assertArrayEquals(finalStud1, board.getStudHall(),"Students now are corrects");
            assertFalse(board.addToHall(testStud2),"TestStud2 added correctly");
            assertArrayEquals(finalStud2, board.getStudHall(),"Students now are corrects");
        }

        @Test
        @DisplayName("simple cases")
        void testRemoveStudent(){
            int[] initialEntrance = {2,3,0,0,0};
            board.addToEntrance(initialEntrance);
            int[] testStud1 = {1,0,0,0,0};
            int[] finalStud1 = {1,3,0,0,0};
            int[] testStud2 = {1,1,0,0,0};
            int[] finalStud2 = {0,2,0,0,0};
            assertTrue(board.removeStudent(testStud1),"TestStud1 added correctly");
            assertArrayEquals(finalStud1, board.getStudEntrance(),"Students now are corrects");
            assertTrue(board.removeStudent(testStud2),"TestStud2 added correctly");
            assertArrayEquals(finalStud2, board.getStudEntrance(),"Students now are corrects");
        }

        @Test
        @DisplayName("given students are more than the students in studEntrance")
        void testOverflowRemoveStudents(){
            int[] initialEntrance = {2,3,0,0,0};
            board.addToEntrance(initialEntrance);
            int[] testStud1 = {1,0,0,0,0};
            int[] finalStud1 = {1,3,0,0,0};
            int[] testStud2 = {4,1,0,0,0};
            int[] finalStud2 = {1,3,0,0,0};
            assertTrue(board.removeStudent(testStud1),"TestStud1 added correctly");
            assertArrayEquals(finalStud1, board.getStudEntrance(),"Students now are corrects");
            assertFalse(board.removeStudent(testStud2),"TestStud2 added correctly");
            assertArrayEquals(finalStud2, board.getStudEntrance(),"Students now are corrects");
        }

        @Test
        @DisplayName("simple cases")
        void testAddToEntrance(){
            int[] testStud1 = {0,0,0,0,1};
            int[] finalStud1 = {0,0,0,0,1};
            int[] testStud2 = {0,0,2,3,1};
            int[] finalStud2 = {0,0,2,3,2};
            assertTrue(board.addToEntrance(testStud1),"TestStud1 added correctly");
            assertArrayEquals(finalStud1, board.getStudEntrance(),"Students now are corrects");
            assertTrue(board.addToEntrance(testStud2),"TestStud2 added correctly");
            assertArrayEquals(finalStud2, board.getStudEntrance(),"Students now are corrects");
        }

        @Test
        @DisplayName("the students exceed the cap of the Entrance")
        void testOverflowAddToEntrance(){
            int[] testStud1 = {0,0,0,0,1};
            int[] finalStud1 = {0,0,0,0,1};
            int[] testStud2 = {0,0,2,3,9};
            int[] finalStud2 = {0,0,0,0,1};
            assertTrue(board.addToEntrance(testStud1),"TestStud1 added correctly");
            assertArrayEquals(finalStud1, board.getStudEntrance(),"Students now are corrects");
            assertFalse(board.addToEntrance(testStud2),"TestStud2 added correctly");
            assertArrayEquals(finalStud2, board.getStudEntrance(),"Students now are corrects");
        }
    }
