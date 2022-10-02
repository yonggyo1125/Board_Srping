package models.member.exception;

import commons.errors.CommonException;

public class MemberNotFoundException extends CommonException {
	public MemberNotFoundException() {
		super("MemberNotFound", "memId");
	}
}
