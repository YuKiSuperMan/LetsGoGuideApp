package com.njz.letsgoappguides.model.server;

import java.util.List;

/**
 * Created by Administrator on 2018/12/5.
 */

public class AutoServiceModel {


    /**
     * titleImg : xxxx
     * servePrice : 100
     * serveFeature : xxxx
     * serveType : 1
     * renegePriceThree : 7,4
     * renegePriceFive : 3,1
     * costExplain : xxxxx
     * title : xxxx
     * address : 长沙
     * dayNum : 1
     * nightNum : 1
     * serveTypeName : xxxx
     * serveMinNum : 1
     * serveMaxNum : 1
     * addressId : 1
     * status : 1
     * njzGuideServeNoTimeEntities : [{"dateValue":"2018-09-09"},{"dateValue":"2018-11-09"},{"dateValue":"2018-09-10"}]
     * njzGuideServeFormatDtos : [{"njzGuideServeFormatDic":"xdpy_yy","guideServeFormatName":"向导陪游语言类型0","servePriceSelect":"20181129$10,20181130$10,20181212$11.11,20180909$11.11,20180914$11.11,20180915$11.11","serveDefaultPrice":100},{"njzGuideServeFormatDic":"xdpy_yy","guideServeFormatName":"向导陪游语言类型1","servePriceSelect":"20181129$10,20181130$10,20181212$11.11,20180909$11.11,20180914$11.11,20180915$11.11","serveDefaultPrice":100},{"njzGuideServeFormatDic":"xdpy_yy","guideServeFormatName":"向导陪游语言类型2","servePriceSelect":"20181129$10,20181130$10,20181212$11.11,20180909$11.11,20180914$11.11,20180915$11.11","serveDefaultPrice":100}]
     */

    private Integer id;
    private String titleImg;
    private float servePrice;
    private String serveFeature;
    private Integer serveType;
    private String renegePriceThree;
    private String renegePriceFive;
    private String costExplain;
    private String title;
    private String address;
    private Integer dayNum;
    private Integer nightNum;
    private String serveTypeName;
    private Integer serveMinNum;
    private Integer serveMaxNum;
    private Integer addressId;
    private Long status;
    private String isEnabled;
    private List<NjzGuideServeNoTimeEntitiesBean> njzGuideServeNoTimeEntities;
    private List<NjzGuideServeFormatDtosBean> njzGuideServeFormatDtos;
    private String purchaseRules;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public float getServePrice() {
        return servePrice;
    }

    public void setServePrice(float servePrice) {
        if (servePrice <= 0) servePrice = 0;
        this.servePrice = servePrice;
    }

    public String getServeFeature() {
        return serveFeature;
    }

    public void setServeFeature(String serveFeature) {
        this.serveFeature = serveFeature;
    }

    public Integer getServeType() {
        return serveType;
    }

    public void setServeType(Integer serveType) {
        this.serveType = serveType;
    }

    public String getRenegePriceThree() {
        return renegePriceThree;
    }

    public void setRenegePriceThree(String renegePriceThree) {
        this.renegePriceThree = renegePriceThree;
    }

    public String getRenegePriceFive() {
        return renegePriceFive;
    }

    public void setRenegePriceFive(String renegePriceFive) {
        this.renegePriceFive = renegePriceFive;
    }

    public String getCostExplain() {
        return costExplain;
    }

