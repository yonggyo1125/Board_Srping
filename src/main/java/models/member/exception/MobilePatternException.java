package models.member.exception;

import commons.errors.CommonException;

public class MobilePatternException extends CommonException {
	public MobilePatternException() {
		super("WrongMobilePattern", "mobile");
	}
}
