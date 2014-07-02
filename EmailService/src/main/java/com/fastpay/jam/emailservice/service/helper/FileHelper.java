package com.fastpay.jam.emailservice.service.helper;

import com.fastpay.jam.emailservice.exception.FPGenericException;
import com.fastpay.jam.emailservice.exception.InvalidRequestException;

import java.io.InputStream;

/**
 * FileHelper implements a strategy pattern. The idea is you can have various strategies to access files. For example,
 * one of the strategies is S3 Helper that knows how to deal with files stored in Amazon S3. The file helper can be used
 * in various cases as well- Template Files or Attachment Files etc
 * TODO: (Spring-specifc) Refactor to ServiceLocator bean
 */
public interface FileHelper {
    /**
     * This method returns an input stream from the file read given a string file name
     * @param fileName Name of the file in String
     * @return {@link java.io.InputStream}
     * @throws {@code RuntimeException}
     */
    public InputStream readFile(String fileName) throws FPGenericException;
}
