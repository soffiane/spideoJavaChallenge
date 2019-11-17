package com.spideo.auction.error;

/**
 * The type Custom error type.
 */
public class CustomErrorType {

    private String errorMessage;

    /**
     * Instantiates a new Custom error type.
     *
     * @param errorMessage the error message
     */
    public CustomErrorType(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

}
