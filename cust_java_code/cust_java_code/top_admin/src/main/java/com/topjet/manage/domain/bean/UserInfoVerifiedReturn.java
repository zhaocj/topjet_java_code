package com.topjet.manage.domain.bean;

/**
 * Created by zhouyw on 2016/9/19.
 */

import com.topjet.common.auth.service.IDCheckResult;
import com.topjet.common.auth.service.dto.IDCheckResponseDTO;

public class UserInfoVerifiedReturn {


    private int row;

    private IDCheckResult idCheckResult;

    private int photoCount;

    private String errorMessage;
    private IDCheckResponseDTO  responseDTO;

    public IDCheckResponseDTO getResponseDTO() {
        return responseDTO;
    }

    public void setResponseDTO(IDCheckResponseDTO responseDTO) {
        this.responseDTO = responseDTO;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public IDCheckResult getIdCheckResult() {
        return idCheckResult;
    }

    public void setIdCheckResult(IDCheckResult idCheckResult) {
        this.idCheckResult = idCheckResult;
    }

    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }




}
