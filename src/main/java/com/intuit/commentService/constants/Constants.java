package com.intuit.commentService.constants;

public class Constants {
    public static class ErrorCodes {
        public static final String BAD_GATEWAY="BAD_GATEWAY";
        public static final String BAD_REQUEST="BAD_REQUEST";

        public static final String INVALID_DATA="INVALID_DATA";
        public static final String DATA_INTEGRITY_VIOLATED="DATA_INTEGRITY_VIOLATED";
        public static final String INTERNAL_SERVER_ERROR_CODE="INTERNAL_SERVER_ERROR";
        public static final String INVALID_COMMENT_ID = "INVALID_COMMENT_ID";
        public static final String INVALID_ENTITY = "INVALID_ENTITY";
    }

    public static class ErrorMessages {
        public static final String POST_ID_REQUIRED = "Post id is required";
        public static final String INVALID_COMMENT_ID = "Invalid comment id";
        public static final String CONTENT_REQUIRED = "Content is required";

        public static final String INVALID_ENTITY = "Invalid entity";
        public static final String USER_ID_REQUIRED = "User id is required";
        public static final String DATA_INTEGRITY_VIOLATED = "Data integrity violated (Duplicate/ Invalid data provided)";

        public static final String ENTITY_ID_REQUIRED = "Entity id is required";
        public static final String ENTITY_TYPE_REQUIRED = "Entity type is required";
        public static final String REACTION_META_ID_REQUIRED = "Reaction meta id is required";
    }

    public static class DefaultValues {
        public static final Integer PAGE_NO = 0;
        public static final Integer PAGE_SIZE = 10;
        public static final String INVALID_VALUE = "INVALID_VALUE";

    }
}
