package com.bi.activity.entity;

import java.math.BigDecimal;

public class Virtualcoin {
    private Integer id;

    private Integer typeId;

    private String cname;

    private String eunit;

    private String rpcip;

    private BigDecimal cnprice;

    private Boolean pricechange;

    private Integer rpcport;

    private String rpcuser;

    private String rpcpassword;

    private String walletaddress;

    private String addressregexp;

    private String contractadres;

    private Integer contractpow;

    private String contractbalanceof;

    private String contracttransfermethod;

    private Integer contractreceiveadresindex;

    private Integer contractamoutindex;

    private String image;

    private BigDecimal price;

    private Integer priceinputdecimalsize;

    private Integer countinputdecimalsize;

    private BigDecimal countfloor;

    private BigDecimal vcoinrate;

    private BigDecimal moneyrate;

    private Integer moneydecimalsize;

    private BigDecimal putinfloor;

    private BigDecimal putinrate;

    private String putinexplain;

    private BigDecimal extractupper;

    private BigDecimal extractfloor;

    private BigDecimal extractrate;

    private BigDecimal extractfeefloor;

    private Integer extractdecimalsize;

    private String extractexplain;

    private String txidquerybrowser;

    private Integer sort;

    private BigDecimal outcount;

    private Boolean enabled;

    private Boolean trading;

    private Boolean canputin;

    private Boolean canextract;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getEunit() {
        return eunit;
    }

    public void setEunit(String eunit) {
        this.eunit = eunit == null ? null : eunit.trim();
    }

    public String getRpcip() {
        return rpcip;
    }

    public void setRpcip(String rpcip) {
        this.rpcip = rpcip == null ? null : rpcip.trim();
    }

    public BigDecimal getCnprice() {
        return cnprice;
    }

    public void setCnprice(BigDecimal cnprice) {
        this.cnprice = cnprice;
    }

    public Boolean getPricechange() {
        return pricechange;
    }

    public void setPricechange(Boolean pricechange) {
        this.pricechange = pricechange;
    }

    public Integer getRpcport() {
        return rpcport;
    }

    public void setRpcport(Integer rpcport) {
        this.rpcport = rpcport;
    }

    public String getRpcuser() {
        return rpcuser;
    }

    public void setRpcuser(String rpcuser) {
        this.rpcuser = rpcuser == null ? null : rpcuser.trim();
    }

    public String getRpcpassword() {
        return rpcpassword;
    }

    public void setRpcpassword(String rpcpassword) {
        this.rpcpassword = rpcpassword == null ? null : rpcpassword.trim();
    }

    public String getWalletaddress() {
        return walletaddress;
    }

    public void setWalletaddress(String walletaddress) {
        this.walletaddress = walletaddress == null ? null : walletaddress.trim();
    }

    public String getAddressregexp() {
        return addressregexp;
    }

    public void setAddressregexp(String addressregexp) {
        this.addressregexp = addressregexp == null ? null : addressregexp.trim();
    }

    public String getContractadres() {
        return contractadres;
    }

    public void setContractadres(String contractadres) {
        this.contractadres = contractadres == null ? null : contractadres.trim();
    }

    public Integer getContractpow() {
        return contractpow;
    }

    public void setContractpow(Integer contractpow) {
        this.contractpow = contractpow;
    }

    public String getContractbalanceof() {
        return contractbalanceof;
    }

    public void setContractbalanceof(String contractbalanceof) {
        this.contractbalanceof = contractbalanceof == null ? null : contractbalanceof.trim();
    }

    public String getContracttransfermethod() {
        return contracttransfermethod;
    }

    public void setContracttransfermethod(String contracttransfermethod) {
        this.contracttransfermethod = contracttransfermethod == null ? null : contracttransfermethod.trim();
    }

    public Integer getContractreceiveadresindex() {
        return contractreceiveadresindex;
    }

    public void setContractreceiveadresindex(Integer contractreceiveadresindex) {
        this.contractreceiveadresindex = contractreceiveadresindex;
    }

