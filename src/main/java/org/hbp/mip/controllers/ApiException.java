/**
 * Created by mirco on 04.12.15.
 */

package org.hbp.mip.controllers;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-01-07T07:38:20.227Z")
public class ApiException extends Exception {
    private int code;

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
