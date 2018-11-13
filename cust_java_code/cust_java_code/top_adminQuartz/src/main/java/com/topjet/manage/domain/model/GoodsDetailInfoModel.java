package com.topjet.manage.domain.model;

import java.math.BigDecimal;

public class GoodsDetailInfoModel {
    private Integer goodsId;

    private String sender;

    private String senderMobile;

    private Integer departCityId;

    private String depart1;

    private String depart2;

    private String depart3;

    private String departDetail;

    private BigDecimal departLng;

    private BigDecimal departLat;

    private String receiver;

    private String receiverMobile;

    private Integer destinationCityId;

    private String destinationDetail;

    private String destination1;

    private String destination2;

    private String destination3;

    private BigDecimal destinationLng;

    private BigDecimal destinationLat;

    private BigDecimal distance;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile;
    }

    public Integer getDepartCityId() {
        return departCityId;
    }

    public void setDepartCityId(Integer departCityId) {
        this.departCityId = departCityId;
    }

    public String getDepart1() {
        return depart1;
    }

    public void setDepart1(String depart1) {
        this.depart1 = depart1;
    }

    public String getDepart2() {
        return depart2;
    }

    public void setDepart2(String depart2) {
        this.depart2 = depart2;
    }

    public String getDepart3() {
        return depart3;
    }

    public void setDepart3(String depart3) {
        this.depart3 = depart3;
    }

    public String getDepartDetail() {
        return departDetail;
    }

    public void setDepartDetail(String departDetail) {
        this.departDetail = departDetail;
    }

    public BigDecimal getDepartLng() {
        return departLng;
    }

    public void setDepartLng(BigDecimal departLng) {
        this.departLng = departLng;
    }

    public BigDecimal getDepartLat() {
        return departLat;
    }

    public void setDepartLat(BigDecimal departLat) {
        this.departLat = departLat;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public Integer getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(Integer destinationCityId) {
        this.destinationCityId = destinationCityId;
    }

    public String getDestinationDetail() {
        return destinationDetail;
    }

    public void setDestinationDetail(String destinationDetail) {
        this.destinationDetail = destinationDetail;
    }

    public String getDestination1() {
        return destination1;
    }

    public void setDestination1(String destination1) {
        this.destination1 = destination1;
    }

    public String getDestination2() {
        return destination2;
    }

    public void setDestination2(String destination2) {
        this.destination2 = destination2;
    }

    public String getDestination3() {
        return destination3;
    }

    public void setDestination3(String destination3) {
        this.destination3 = destination3;
    }

    public BigDecimal getDestinationLng() {
        return destinationLng;
    }

    public void setDestinationLng(BigDecimal destinationLng) {
        this.destinationLng = destinationLng;
    }

    public BigDecimal getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(BigDecimal destinationLat) {
        this.destinationLat = destinationLat;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
}