    public Integer getContractamoutindex() {
        return contractamoutindex;
    }

    public void setContractamoutindex(Integer contractamoutindex) {
        this.contractamoutindex = contractamoutindex;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPriceinputdecimalsize() {
        return priceinputdecimalsize;
    }

    public void setPriceinputdecimalsize(Integer priceinputdecimalsize) {
        this.priceinputdecimalsize = priceinputdecimalsize;
    }

    public Integer getCountinputdecimalsize() {
        return countinputdecimalsize;
    }

    public void setCountinputdecimalsize(Integer countinputdecimalsize) {
        this.countinputdecimalsize = countinputdecimalsize;
    }

    public BigDecimal getCountfloor() {
        return countfloor;
    }

    public void setCountfloor(BigDecimal countfloor) {
        this.countfloor = countfloor;
    }

    public BigDecimal getVcoinrate() {
        return vcoinrate;
    }

    public void setVcoinrate(BigDecimal vcoinrate) {
        this.vcoinrate = vcoinrate;
    }

    public BigDecimal getMoneyrate() {
        return moneyrate;
    }

    public void setMoneyrate(BigDecimal moneyrate) {
        this.moneyrate = moneyrate;
    }

    public Integer getMoneydecimalsize() {
        return moneydecimalsize;
    }

    public void setMoneydecimalsize(Integer moneydecimalsize) {
        this.moneydecimalsize = moneydecimalsize;
    }

    public BigDecimal getPutinfloor() {
        return putinfloor;
    }

    public void setPutinfloor(BigDecimal putinfloor) {
        this.putinfloor = putinfloor;
    }

    public BigDecimal getPutinrate() {
        return putinrate;
    }

    public void setPutinrate(BigDecimal putinrate) {
        this.putinrate = putinrate;
    }

    public String getPutinexplain() {
        return putinexplain;
    }

    public void setPutinexplain(String putinexplain) {
        this.putinexplain = putinexplain == null ? null : putinexplain.trim();
    }

    public BigDecimal getExtractupper() {
        return extractupper;
    }

    public void setExtractupper(BigDecimal extractupper) {
        this.extractupper = extractupper;
    }

    public BigDecimal getExtractfloor() {
        return extractfloor;
    }

    public void setExtractfloor(BigDecimal extractfloor) {
        this.extractfloor = extractfloor;
    }

    public BigDecimal getExtractrate() {
        return extractrate;
    }

    public void setExtractrate(BigDecimal extractrate) {
        this.extractrate = extractrate;
    }

    public BigDecimal getExtractfeefloor() {
        return extractfeefloor;
    }

    public void setExtractfeefloor(BigDecimal extractfeefloor) {
        this.extractfeefloor = extractfeefloor;
    }

    public Integer getExtractdecimalsize() {
        return extractdecimalsize;
    }

    public void setExtractdecimalsize(Integer extractdecimalsize) {
        this.extractdecimalsize = extractdecimalsize;
    }

    public String getExtractexplain() {
        return extractexplain;
    }

    public void setExtractexplain(String extractexplain) {
        this.extractexplain = extractexplain == null ? null : extractexplain.trim();
    }

    public String getTxidquerybrowser() {
        return txidquerybrowser;
    }

    public void setTxidquerybrowser(String txidquerybrowser) {
        this.txidquerybrowser = txidquerybrowser == null ? null : txidquerybrowser.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public BigDecimal getOutcount() {
        return outcount;
    }

    public void setOutcount(BigDecimal outcount) {
        this.outcount = outcount;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getTrading() {
        return trading;
    }

    public void setTrading(Boolean trading) {
        this.trading = trading;
    }

    public Boolean getCanputin() {
        return canputin;
    }

    public void setCanputin(Boolean canputin) {
        this.canputin = canputin;
    }

    public Boolean getCanextract() {
        return canextract;
    }

    public void setCanextract(Boolean canextract) {
        this.canextract = canextract;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}