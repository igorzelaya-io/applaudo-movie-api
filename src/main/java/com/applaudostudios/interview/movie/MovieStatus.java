package com.applaudostudios.interview.movie;

/**
 * Enum used to represent a movie's status.
 * @author igorzelaya
 *
 */
public enum MovieStatus {

	ACTIVE(0),
	INACTIVE(1);
	
	private final int movieStatusCode;

	MovieStatus(int statusCode) {
		this.movieStatusCode = statusCode; 
	}
	
	/**
	 * get status code
	 * @return
	 */
	public int getMovieStatusCode() {
		return this.movieStatusCode;
	}
	
	/**
	 * Get MovieStatus by status code integer.
	 * @param statusCode
	 * @return
	 */
	public static MovieStatus valueOf(int statusCode) {
		return valueOf(String.valueOf(statusCode));
	}
}
