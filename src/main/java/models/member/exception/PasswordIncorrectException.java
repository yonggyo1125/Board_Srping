package models.member.exception;

import commons.errors.CommonException;

public class PasswordIncorrectException extends CommonException {
	public PasswordIncorrectException() {
		super("PasswordIncorrect", "memPw");
	}
}
