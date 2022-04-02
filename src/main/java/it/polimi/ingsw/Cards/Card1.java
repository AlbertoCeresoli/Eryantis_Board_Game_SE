package it.polimi.ingsw.Cards;

import it.polimi.ingsw.*;
import it.polimi.ingsw.Exceptions.OutOfBoundException;
import it.polimi.ingsw.Exceptions.StudentNotAvailableException;
import it.polimi.ingsw.Exceptions.WrongArrayException;

public class Card1 extends CharCardsIslands {
	private BagNClouds bagNClouds;
	private int[] students;
	private static final int capacity = Constants.CARD1_STUDENTS_CAPACITY;

	/**
	 * Card1 constructor
	 *
	 * @param bagNClouds bagNClouds reference used to draw students in useEffect
	 * @param students   students located on the card
	 */
	public Card1(int cost, IslandInteraction islandInteraction, BagNClouds bagNClouds, int[] students) {
		super(cost, islandInteraction);
		this.bagNClouds = bagNClouds;
		this.students = new int[Constants.NUMBER_OF_STUDENTS_COLOR];
		System.arraycopy(students, 0, this.students, 0, Constants.NUMBER_OF_STUDENTS_COLOR);
	}

	/**
	 * The method removes the student in input, put it on the chosen island
	 * and substitute it with a new student drawn from the bag
	 *
	 * @param index         island's index where the student will be placed
	 * @param studentColor  not used
	 * @param studentArray1 student that will be removed from the card and will be put on the chosen island
	 * @param studentArray2 not used
     */
	@Override
	public void useEffect(int index, int studentColor, int[] studentArray1, int[] studentArray2) throws OutOfBoundException, StudentNotAvailableException, WrongArrayException {
		checkInputs(index, studentArray1);

		for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
			students[i] -= studentArray1[i];
		}
		this.getIslandInteraction().getIslands().get(index).addStudents(studentArray1);
		int[] temp = bagNClouds.drawStudents(1);
		for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
			students[i] += temp[i];
		}
	}

	private void checkInputs(int index, int[] studentArray1) throws OutOfBoundException, WrongArrayException, StudentNotAvailableException {
		if (index < 0 || index >= getIslandInteraction().getIslands().size()) {
			throw new OutOfBoundException();
		}

		if (studentArray1.length != Constants.NUMBER_OF_STUDENTS_COLOR) {
			throw new WrongArrayException();
		}

		for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
			if (students[i] - studentArray1[i] < 0) {
				throw new StudentNotAvailableException();
			}
		}

		int sum = 0;
		for (int i = 0; i < Constants.NUMBER_OF_STUDENTS_COLOR; i++) {
			if (studentArray1[i] < 0) {
				throw new WrongArrayException();
			}
			sum += studentArray1[i];
		}
		if (sum != Constants.CARD1_STUDENTS_TO_MOVE) {
			throw new WrongArrayException();
		}
	}

	public BagNClouds getBagNClouds() {
		return bagNClouds;
	}

	public int[] getStudents() {
		return students;
	}

	public void setBagNClouds(BagNClouds bagNClouds) {
		this.bagNClouds = bagNClouds;
	}

	public void setStudents(int[] students) {
		this.students = students;
	}
}
