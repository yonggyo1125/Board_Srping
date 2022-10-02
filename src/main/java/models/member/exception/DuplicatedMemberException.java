package models.member.exception;

import commons.errors.CommonException;

public class DuplicatedMemberException extends CommonException {
	public DuplicatedMemberException() {
		super("DuplicatedMember", "memId");
	}
}
