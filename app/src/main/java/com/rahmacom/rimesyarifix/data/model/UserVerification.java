package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

public class UserVerification {
    @SerializedName("face_path")
    private String facePath;

    @SerializedName("id_card_path")
    private String idCardPath;

    @SerializedName("result")
    private String result;

    @SerializedName("similarity")
    private double similarity;

    @SerializedName("verification_status")
    private VerificationStatus verificationStatus;

    public String getFacePath() {
        return facePath;
    }

    public void setFacePath(String facePath) {
        this.facePath = facePath;
    }

    public String getIdCardPath() {
        return idCardPath;
    }

    public void setIdCardPath(String idCardPath) {
        this.idCardPath = idCardPath;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
    }
}