package aromanticcat.umcproject.apiPayload.exception.handler;

import aromanticcat.umcproject.apiPayload.code.BaseErrorCode;
import aromanticcat.umcproject.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
