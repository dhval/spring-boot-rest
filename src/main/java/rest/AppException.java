/*
 * Copyright (c)JNET 2015.
 */

package rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by dhval on 2/26/15.
 */
@XmlRootElement
public class AppException extends Exception {

    private static final long serialVersionUID = -8253732578270387947L;
    @XmlElement(name = "status")
    Integer status = 500;
    @XmlElement(name = "code")
    int code = -1;
    @XmlElement(name = "title")
    String title = "Error";
    @XmlElement(name = "content")
    String content = "Please contact your administrator.";
    @XmlElement(name = "message")
    String message;

    /**
     *
     * @param status
     * @param code
     * @param message
     * @param title
     * @param content
     */
    public AppException(int status, int code, String message,
                        String title, String content) {
        super(message);
        this.status = status;
        this.code = code;
        this.title = title;
        this.content = content;
    }

    public AppException(String content) {
        this.content = content;
    }

    public AppException() { }

    public AppException(Throwable ex) {
        this.message = ex.getLocalizedMessage();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}