    public void setCostExplain(String costExplain) {
        this.costExplain = costExplain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public Integer getNightNum() {
        return nightNum;
    }

    public void setNightNum(Integer nightNum) {
        this.nightNum = nightNum;
    }

    public String getServeTypeName() {
        return serveTypeName;
    }

    public void setServeTypeName(String serveTypeName) {
        this.serveTypeName = serveTypeName;
    }

    public Integer getServeMinNum() {
        return serveMinNum;
    }

    public void setServeMinNum(Integer serveMinNum) {
        this.serveMinNum = serveMinNum;
    }

    public Integer getServeMaxNum() {
        return serveMaxNum;
    }

    public void setServeMaxNum(Integer serveMaxNum) {
        this.serveMaxNum = serveMaxNum;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public List<NjzGuideServeNoTimeEntitiesBean> getNjzGuideServeNoTimeEntities() {
        return njzGuideServeNoTimeEntities;
    }

    public void setNjzGuideServeNoTimeEntities(List<NjzGuideServeNoTimeEntitiesBean> njzGuideServeNoTimeEntities) {
        this.njzGuideServeNoTimeEntities = njzGuideServeNoTimeEntities;
    }

    public List<NjzGuideServeFormatDtosBean> getNjzGuideServeFormatDtos() {
        return njzGuideServeFormatDtos;
    }

    public void setNjzGuideServeFormatDtos(List<NjzGuideServeFormatDtosBean> njzGuideServeFormatDtos) {
        this.njzGuideServeFormatDtos = njzGuideServeFormatDtos;
    }

    public String getPurchaseRules() {
        return purchaseRules;
    }

    public void setPurchaseRules(String purchaseRules) {
        this.purchaseRules = purchaseRules;
    }


    @Override
    public String toString() {
        return "AutoServiceModel{" +
                "id=" + id +
                ", titleImg='" + titleImg + '\'' +
                ", servePrice=" + servePrice +
                ", serveFeature='" + serveFeature + '\'' +
                ", serveType=" + serveType +
                ", renegePriceThree='" + renegePriceThree + '\'' +
                ", renegePriceFive='" + renegePriceFive + '\'' +
                ", costExplain='" + costExplain + '\'' +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", dayNum=" + dayNum +
                ", nightNum=" + nightNum +
                ", serveTypeName='" + serveTypeName + '\'' +
                ", serveMinNum=" + serveMinNum +
                ", serveMaxNum=" + serveMaxNum +
                ", addressId=" + addressId +
                ", status=" + status +
                ", isEnabled='" + isEnabled + '\'' +
                ", njzGuideServeNoTimeEntities=" + njzGuideServeNoTimeEntities +
                ", njzGuideServeFormatDtos=" + njzGuideServeFormatDtos +
                ", purchaseRules='" + purchaseRules + '\'' +
                '}';
    }

    public static class NjzGuideServeNoTimeEntitiesBean {
        /**
         * dateValue : 2018-09-09
         */

        private String dateValue;

        @Override
        public String toString() {
            return "NjzGuideServeNoTimeEntitiesBean{" +
                    "dateValue='" + dateValue + '\'' +
                    '}';
        }

        public String getDateValue() {
            return dateValue;
        }

        public void setDateValue(String dateValue) {
            this.dateValue = dateValue;
        }
    }

    public static class NjzGuideServeFormatDtosBean {
        /**
         * njzGuideServeFormatDic : xdpy_yy
         * guideServeFormatName : 向导陪游语言类型0
         * servePriceSelect : 20181129$10,20181130$10,20181212$11.11,20180909$11.11,20180914$11.11,20180915$11.11
         * serveDefaultPrice : 100
         */

        private Long id;
        private String njzGuideServeFormatDic;
        private String guideServeFormatName;
        List<NjzGuideServeFormatPriceEntities> njzGuideServeFormatPriceEntities;
        private float serveDefaultPrice;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNjzGuideServeFormatDic() {
            return njzGuideServeFormatDic;
        }

        public void setNjzGuideServeFormatDic(String njzGuideServeFormatDic) {
            this.njzGuideServeFormatDic = njzGuideServeFormatDic;
        }

        public String getGuideServeFormatName() {
            return guideServeFormatName;
        }

        public void setGuideServeFormatName(String guideServeFormatName) {
            this.guideServeFormatName = guideServeFormatName;
        }

        public List<NjzGuideServeFormatPriceEntities> getServePriceSelect() {
            return njzGuideServeFormatPriceEntities;
        }

        public void setServePriceSelect(List<NjzGuideServeFormatPriceEntities> njzGuideServeFormatPriceEntities) {
            this.njzGuideServeFormatPriceEntities = njzGuideServeFormatPriceEntities;
        }

        public float getServeDefaultPrice() {
            return serveDefaultPrice;
        }

        public void setServeDefaultPrice(float serveDefaultPrice) {
            this.serveDefaultPrice = serveDefaultPrice;
        }

        @Override
        public String toString() {
            return "NjzGuideServeFormatDtosBean{" +
                    "id=" + id +
                    ", njzGuideServeFormatDic='" + njzGuideServeFormatDic + '\'' +
                    ", guideServeFormatName='" + guideServeFormatName + '\'' +
                    ", njzGuideServeFormatPriceEntities=" + njzGuideServeFormatPriceEntities +
                    ", serveDefaultPrice=" + serveDefaultPrice +
                    '}';
        }

        public static class NjzGuideServeFormatPriceEntities{
            int yearInt;
            int monthInt;
            int dateInt;
            float price;

            public int getYearInt() {
                return yearInt;
            }

            public void setYearInt(int yearInt) {
                this.yearInt = yearInt;
            }

            public int getMonthInt() {
                return monthInt;
            }

            public void setMonthInt(int monthInt) {
                this.monthInt = monthInt;
            }

            public int getDateInt() {
                return dateInt;
            }

            public void setDateInt(int dateInt) {
                this.dateInt = dateInt;
            }

            public float getPrice() {
                return price;
            }

            public void setPrice(float price) {
                this.price = price;
            }

            @Override
            public String toString() {
                return "NjzGuideServeFormatPriceEntities{" +
                        "yearInt=" + yearInt +
                        ", monthInt=" + monthInt +
                        ", dateInt=" + dateInt +
                        ", price=" + price +
                        '}';
            }
        }

    }
}
