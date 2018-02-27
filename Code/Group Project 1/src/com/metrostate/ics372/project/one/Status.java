package com.metrostate.ics372.project.one;

/**
 * Status' used in the application
 * @author adamv
 *
 */
public enum Status {
	OK,
	FILE_NOT_FOUND,
	ERROR_EMPTYING_DATA_FILE,
	ERROR_LOADING_FROM_DISK,
	ERROR_WRITING_TO_DISK,
	UNKNOWN_OBJECT_TYPE_FROM_DISK,
	UNKNOWN_CLASS,
	UNSAVED_DATA_IN_SESSION
}
